package com.example.dogvote.service.dog;

import com.example.dogvote.domain.dog.DogVoteEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class KafkaProducerService{
    private final KafkaTemplate<String, DogVoteEvent> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, DogVoteEvent> template) {
        this.kafkaTemplate = template;
    }

    public void sendMessage(DogVoteEvent event) {
        kafkaTemplate.send("dog-vote-events", event);
    }
}
