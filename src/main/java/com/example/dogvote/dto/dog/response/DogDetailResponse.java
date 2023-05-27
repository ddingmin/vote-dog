package com.example.dogvote.dto.dog.response;

import com.example.dogvote.domain.dog.Dog;

public class DogDetailResponse {
    private long id;
    private String name;
    private String description;
    private String photoUrl;
    private long voteCount;

    public DogDetailResponse(long id, String name, String description, String photoUrl, long voteCount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.photoUrl = photoUrl;
        this.voteCount = voteCount;
    }

    public DogDetailResponse(Dog dog) {
        this.id = dog.getId();
        this.name = dog.getName();
        this.description = dog.getDescription();
        this.photoUrl = dog.getPhotoUrl();
        this.voteCount = dog.getVoteCount();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public long getVoteCount() {
        return voteCount;
    }
}
