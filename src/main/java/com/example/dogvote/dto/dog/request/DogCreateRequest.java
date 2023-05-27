package com.example.dogvote.dto.dog.request;

import org.springframework.web.multipart.MultipartFile;

public class DogCreateRequest {
    private String name;
    private String description;

    public DogCreateRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
}
