package senla.dto.course;

import lombok.*;
import senla.dto.order.OrderShortDTO;

import java.math.BigDecimal;
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseShortInfoDTO {
    private String courseName;
    private BigDecimal coursePrice;
    private OrderShortDTO order;
}
