package senla.entities;
import lombok.*;

import java.math.BigDecimal;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course extends Entity{


    private String courseName;

    private BigDecimal coursePrice;

    private Order order;

}
