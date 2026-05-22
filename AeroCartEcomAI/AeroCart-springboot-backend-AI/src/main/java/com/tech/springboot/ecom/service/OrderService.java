package com.tech.springboot.ecom.service;

import com.tech.springboot.ecom.model.dto.OrderRequest;
import com.tech.springboot.ecom.model.dto.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse placeOrder(OrderRequest orderRequest);

    List<OrderResponse> getAllOrderResponses();
}
