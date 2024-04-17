package senla.servise.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import senla.mapper.ReviewMapper;
import senla.dto.review.ReviewDTO;
import senla.dto.review.ReviewDTOToEntity;
import senla.entities.Review;
import senla.repository.iface.ReviewRepository;
import senla.servise.ReviewService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    @Override
    public List<ReviewDTO> findAll() {
        return reviewRepository.findAll().stream()
                .map(ReviewMapper::convertToDto)
                .toList();
    }

    @Override
    public ReviewDTO findById(long uuid) {
        Review review = reviewRepository.findById(uuid);
        return review != null ? ReviewMapper.convertToDto(review) : null;
    }

    @Override
    public boolean save(ReviewDTOToEntity object) {
        Review review = ReviewMapper.CreateReviewDto(object);
        reviewRepository.save(review);
        return review.getId() != null;
    }

    @Override
    public boolean update(long uuid, ReviewDTOToEntity updateDTO) {
        Review review = reviewRepository.findById(uuid);
        if (review != null) {
            if (updateDTO.getCommentReviews() != null) {
                review.setCommentReviews(updateDTO.getCommentReviews());
            }
            if (updateDTO.getRating() != null) {
                review.setRating(updateDTO.getRating());
            }
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public void deleteById(long uuid){
        reviewRepository.deleteById(uuid);
    }
}

