package senla.servise.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import senla.dto.PaymentStatus.PaymentStatusDTO;
import senla.dto.PaymentStatus.PaymentStatusDTOToEntity;
import senla.enums.StatusPayment;
import senla.mapper.PaymentStatusMapper;
import senla.entities.PaymentStatus;
import senla.repository.iface.PaymentStatusRepository;
import senla.servise.PaymentStatusService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentStatusServiceImpl implements PaymentStatusService {
    private final PaymentStatusRepository paymentStatusRepository;

    @Override
    public List<PaymentStatusDTO> findAll() {
        return paymentStatusRepository.findAll().stream()
                .map(PaymentStatusMapper::convertToDto)
                .toList();
    }

    @Override
    public PaymentStatusDTO findById(long uuid) {
        return PaymentStatusMapper.convertToDto(paymentStatusRepository.findById(uuid));
    }

    @Override
    public void save(PaymentStatusDTOToEntity object) {
        paymentStatusRepository.save(PaymentStatusMapper.createPaymentStatusDto(object));
    }

    @Override
    public void update(long uuid, PaymentStatusDTOToEntity updateDTO) {
        PaymentStatus paymentStatus = paymentStatusRepository.findById(uuid);
        if (updateDTO.getStatusPayment() != null) {
            paymentStatus.setStatus(StatusPayment.valueOf(String.valueOf(updateDTO.getStatusPayment())));
        }
        paymentStatusRepository.save(paymentStatus);
    }

    @Override
    public void deleteById(long uuid) {
        paymentStatusRepository.deleteById(uuid);
    }
}
