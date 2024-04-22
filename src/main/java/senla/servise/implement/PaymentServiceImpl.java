package senla.servise.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import senla.mapper.PaymentMapper;
import senla.dto.payment.PaymentDTOToEntity;
import senla.dto.payment.PaymentShortInfoDTO;
import senla.entities.Payment;
import senla.repository.api.PaymentRepository;
import senla.servise.PaymentService;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    @Override
    public List<PaymentShortInfoDTO> findAll() {
        return paymentRepository.findAll().stream()
                .map(PaymentMapper::convertToShortDto)
                .toList();
    }

    @Override
    public PaymentShortInfoDTO findById(long uuid) {
        Payment payment = paymentRepository.findById(uuid);
        return payment != null ? PaymentMapper.convertToShortDto(payment) : null;
    }

    @Override
    public boolean save(PaymentDTOToEntity object) {
        Payment payment = PaymentMapper.createPaymentDto(object);
        paymentRepository.save(payment);
        return payment.getId() != null;
    }

    @Override
    public boolean update(long uuid, PaymentDTOToEntity updateDTO) {
        Payment payment = paymentRepository.findById(uuid);
        if (payment != null) {
            if (!updateDTO.getUrl().isEmpty()) payment.setUrl(updateDTO.getUrl());
            paymentRepository.save(payment);
            return true;
        }
        return false;
    }


    @Override
    public void deleteById(long uuid){
        paymentRepository.deleteById(uuid);
    }
}
