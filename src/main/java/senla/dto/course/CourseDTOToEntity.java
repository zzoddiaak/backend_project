package senla.dto.course;

import lombok.*;
import senla.dto.order.OrderDTOToEntity;

import java.math.BigDecimal;
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTOToEntity {
    private String courseName;
    private BigDecimal coursePrice;
    private OrderDTOToEntity order;
}
