package senla.dto.mapper;

import senla.dto.review.ReviewDTO;
import senla.dto.review.ReviewDTOToEntity;
import senla.entities.Review;

public class ReviewMapper {
    public static ReviewDTO convertToDTO(Review source){
        return source == null ? null : ReviewDTO.builder()
                .commentReviews(source.getCommentReviews())
                .rating(source.getRating())
                .session(SessionMapper.convertToFullDTO( source.getSession()))
                .build();

    }
    public static Review convertDTOToEntity(ReviewDTOToEntity source){
        return source == null ? null : Review.builder()
                .commentReviews(source.getCommentReviews())
                .rating(source.getRating())
                .session(SessionMapper.convertDTOToEntity(source.getSession()))
                .build();
    }

}
