package senla.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import senla.dto.PaymentStatus.PaymentStatusDTO;
import senla.dto.PaymentStatus.PaymentStatusDTOToEntity;
import senla.enums.StatusPayment;
import senla.mapper.PaymentStatusMapper;
import senla.entities.PaymentStatus;
import senla.repository.api.PaymentStatusRepository;
import senla.service.PaymentStatusService;

import java.util.List;
@Transactional(readOnly = true)
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
        PaymentStatus paymentStatus = paymentStatusRepository.findById(uuid);
        return paymentStatus != null ? PaymentStatusMapper.convertToDto(paymentStatus) : null;
    }

    @Override
    public boolean save(PaymentStatusDTOToEntity object) {
        PaymentStatus paymentStatus = PaymentStatusMapper.createPaymentStatusDto(object);
        paymentStatusRepository.save(paymentStatus);
        return paymentStatus.getId() != null;
    }

    @Override
    public boolean update(long uuid, PaymentStatusDTOToEntity updateDTO) {
        PaymentStatus paymentStatus = paymentStatusRepository.findById(uuid);
        if (paymentStatus != null) {
            if (updateDTO.getStatusPayment() != null) {
                paymentStatus.setStatus(StatusPayment.valueOf(String.valueOf(updateDTO.getStatusPayment())));
            }
            paymentStatusRepository.save(paymentStatus);
            return true;
        }
        return false;
    }

    @Override
    public void deleteById(long uuid) {
        paymentStatusRepository.deleteById(uuid);
    }
}

