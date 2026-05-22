package com.tech.springboot.ecom.service;

import com.tech.springboot.ecom.model.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product getProductById(int id);

    Product addOrUpdateProduct(Product product, MultipartFile imageFile) throws IOException;

    Product getImageById(int id);

    void deleteProduct(int id);

    List<Product> searchProducts(String keyword);
}
