package senla.dto.mapper;

import senla.dto.course.CourseShortInfoDTO;
import senla.dto.order.OrderDTOToEntity;
import senla.dto.order.OrderFullDTO;
import senla.dto.order.OrderShortDTO;
import senla.entities.Order;

public class OrderMapper {
    public static OrderFullDTO convertToFullDTO(Order source){
        return source == null ? null : OrderFullDTO.builder()
                .uuid(source.getUuid())
                .psychologist(PsychologistMapper.convertToFullDTO( source.getPsychologist()))
                .totalPrice(source.getTotalPrice())
                .discounts(source.getDiscounts())
                .build();
    }
    public static OrderShortDTO convertToShortDTO(Order source){
        return source == null ? null : OrderShortDTO.builder()
                .discounts(source.getDiscounts())
                .psychologist(PsychologistMapper.convertToShortDTO( source.getPsychologist()))
                .totalPrice(source.getTotalPrice())
                .build();
    }
    public static Order convertDTOToEntity(OrderDTOToEntity source){
        return source == null ? null : Order.builder()
                .discounts(source.getDiscounts())
                .psychologist(PsychologistMapper.convertDTOToEntity(source.getPsychologist()))
                .totalPrice(source.getTotalPrice())
                .build();

    }
}
