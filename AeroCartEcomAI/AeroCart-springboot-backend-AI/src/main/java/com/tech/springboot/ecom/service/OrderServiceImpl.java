package com.tech.springboot.ecom.service;

import com.tech.springboot.ecom.model.dto.OrderItemRequest;
import com.tech.springboot.ecom.model.dto.OrderItemResponse;
import com.tech.springboot.ecom.model.dto.OrderRequest;
import com.tech.springboot.ecom.model.dto.OrderResponse;
import com.tech.springboot.ecom.model.entity.Order;
import com.tech.springboot.ecom.model.entity.OrderItem;
import com.tech.springboot.ecom.model.entity.Product;
import com.tech.springboot.ecom.repo.OrderRepo;
import com.tech.springboot.ecom.repo.ProductRepo;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class OrderServiceImpl implements  OrderService {

    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;
    private final VectorStore vectorStore;

    @Autowired
    public OrderServiceImpl(ProductRepo productRepo, OrderRepo orderRepo, VectorStore vectorStore) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
        this.vectorStore = vectorStore;
    }

    @Override
    public OrderResponse placeOrder(OrderRequest orderRequest) {

        //Here, Create Order Object
        Order order = new Order();
        String orderId = "ORD" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        order.setOrderId(orderId);
        order.setCustomerName(orderRequest.customerName());
        order.setEmail(orderRequest.email());
        order.setStatus("PLACED");
        order.setOrderDate(LocalDate.now());

        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemRequest itemRequest : orderRequest.items()) {
            Product product = productRepo.findById(itemRequest.productId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            product.setStockQuantity(product.getStockQuantity() - itemRequest.quantity());
            productRepo.save(product);

            // delete
            String filter = String.format("productId == %s", String.valueOf(product.getId()));
            vectorStore.delete(filter);

            //update new entry
            String updatedContent = String.format(
                    """
               Product Name: %s
               Description: %s
               Brand: %s
               Category: %s
               Price: %.2f
               Release Date: %s
               Available: %s
               Stock: %d
               """,
                    product.getName(),
                    product.getDescription(),
                    product.getBrand(),
                    product.getCategory(),
                    product.getPrice(),
                    product.getReleaseDate(),
                    product.isProductAvailable(),
                    product.getStockQuantity()
            );

            Document updatedDocument = new Document(
                    UUID.randomUUID().toString(),
                    updatedContent,
                    Map.of("productId", String.valueOf(product.getId()))
            );

            vectorStore.add(List.of(updatedDocument));

            OrderItem orderItem = OrderItem.builder()
                    .product(product)
                    .quantity(itemRequest.quantity())
                    .totalPrice(product.getPrice().multiply(BigDecimal.valueOf(itemRequest.quantity())))
                    .order(order)
                    .build();
            orderItems.add(orderItem);
        }

        order.setOrderItems(orderItems);
        Order saveOrder = orderRepo.save(order);

        OrderResponse orderResponse = getOrderResponse(order, saveOrder);
        return orderResponse;
    }

    private static OrderResponse getOrderResponse(Order order, Order saveOrder) {
        List<OrderItemResponse> itemResponses = new ArrayList<>();
        for (OrderItem orderItem : order.getOrderItems()) {
            OrderItemResponse orderItemResponse = new OrderItemResponse(
                    orderItem.getProduct().getName(),
                    orderItem.getQuantity(),
                    orderItem.getTotalPrice()
            );
            itemResponses.add(orderItemResponse);
        }

        OrderResponse orderResponse = new OrderResponse(
                saveOrder.getOrderId(),
                saveOrder.getCustomerName(),
                saveOrder.getEmail(),
                saveOrder.getStatus(),
                saveOrder.getOrderDate(),
                itemResponses
        );
        return orderResponse;
    }

    @Override
    public List<OrderResponse> getAllOrderResponses() {

        List<Order> orders = orderRepo.findAll();
        List<OrderResponse> orderResponses = new ArrayList<>();

        for (Order order : orders) {

            OrderResponse orderResponse = getOrderResponse(order, order);
            orderResponses.add(orderResponse);
        }
        return orderResponses;
    }
}
