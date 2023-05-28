package com.example.dogvote.domain.dog;

import java.io.Serializable;

public class DogVoteEvent implements Serializable {
    private Long id;
    private boolean vote;

    public DogVoteEvent(long id, boolean vote) {
        this.id = id;
        this.vote = vote;
    }

    public Long getId() {
        return id;
    }

    public boolean isVote() {
        return vote;
    }

}
