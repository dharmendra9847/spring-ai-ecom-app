package com.tech.springboot.ecom.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OllamaConfig {

    /**
     * Configure ChatClient for Ollama AI model.
     */
    @Bean
    public ChatClient chatClient(OllamaChatModel chatModel) {

        return ChatClient.builder(chatModel)
                .build();
    }
}
