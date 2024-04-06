package senla.servise;

import senla.dto.review.ReviewDTO;
import senla.dto.review.ReviewDTOToEntity;

import java.util.List;

public interface ReviewService {
    List<ReviewDTO> findAll();
    ReviewDTO findById(long uuid);
    void save(ReviewDTOToEntity object);
    void deleteById(long uuid);
}