package com.geminiclient.api.service;

import com.geminiclient.api.config.ImageUtils;
import com.geminiclient.api.model.GeminiImageRequest;
import com.geminiclient.api.model.GeminiRequest;
import com.geminiclient.api.model.GeminiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class GeminiService {

    private final RestTemplate restTemplate;

    @Value("${gemini.api.key}")
    private String apiKey;

    private static final String TEXT_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent";
    private static final String IMAGE_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash-exp-image-generation:generateContent";

    public GeminiResponse generateTextContent(GeminiRequest request) {
        return invokeGeminiApi(TEXT_URL, request);
    }

    public GeminiResponse generateImageContent(GeminiImageRequest request) {
        return invokeGeminiApi(IMAGE_URL, request);
    }

    private GeminiResponse invokeGeminiApi(String url, Object request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> entity = new HttpEntity<>(request, headers);

        ResponseEntity<GeminiResponse> response = restTemplate.exchange(
                url + "?key=" + apiKey,
                HttpMethod.POST,
                entity,
                GeminiResponse.class
        );

        GeminiResponse body = response.getBody();
        if (body != null && !body.getCandidates().isEmpty()) {
            GeminiResponse.Part part = body.getCandidates().get(0).getContent().getParts().get(0);
            if (part.getText() != null) {
                System.out.println("Generated Text:\n" + part.getText());
            } else if (part.getInlineData() != null) {
                try {
                   // ImageUtils.saveBase64Image(part.getInlineData().getData(), "D:\\GenAI\\Workspace\\gemini-client\\generated_image.png");
                    ImageUtils.saveBase64Image(part.getInlineData().getData(), "generated_image.png");
                    System.out.println("Image saved successfully at ./images/generated_image_<timestamp>.png");
                } catch (IOException e) {
                    System.err.println("Failed to save image: " + e.getMessage());
                }
            }
        }

        return body;
    }
}