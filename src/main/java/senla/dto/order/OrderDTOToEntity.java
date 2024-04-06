package senla.dto.order;

import lombok.*;
import senla.dto.psychologist.PsychologistDTOToEntity;
import senla.dto.psychologist.PsychologistShortDTO;

import java.math.BigDecimal;
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTOToEntity {
    private BigDecimal totalPrice;
    private BigDecimal discounts;
    private PsychologistDTOToEntity psychologist;
}
