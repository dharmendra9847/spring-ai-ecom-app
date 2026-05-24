package com.tech.springboot.ecom.service;

import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.stabilityai.api.StabilityAiImageOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;

@Service
public class ImageGenerationService {

    private final ImageModel imageModel;

    @Autowired
    public ImageGenerationService(ImageModel imageModel) {
        this.imageModel = imageModel;
    }

    public byte[] generateImage(String imagePrompt) {

        StabilityAiImageOptions imageOptions = StabilityAiImageOptions.builder()
                .N(1)
                .width(1024)
                .height(1024)
                .responseFormat("url")
                .model("stable-image-core")
                .build();

        ImageResponse imageResponse = imageModel.call(new ImagePrompt(imagePrompt, imageOptions));
        String imageUrl = imageResponse.getResult().getOutput().getUrl();

        try {
            return new URL(imageUrl).openStream().readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
