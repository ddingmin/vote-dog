package com.example.dogvote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication()
public class DogVoteApplication {

    public static void main(String[] args) {
        SpringApplication.run(DogVoteApplication.class, args);
    }

}
