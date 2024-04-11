package senla.mapper;

import senla.dto.review.ReviewDTO;
import senla.dto.review.ReviewDTOToEntity;
import senla.entities.Review;

public class ReviewMapper {
    public static ReviewDTO convertToDto(Review source){
        return source == null ? null : ReviewDTO.builder()
                .commentReviews(source.getCommentReviews())
                .rating(source.getRating())
                .session(SessionMapper.convertToFullDto( source.getSession()))
                .build();

    }
    public static Review CreateReviewDto(ReviewDTOToEntity source){
        return source == null ? null : Review.builder()
                .commentReviews(source.getCommentReviews())
                .rating(source.getRating())
                .session(SessionMapper.createSessionDto(source.getSession()))
                .build();
    }

}
