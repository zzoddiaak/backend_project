package senla.dto.mapper;

import senla.dto.PaymentStatus.PaymentStatusDTO;
import senla.dto.PaymentStatus.PaymentStatusDTOToEntity;
import senla.dto.payment.PaymentFullInfoDTO;
import senla.entities.PaymentStatus;

public class PaymentStatusMapper {
    public static PaymentStatusDTO convertEntityToDTO(PaymentStatus source){
        return source == null ? null : PaymentStatusDTO.builder()
                .paymentStatus(source.getStatusName())
                .build();

    }
    public static PaymentStatus convertDTOToEntity(PaymentStatusDTOToEntity source){
        return source == null ? null : PaymentStatus.builder()
                .statusName(source.getPaymentStatus())
                .build();

    }
}
