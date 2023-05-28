package com.example.dogvote.service.dog;

import com.example.dogvote.domain.dog.Dog;
import com.example.dogvote.dto.dog.request.DogCreateRequest;
import com.example.dogvote.dto.dog.request.DogVoteRequest;
import com.example.dogvote.dto.dog.response.DogDetailResponse;
import com.example.dogvote.dto.dog.response.DogResponse;
import com.example.dogvote.repository.dog.DogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DogService {
    private final DogRepository dogRepository;
    private final ClassPathResource resource = new ClassPathResource("/images/");


    public DogService(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public void saveDog(DogCreateRequest request) {
        if (!request.getFile().isEmpty()) {
            try {
                Dog dog = new Dog(request.getName(), request.getDescription());

                String filename = request.getFile().getOriginalFilename();
                String fileExtension = Objects.requireNonNull(filename).substring(filename.indexOf("."));
                String path = dog.getName() + (int) (Math.random() * 100000) + fileExtension;
                request.getFile().transferTo(new File(path));

                dog.setPhotoUrl(path);
                dogRepository.save(dog);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public List<DogResponse> getDogs(int page, int size) {
        return dogRepository.findAll(PageRequest.of(page, size)).stream()
                .map(DogResponse::new)
                .collect(Collectors.toList());
    }

    @Cacheable(value = "Dog", key = "#id", cacheManager = "dogCacheManager")
    public DogDetailResponse getDetailDog(long id){
        Dog dog = dogRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        return new DogDetailResponse(dog);
    }

    @CachePut(value = "Dog", key = "#request.getId()", cacheManager = "dogCacheManager")
    public DogDetailResponse voteDog(DogVoteRequest request) {
        Dog dog = dogRepository.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new);
        if (request.getVote()){
            dog.addVoteCount(1);
        }
        else {
            dog.addVoteCount(-1);
        }
        dogRepository.save(dog);
        return new DogDetailResponse(dog);
    }
}
