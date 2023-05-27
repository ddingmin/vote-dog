package com.example.dogvote.dto.dog.request;

import org.springframework.web.multipart.MultipartFile;

public class DogCreateRequest {
    private String name;
    private String description;
    private MultipartFile file;

    public DogCreateRequest(String name, String description, MultipartFile file) {
        this.name = name;
        this.description = description;
        this.file = file;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public MultipartFile getFile() {
        return file;
    }
}
