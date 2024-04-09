package senla.mapper;

import senla.dto.PaymentStatus.PaymentStatusDTO;
import senla.dto.PaymentStatus.PaymentStatusDTOToEntity;
import senla.entities.PaymentStatus;

public class PaymentStatusMapper {
    public static PaymentStatusDTO convertToDto(PaymentStatus source){
        return source == null ? null : PaymentStatusDTO.builder()
                .statusPayment(source.getStatus())
                .build();
    }

    public static PaymentStatus createPaymentStatusDto(PaymentStatusDTOToEntity source){
        return source == null ? null : PaymentStatus.builder()
                .status(source.getStatusPayment())
                .build();
    }


}
