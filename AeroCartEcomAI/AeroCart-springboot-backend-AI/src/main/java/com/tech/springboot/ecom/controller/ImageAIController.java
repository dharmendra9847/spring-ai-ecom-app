package com.tech.springboot.ecom.controller;

import com.tech.springboot.ecom.service.ProductAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ImageAIController {

    private final ProductAIService productAIService;

    @Autowired
    public ImageAIController(ProductAIService productAIService) {
        this.productAIService = productAIService;
    }

    @PostMapping("/product/generate-image")
    public ResponseEntity<byte[]> generateProductImage(@RequestParam String name, @RequestParam String category, @RequestParam String description) {

        try {
            byte[] generateImage = productAIService.generateImage(name, category, description);
            return new ResponseEntity<>(generateImage, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
