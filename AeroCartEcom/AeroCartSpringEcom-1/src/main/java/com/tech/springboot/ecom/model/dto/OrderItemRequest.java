package com.tech.springboot.ecom.model.dto;

public record OrderItemRequest(
        int productId,
        int quantity
) {
}
