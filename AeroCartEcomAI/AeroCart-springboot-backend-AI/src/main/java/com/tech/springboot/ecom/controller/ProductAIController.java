package com.tech.springboot.ecom.controller;

import com.tech.springboot.ecom.service.ProductAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductAIController {

    private final ProductAIService productAIService;

    @Autowired
    public ProductAIController(ProductAIService productAIService) {
        this.productAIService = productAIService;
    }

    @PostMapping("/product/generate-description")
    public ResponseEntity<String> generateProductDescription(@RequestParam String name, @RequestParam String category) {

        try {
            String aiGenDesc = productAIService.generateDescription(name, category);
            return new ResponseEntity<>(aiGenDesc, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
