package senla.dto.order;

import java.math.BigDecimal;
import lombok.*;
import senla.dto.psychologist.PsychologistShortDTO;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderShortDTO {
    private BigDecimal totalPrice;
    private BigDecimal discounts;
    private PsychologistShortDTO psychologist;
}
