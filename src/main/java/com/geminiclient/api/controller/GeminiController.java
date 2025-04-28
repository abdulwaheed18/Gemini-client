package com.geminiclient.api.controller;

import com.geminiclient.api.model.GeminiImageRequest;
import com.geminiclient.api.model.GeminiRequest;
import com.geminiclient.api.model.GeminiResponse;
import com.geminiclient.api.service.GeminiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gemini")
@RequiredArgsConstructor
public class GeminiController {

    private final GeminiService geminiService;

    @PostMapping("/text")
    public ResponseEntity<GeminiResponse> generateText(@RequestBody GeminiRequest request) {
        return ResponseEntity.ok(geminiService.generateTextContent(request));
    }

    @PostMapping("/image")
    public ResponseEntity<GeminiResponse> generateImage(@RequestBody GeminiImageRequest request) {
        return ResponseEntity.ok(geminiService.generateImageContent(request));
    }
}