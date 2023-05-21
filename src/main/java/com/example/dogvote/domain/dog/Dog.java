package com.example.dogvote.domain.dog;

public class Dog {
    private String name;
    private String description;
    private String photoUrl;



    private long voteCount;

    public Dog(String name, String description, String photoUrl) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다.", name));
        }
        this.name = name;
        this.description = description;
        this.photoUrl = photoUrl;
        voteCount = 0;
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
