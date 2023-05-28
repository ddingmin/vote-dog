package com.example.dogvote.domain.dog;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor    // 기본 생성자 추가
@AllArgsConstructor
//@RedisHash("dog")
public class Dog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;
    @Column(nullable = false)
    private String name;
    private String description;
    @Column(name = "photo_url")
    private String photoUrl;
    private long voteCount;

    public Dog(String name, String description) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다.", name));
        }
        this.name = name;
        if (description == null || description.isBlank()) {
            this.description = "-";
        }
        else {
            this.description = description;
        }
        this.photoUrl = "/" + this.id;
        this.voteCount = 0;
    }

    public Long getId() {
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

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void addVoteCount(int count) {
        this.voteCount = this.voteCount + count;
    }
}
