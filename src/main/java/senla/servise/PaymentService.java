package senla.servise;


import senla.dto.payment.PaymentDTOToEntity;
import senla.dto.payment.PaymentShortInfoDTO;

import java.util.List;

public interface PaymentService {
    List<PaymentShortInfoDTO> findAll();
    PaymentShortInfoDTO findById(long uuid);
    void save(PaymentDTOToEntity object);
    void update(long uuid, PaymentDTOToEntity updateDTO);

    void deleteById(long uuid);
}
