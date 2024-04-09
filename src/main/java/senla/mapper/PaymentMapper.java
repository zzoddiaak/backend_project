package senla.mapper;

import senla.dto.payment.PaymentDTOToEntity;
import senla.dto.payment.PaymentFullInfoDTO;
import senla.dto.payment.PaymentShortInfoDTO;
import senla.entities.Payment;

public class PaymentMapper {
    public static PaymentFullInfoDTO convertToFullDto(Payment source){
        return source == null ? null : PaymentFullInfoDTO.builder()
                .user(UserMapper.convertToFullDto( source.getUser()))
                .url(source.getUrl())
                .paymentStatus(PaymentStatusMapper.convertToDto(source.getPaymentStatus()) )
                .session(SessionMapper.convertToFullDto( source.getSession()))
                .order(OrderMapper.convertToFullDto(source.getOrder()))
                .build();

    }
    public static PaymentShortInfoDTO convertToShortDto(Payment source) {
        return source == null ? null : PaymentShortInfoDTO.builder()
                .order(OrderMapper.convertToShortDto(source.getOrder()))
                .session(SessionMapper.convertToShortDto(source.getSession()))
                .paymentStatus(PaymentStatusMapper.convertToDto(source.getPaymentStatus()) )
                .url(source.getUrl())
                .user(UserMapper.convertToShortDto( source.getUser()))
                .build();
    }
    public static Payment createPaymentDto(PaymentDTOToEntity source){
        return source == null ? null : Payment.builder()
                .order(OrderMapper.createOrderDto(source.getOrder()))
                .session(SessionMapper.createSessionDto(source.getSession()))
                .paymentStatus(PaymentStatusMapper.createPaymentStatusDto(source.getPaymentStatus()))
                .url(source.getUrl())
                .user(UserMapper.createUserDto(source.getUser()))
                .build();


    }
}
