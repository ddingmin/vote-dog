package com.example.dogvote.repository.dog;

import com.example.dogvote.domain.dog.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogRepository extends JpaRepository<Dog, Long> { // 엔티티와 PK인 Long
}
