package com.tech.springboot.ecom.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductAIService {

    private final ChatClient chatClient;
    private final ImageGenerationService imageGenerationService;

    @Autowired
    public ProductAIService(ChatClient chatClient, ImageGenerationService imageGenerationService) {
        this.chatClient = chatClient;
        this.imageGenerationService = imageGenerationService;
    }

    /*  GENERATING PRODUCT DESCRIPTION HERE */
    public String generateDescription(String name, String category) {

        String promptDesc = String.format("""
                
                Write a concise and professional product description for an e-commerce listing.
                
                Product Name: %s
                Category: %s
                
                Keep it simple, engaging, and highlight its primary features or benefits.
                Avoid technical jargon and keep it customer-friendly.
                Limit the description to 250 characters maximum.
                
                """, name , category );

        return chatClient.prompt(promptDesc)
                .call()
                .chatResponse()
                .getResult()
                .getOutput()
                .getText();
    }

    /*  GENERATING PRODUCT IMAGE HERE */
    public byte[] generateImage(String name, String category, String description) {

        String imagePrompt = String.format("""
                Generate a highly realistic, professional-grade e-commerce product image.
                
                     Product Details:
                     - Category: %s
                     - Name: '%s'
                     - Description: %s
                
                     Requirements:
                     - Use a clean, minimalistic, white or very light grey background.
                     - Ensure the product is well-lit with soft, natural-looking lighting.
                     - Add realistic shadows and soft reflections to ground the product naturally.
                     - No humans, brand logos, watermarks, or text overlays should be visible.
                     - Showcase the product from its most flattering angle that highlights key features.
                     - Ensure the product occupies a prominent position in the frame, centered or slightly off-centered.
                     - Maintain a high resolution and sharpness, ensuring all textures, colors, and details are clear.
                     - Follow the typical visual style of top e-commerce websites like Amazon, Flipkart, or Shopify.
                     - Make the product appear life-like and professionally photographed in a studio setup.
                     - The final image should look immediately ready for use on an e-commerce website without further editing.
                     """, category, name, description);

        return imageGenerationService.generateImage(imagePrompt);
    }
}
