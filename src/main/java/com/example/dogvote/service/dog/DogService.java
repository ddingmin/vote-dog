package com.example.dogvote.service.dog;

import com.example.dogvote.domain.dog.Dog;
import com.example.dogvote.repository.dog.DogRepository;
import com.example.dogvote.dto.dog.request.DogCreateRequest;
import com.example.dogvote.dto.dog.request.DogVoteRequest;
import com.example.dogvote.dto.dog.response.DogDetailResponse;
import com.example.dogvote.dto.dog.response.DogResponse;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DogService {
    private final DogRepository dogRepository;
    private final ClassPathResource resource = new ClassPathResource("/images/");


    public DogService(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public void saveDog(DogCreateRequest request, MultipartFile file) {
        Dog dog = new Dog(request.getName(), request.getDescription());
        dogRepository.save(dog);
        if (!file.isEmpty()) {
            try {
                String path = resource.getPath();
                String filename = dog.getId() + file.getContentType();
                file.transferTo(new File(path + filename));
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public List<DogResponse> getDogs() {
        return dogRepository.findAll().stream()
                .map(DogResponse::new)
                .collect(Collectors.toList());
    }

    public DogDetailResponse getDetailDog(long id){
        Dog dog = dogRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        return new DogDetailResponse(dog);
    }

    public void voteDog(DogVoteRequest request) {
        Dog dog = dogRepository.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new);
        dog.addVoteCount(1);
        dogRepository.save(dog);
    }
}
