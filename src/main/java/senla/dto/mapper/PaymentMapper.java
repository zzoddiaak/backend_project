package senla.dto.mapper;

import senla.dto.payment.PaymentDTOToEntity;
import senla.dto.payment.PaymentFullInfoDTO;
import senla.dto.payment.PaymentShortInfoDTO;
import senla.entities.Payment;
import senla.entities.PaymentStatus;

public class PaymentMapper {
    public static PaymentFullInfoDTO convertToFullDTO(Payment source){
        return source == null ? null : PaymentFullInfoDTO.builder()
                .user(UserMapper.convertToFullDTO( source.getUser()))
                .uuid(source.getUuid())
                .url(source.getUrl())
                .paymentStatus(PaymentStatusMapper.convertEntityToDTO(source.getPaymentStatus()) )
                .session(SessionMapper.convertToFullDTO( source.getSession()))
                .order(OrderMapper.convertToFullDTO(source.getOrder()))
                .build();

    }
    public static PaymentShortInfoDTO convertToShortDTO(Payment source) {
        return source == null ? null : PaymentShortInfoDTO.builder()
                .order(OrderMapper.convertToShortDTO(source.getOrder()))
                .session(SessionMapper.convertToShortDTO(source.getSession()))
                .paymentStatus(PaymentStatusMapper.convertEntityToDTO(source.getPaymentStatus()) )
                .url(source.getUrl())
                .user(UserMapper.convertToShortDTO( source.getUser()))
                .build();
    }
    public static Payment convertDTOToEntity(PaymentDTOToEntity source){
        return source == null ? null : Payment.builder()
                .order(OrderMapper.convertDTOToEntity(source.getOrder()))
                .session(SessionMapper.convertDTOToEntity(source.getSession()))
                .paymentStatus(PaymentStatusMapper.convertDTOToEntity(source.getPaymentStatus()))
                .url(source.getUrl())
                .user(UserMapper.convertDTOToEntity(source.getUser()))
                .build();


    }
}
