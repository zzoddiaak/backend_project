package senla.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import senla.dto.review.ReviewDTOToEntity;
import senla.dto.session.SessionDTOToEntity;
import senla.servise.ReviewService;
import senla.servise.SessionService;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final ObjectMapper objectMapper;

    @GetMapping
    @SneakyThrows
    public String findAll(){return objectMapper.writeValueAsString((reviewService.findAll()));
    }
    @GetMapping("/{uuid}")
    @SneakyThrows
    public String findById(@PathVariable Long uuid){
        return objectMapper.writeValueAsString(reviewService.findById(uuid));
    }
    @PostMapping
    @SneakyThrows
    public void save(@RequestBody String reviewDTO){
        reviewService.save(objectMapper.readValue(reviewDTO, ReviewDTOToEntity.class));
    }
    @DeleteMapping("/id")
    public void deleteById(@PathVariable Long uuid){reviewService.deleteById(uuid);}
}
