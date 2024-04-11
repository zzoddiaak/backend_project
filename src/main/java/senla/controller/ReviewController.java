package senla.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import senla.dto.review.ReviewDTOToEntity;
import senla.servise.ReviewService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final ObjectMapper objectMapper;

    @GetMapping
    public String findAll() {
        return serialize(reviewService.findAll());
    }

    @GetMapping("/{uuid}")
    public String findById(@PathVariable Long uuid) {
        return serialize(reviewService.findById(uuid));
    }

    @PostMapping
    public void save(@RequestBody String reviewDTO) {
        try {
            reviewService.save(deserializeReviewDTO(reviewDTO));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody String updateDTO) {
        try {
            reviewService.update(id, deserializeReviewDTO(updateDTO));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        reviewService.deleteById(id);
    }

    private String serialize(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private ReviewDTOToEntity deserializeReviewDTO(String reviewDTO) throws IOException {
        return objectMapper.readValue(reviewDTO, ReviewDTOToEntity.class);
    }
}
