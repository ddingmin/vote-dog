package com.example.dogvote.dto.dog.request;

import lombok.Data;

@Data
public class DogVoteRequest {
    private Long id;
    private Boolean isVote;

    public long getId() {
        return id;
    }

    public Boolean getVote() {
        return isVote;
    }
}
