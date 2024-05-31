package senla.service;


import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;
import senla.config.HibernateConfig;
import senla.config.LiquibaseConfig;
import senla.dto.review.ReviewDTO;
import senla.dto.review.ReviewDTOToEntity;
import senla.entities.Review;
import senla.mapper.ReviewMapper;
import senla.repository.api.ReviewRepository;
import senla.service.impl.ReviewServiceImpl;


import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(
        classes = {HibernateConfig.class, LiquibaseConfig.class},
        loader = AnnotationConfigContextLoader.class
)
@Transactional
public class ReviewServiceTest{
    @InjectMocks
    private ReviewServiceImpl reviewService;

    @Spy
    private ReviewRepository reviewRepository;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findAllTest(){
        Review review = Review.builder()
                .id(1L)
                .commentReviews("test")
                .build();

        Review review1 = Review.builder()
                .id(1L)
                .commentReviews("TEST")
                .build();

        Mockito.when(reviewRepository.findAll()).thenReturn(List.of(review, review1));

        List<ReviewDTO> actual = reviewService.findAll();

        Mockito.verify(reviewRepository, Mockito.times(1)).findAll();
        Assertions.assertFalse(actual.isEmpty());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(2, actual.size());
    }

    @Test
    public void findByIdTest(){
        Review review = Review.builder()
                .id(1L)
                .commentReviews("testt")
                .build();

        Mockito.when(reviewRepository.findById(any(Long.class))).thenReturn(review);

        ReviewDTO expected = ReviewMapper.convertToDto(review);
        ReviewDTO actual = reviewService.findById(review.getId());

        Mockito.verify(reviewRepository, Mockito.times(1)).findById(any());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected.getCommentReviews(), actual.getCommentReviews());
    }

    @Test
    public void saveTest(){
        ReviewDTOToEntity dto = ReviewDTOToEntity.builder()
                .commentReviews("4")
                .build();


        Mockito.doNothing().when(reviewRepository).save(any(Review.class));

        reviewService. save(dto);

        Mockito.verify(reviewRepository, Mockito.times(1)).save(any(Review.class));
    }

    @Test
    public void updateTest(){
        Review review = Review.builder()
                .id(1L)
                .commentReviews("3")

                .build();

        ReviewDTOToEntity dto = new ReviewDTOToEntity();
        dto.setCommentReviews("6");

        Mockito.when(reviewRepository.findById(review.getId())).thenReturn(review);

        reviewService.update(review.getId(), dto);

        Mockito.verify(reviewRepository, Mockito.times(1)).save(any(Review.class));
    }

    @Test
    public void deleteByIdTest(){
        Long id = 1L;

        reviewService.deleteById(id);
        Mockito.verify(reviewRepository, Mockito.times(1)).deleteById(id);
    }
}
