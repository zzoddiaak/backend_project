package senla.mapper;

import senla.dto.order.OrderDTOToEntity;
import senla.dto.order.OrderFullDTO;
import senla.dto.order.OrderShortDTO;
import senla.entities.Order;

public class OrderMapper {
    public static OrderFullDTO convertToFullDto(Order source){
        return source == null ? null : OrderFullDTO.builder()
                .psychologist(PsychologistMapper.convertToFullDto( source.getPsychologist()))
                .totalPrice(source.getTotalPrice())
                .discounts(source.getDiscounts())
                .build();
    }
    public static OrderShortDTO convertToShortDto(Order source){
        return source == null ? null : OrderShortDTO.builder()
                .discounts(source.getDiscounts())
                .psychologist(PsychologistMapper.convertToShortDto( source.getPsychologist()))
                .totalPrice(source.getTotalPrice())
                .build();
    }
    public static Order createOrderDto(OrderDTOToEntity source){
        return source == null ? null : Order.builder()
                .discounts(source.getDiscounts())
                .psychologist(PsychologistMapper.createPsychologistDto(source.getPsychologist()))
                .totalPrice(source.getTotalPrice())
                .build();

    }
}
