package com.example.dogvote.dto.dog.request;

public class DogCreateRequest {
    private String name;
    private String description;
    private String photoUrl;

    public String getName() {
        return name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getDescription() {
        return description;
    }
}
