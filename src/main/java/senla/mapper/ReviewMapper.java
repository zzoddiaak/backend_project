package senla.mapper;

import senla.dto.review.ReviewDTO;
import senla.dto.review.ReviewDTOToEntity;
import senla.entities.Review;

public class ReviewMapper {
    public static ReviewDTO convertToDto(Review source){
        if (source == null) {
            return null;
        }

        Integer rating = source.getRating();

        Integer ratingValue = rating != null ? rating : 0;

        return ReviewDTO.builder()
                .commentReviews(source.getCommentReviews())
                .rating(ratingValue.intValue())
                .session(SessionMapper.convertToFullDto(source.getSession()))
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
