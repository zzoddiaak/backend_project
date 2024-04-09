package senla.entities;
import lombok.*;

import java.math.BigDecimal;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order extends Entity{

    private BigDecimal totalPrice;

    private BigDecimal discounts;

    private Psychologist psychologist;

}
