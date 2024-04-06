package senla.servise.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import senla.dto.PaymentStatus.PaymentStatusDTO;
import senla.dto.PaymentStatus.PaymentStatusDTOToEntity;
import senla.dto.mapper.PaymentStatusMapper;
import senla.repository.iface.PaymentStatusRepository;
import senla.servise.PaymentStatusService;

import java.util.List;
@Service
@RequiredArgsConstructor
public class PaymentStatusServiceImpl implements PaymentStatusService {
    private final PaymentStatusRepository paymentStatusRepository;
    @Override
    public List<PaymentStatusDTO> findAll(){
        return paymentStatusRepository.findAll().stream()
                .map(PaymentStatusMapper::convertEntityToDTO)
                .toList();

    }
    @Override
    public PaymentStatusDTO findById(long uuid){
        return PaymentStatusMapper.convertEntityToDTO(paymentStatusRepository.findById(uuid));
    }
    @Override
    public void save(PaymentStatusDTOToEntity object){
        paymentStatusRepository.save(PaymentStatusMapper.convertDTOToEntity(object));
    }
    @Override
    public void deleteById(long uuid){
        paymentStatusRepository.deleteById(uuid);
    }

}
