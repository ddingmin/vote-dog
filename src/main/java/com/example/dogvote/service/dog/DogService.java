package com.example.dogvote.service.dog;

import com.example.dogvote.dto.dog.request.DogCreateRequest;
import com.example.dogvote.dto.dog.request.DogVoteRequest;
import com.example.dogvote.dto.dog.response.DogDetailResponse;
import com.example.dogvote.dto.dog.response.DogResponse;
import com.example.dogvote.repository.dog.DogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DogService {

    private final DogRepository dogRepository;

    public DogService(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public void saveDog(DogCreateRequest request) {
        dogRepository.addDog(request);
    }

    public List<DogResponse> getDogs(int page, int size) {
        return dogRepository.getDogs();
    }

    public DogDetailResponse getDetailDog(long id) {
        if (dogRepository.isDogNotExist(id)) {
            throw new IllegalArgumentException();
        }
        return dogRepository.getDetailDog(id);
    }

    public void voteDog(DogVoteRequest request) {
        if (dogRepository.isDogNotExist(request.getId())) {
            throw new IllegalArgumentException();
        }

        dogRepository.updateDogVoteCount(request.getId(), true);
    }
}
