package com.tech.springboot.ecom.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductAIService {

    private final ChatClient chatClient;

    @Autowired
    public ProductAIService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String generateDescription(String name, String category) {

        String prompt = String.format("""
                
                Write a concise and professional product description for an e-commerce listing.
                
                Product Name: %s
                Category: %s
                
                Keep it simple, engaging, and highlight its primary features or benefits.
                Avoid technical jargon and keep it customer-friendly.
                Limit the description to 250 characters maximum.
                
                """, name , category );

        return chatClient.prompt()
                .user(prompt)
                .call()
                .chatResponse()
                .getResult()
                .getOutput()
                .getText();
    }
}
