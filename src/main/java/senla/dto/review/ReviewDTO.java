package senla.dto.review;
import lombok.*;
import senla.dto.session.SessionDTO;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private SessionDTO session;
    private int rating;
    private String commentReviews;
}
