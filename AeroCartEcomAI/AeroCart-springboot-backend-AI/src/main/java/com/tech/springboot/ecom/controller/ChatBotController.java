package com.tech.springboot.ecom.controller;

import com.tech.springboot.ecom.service.ChatBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin
public class ChatBotController {

    private final ChatBotService chatBotService;

    @Autowired
    public ChatBotController(ChatBotService chatBotService) {
        this.chatBotService = chatBotService;
    }

    @GetMapping("/ask")
    public ResponseEntity<String> chatBot(@RequestParam String message) {

        String response = chatBotService.getResponse(message);

        return ResponseEntity.ok(response);
    }
}
