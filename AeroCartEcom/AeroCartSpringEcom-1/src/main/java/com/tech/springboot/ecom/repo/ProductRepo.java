package com.tech.springboot.ecom.repo;

import com.tech.springboot.ecom.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Integer> {

    // Standard CRUD methods are built-in
    @Query("""
    SELECT p FROM Product p
    WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
       OR LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%'))
       OR LOWER(p.brand) LIKE LOWER(CONCAT('%', :keyword, '%'))
       OR LOWER(p.category) LIKE LOWER(CONCAT('%', :keyword, '%'))
""")
    List<Product> searchProduct(String keyword);
}
