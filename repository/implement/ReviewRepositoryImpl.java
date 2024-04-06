package senla.repository.implement;

import org.springframework.stereotype.Repository;
import senla.entities.Review;
import senla.repository.AbstractRepository;
import senla.repository.iface.ReviewRepository;
@Repository

public class ReviewRepositoryImpl extends AbstractRepository<Review> implements ReviewRepository {
    public ReviewRepositoryImpl(){
        save(Review.builder()
                .commentReviews("Nice")
                .rating(32)
                .build());
        save(Review.builder()
                .commentReviews("Ne nice")
                .rating(62)
                .build());
    }
}
