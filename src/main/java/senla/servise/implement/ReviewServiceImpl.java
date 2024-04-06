package senla.servise.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import senla.dto.mapper.ReviewMapper;
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
    public List<ReviewDTO> findAll(){
        return reviewRepository.findAll().stream()
                .map(ReviewMapper::convertToDTO)
                .toList();

    }
    @Override
    public ReviewDTO findById(long uuid){
        return ReviewMapper.convertToDTO(reviewRepository.findById(uuid));
    }
    @Override
    public void save(ReviewDTOToEntity object){
        reviewRepository.save(ReviewMapper.convertDTOToEntity(object));
    }
    @Override
    public void update(long uuid, ReviewDTOToEntity updateDTO){
        Review review = reviewRepository.findById(uuid);
        if (!updateDTO.getCommentReviews().isEmpty()) review.setCommentReviews(updateDTO.getCommentReviews());
        if (updateDTO.getRating() != null) review.setRating(updateDTO.getRating());

    }
    @Override
    public void deleteById(long uuid){
        reviewRepository.deleteById(uuid);
    }
}
