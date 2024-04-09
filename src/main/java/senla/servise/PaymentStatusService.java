package senla.servise;

import senla.dto.PaymentStatus.PaymentStatusDTO;
import senla.dto.PaymentStatus.PaymentStatusDTOToEntity;
import java.util.List;

public interface PaymentStatusService {
    List<PaymentStatusDTO> findAll();
    PaymentStatusDTO findById(long uuid);
    void save(PaymentStatusDTOToEntity object);
    void update(long uuid, PaymentStatusDTOToEntity updateDTO);

    void deleteById(long uuid);
}
