package senla.dto.review;

import lombok.*;
import senla.dto.session.SessionDTO;
import senla.dto.session.SessionDTOToEntity;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTOToEntity {
    private SessionDTOToEntity session;
    private int rating;
    private String commentReviews;
}
