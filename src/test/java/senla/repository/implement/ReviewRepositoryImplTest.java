package senla.repository.implement;

import jakarta.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;
import senla.config.HibernateConfig;
import senla.config.LiquibaseConfig;
import senla.entities.Review;
import senla.entities.Role;
import senla.entities.Session;
import senla.entities.User;
import senla.enums.RoleName;
import senla.repository.api.ReviewRepository;
import senla.repository.api.UserRepository;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {HibernateConfig.class, LiquibaseConfig.class},
        loader = AnnotationConfigContextLoader.class
)
@Transactional
public class ReviewRepositoryImplTest {
    @Resource
    private ReviewRepository reviewRepository;

    @Test
    public void findAllWithFetch() {
        Review review = Review.builder()
                .rating(2)
                .build();

        reviewRepository.save(review);

        List<Review> reviews = reviewRepository.findAllWithFetch(review.getRating());

        assertNotNull(reviews);
        assertFalse(reviews.isEmpty());

        reviews.forEach(x -> assertEquals(review.getRating(), x.getRating()));
    }

    @Test
    public void findAllWithJoinFetch() {
        Review review = Review.builder()
                .commentReviews("TEST")
                .build();

        reviewRepository.save(review);

        List<Review> reviews = reviewRepository.findAllWithJoinFetch(review.getCommentReviews());

        assertNotNull(reviews);
        assertFalse(reviews.isEmpty());
    }

    @Test
    public void findAllWithDetails() {
        Review review = Review.builder()
                .build();

        reviewRepository.save(review);

        List<Review> reviews = reviewRepository.findAllWithDetails();

        assertNotNull(reviews);
        assertFalse(reviews.isEmpty());
    }
}