package com.example.dogvote.service.dog;

import com.example.dogvote.domain.dog.Dog;
import com.example.dogvote.domain.dog.DogVoteEvent;
import com.example.dogvote.repository.dog.DogRepository;
import org.springframework.kafka.annotation.KafkaListener;

//@Component
public class DogVoteEventConsumer {
    private final DogRepository dogRepository;

    public DogVoteEventConsumer(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    @KafkaListener(topics = "dog-vote-events")
    public void consumeVoteEvent(DogVoteEvent event) {
        Dog dog = dogRepository.findById(event.getId())
                .orElseThrow(IllegalArgumentException::new);

        // 투표 요청이라면 +1
        if (event.isVote()) {
            dog.addVoteCount(1);
            dogRepository.save(dog);
        }
        else {
            dog.addVoteCount(-1);
            dogRepository.save(dog);
        }
    }
}
