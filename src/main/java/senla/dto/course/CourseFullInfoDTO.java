package senla.dto.course;

import java.math.BigDecimal;
import lombok.*;

import senla.dto.order.OrderFullDTO;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseFullInfoDTO {
    private long uuid;
    private String courseName;
    private BigDecimal coursePrice;
    private OrderFullDTO order;
}
