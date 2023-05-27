package com.example.dogvote.service.dog;

import com.example.dogvote.domain.dog.Dog;
import com.example.dogvote.repository.dog.DogRepository;
import com.example.dogvote.dto.dog.request.DogCreateRequest;
import com.example.dogvote.dto.dog.request.DogVoteRequest;
import com.example.dogvote.dto.dog.response.DogDetailResponse;
import com.example.dogvote.dto.dog.response.DogResponse;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
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
                String fileExtension = filename.substring(filename.indexOf("."));
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
