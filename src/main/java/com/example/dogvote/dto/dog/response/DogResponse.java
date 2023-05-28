package com.example.dogvote.dto.dog.response;

import com.example.dogvote.domain.dog.Dog;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DogResponse {
    private long id;
    private String name;
    private String photoUrl;
    private long voteCount;

    public DogResponse(long id, String name, String photoUrl, long voteCount) {
        this.id = id;
        this.name = name;
        this.photoUrl = photoUrl;
        this.voteCount = voteCount;
    }

    public DogResponse(Dog dog) {
        this.id = dog.getId();
        this.name = dog.getName();
        this.photoUrl = dog.getPhotoUrl();
        this.voteCount = dog.getVoteCount();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public long getVoteCount() {
        return voteCount;
    }
}
