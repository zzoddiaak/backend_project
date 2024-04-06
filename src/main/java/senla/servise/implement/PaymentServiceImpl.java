package senla.servise.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import senla.dto.mapper.PaymentMapper;
import senla.dto.payment.PaymentDTOToEntity;
import senla.dto.payment.PaymentShortInfoDTO;
import senla.entities.Payment;
import senla.repository.iface.PaymentRepository;
import senla.servise.PaymentService;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    @Override
    public List<PaymentShortInfoDTO> findAll(){
        return paymentRepository.findAll().stream()
                .map(PaymentMapper::convertToShortDTO)
                .toList();

    }
    @Override
    public PaymentShortInfoDTO findById(long uuid){
        return PaymentMapper.convertToShortDTO(paymentRepository.findById(uuid));
    }
    @Override
    public void save(PaymentDTOToEntity object){
        paymentRepository.save(PaymentMapper.convertDTOToEntity(object));
    }
    @Override
    public void update(long uuid, PaymentDTOToEntity updateDTO){
        Payment payment = paymentRepository.findById(uuid);
        if (!updateDTO.getUrl().isEmpty()) payment.setUrl(updateDTO.getUrl());
    }
    @Override
    public void deleteById(long uuid){
        paymentRepository.deleteById(uuid);
    }
}
