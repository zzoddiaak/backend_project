package senla.entities;

import lombok.*;

import java.math.BigDecimal;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Psychologist extends Entity{

    private User user;

    private int experience;

    private BigDecimal hourlyRate;

    private int ratingValue;

}
