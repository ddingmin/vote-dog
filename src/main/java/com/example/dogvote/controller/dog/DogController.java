package com.example.dogvote.controller.dog;

import com.example.dogvote.dto.dog.request.DogCreateRequest;
import com.example.dogvote.dto.dog.request.DogVoteRequest;
import com.example.dogvote.dto.dog.response.DogDetailResponse;
import com.example.dogvote.dto.dog.response.DogResponse;
import com.example.dogvote.service.dog.DogService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class DogController {

    private final DogService dogService;


    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    @PostMapping("/dog")
    public void saveDog(DogCreateRequest request) {
        dogService.saveDog(request);
    }

    @GetMapping("/dog")
    public List<DogResponse> getDogs(@RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "size", defaultValue = "10") int size) {
        return dogService.getDogs(page, size);
    }

    @GetMapping("/dog/detail")
    public DogDetailResponse getDetailDog(@RequestParam long id) {
        return dogService.getDetailDog(id);
    }

    @PatchMapping("/dog/vote")
    public void voteDog(@RequestBody DogVoteRequest request) {
        dogService.voteDog(request);
    }

    @GetMapping("/test")
    public String getIp(HttpServletRequest request) {
        String clientIP = request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");
        return "Client IP: " + clientIP + ", User-Agent: " + userAgent;
    }
}
