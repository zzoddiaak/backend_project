package senla.entities;
import lombok.*;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review extends Entity{

    private Session session;

    private Integer rating;

    private String commentReviews;
}
