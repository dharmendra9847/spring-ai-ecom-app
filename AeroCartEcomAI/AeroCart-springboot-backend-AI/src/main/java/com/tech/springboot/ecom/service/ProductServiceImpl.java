package com.tech.springboot.ecom.service;

import com.tech.springboot.ecom.model.entity.Product;
import com.tech.springboot.ecom.repo.ProductRepo;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final VectorStore vectorStore;

    @Autowired
    public ProductServiceImpl(ProductRepo productRepo, VectorStore vectorStore) {
        this.productRepo = productRepo;
        this.vectorStore = vectorStore;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product getProductById(int id) {
        return productRepo.findById(id).orElse(new Product(-1));
    }

    @Override
    public Product addOrUpdateProduct(Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());

        Product savedProduct = productRepo.save(product);

        String content = String.format(
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
                savedProduct.getName(),
                savedProduct.getDescription(),
                savedProduct.getBrand(),
                savedProduct.getCategory(),
                savedProduct.getPrice(),
                savedProduct.getReleaseDate(),
                savedProduct.isProductAvailable(),
                savedProduct.getStockQuantity()

        );

        Document document = new Document(
                UUID.randomUUID().toString(),
                content,
                Map.of("productId", String.valueOf(savedProduct.getId()))
        );

        vectorStore.add(List.of(document));

        return savedProduct;
    }

    @Override
    public Product getImageById(int id) {
        return productRepo.findById(id).orElse(new Product(-1));
    }

    @Override
    public void deleteProduct(int id) {
        productRepo.deleteById(id);
    }

    @Override
    public List<Product> searchProducts(String keyword) {
        return productRepo.searchProduct(keyword);
    }

}
