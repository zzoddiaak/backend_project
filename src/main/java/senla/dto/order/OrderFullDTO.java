package senla.dto.order;

import lombok.*;
import senla.dto.psychologist.PsychologistFullDTO;

import java.math.BigDecimal;
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderFullDTO {
    private long uuid;
    private BigDecimal totalPrice;
    private BigDecimal discounts;
    private PsychologistFullDTO psychologist;
}
