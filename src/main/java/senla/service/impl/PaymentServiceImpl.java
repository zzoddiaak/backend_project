package senla.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import senla.dto.payment.PaymentSessionDTO;
import senla.entities.Order;
import senla.entities.Session;
import senla.entities.User;
import senla.exception.UserNotFoundId;
import senla.entities.Payment;
import senla.repository.api.OrderRepository;
import senla.repository.api.PaymentRepository;
import senla.repository.api.SessionRepository;
import senla.repository.api.UserRepository;
import senla.service.PaymentService;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;
    private final OrderRepository orderRepository;

    @Override
    public void buySession(PaymentSessionDTO paymentSessionDTO) {
        User user =  userRepository.findById(paymentSessionDTO.getUserId());
        if (user == null) {
            throw new UserNotFoundId(paymentSessionDTO.getUserId());
        }
        Session session = sessionRepository.findById(paymentSessionDTO.getSessionId());
        Order order = orderRepository.findById(paymentSessionDTO.getOrderId());

        Payment payment = Payment.builder()
                .user(user)
                .session(session)
                .url(paymentSessionDTO.getUrl())
                .order(order)
                .build();

        paymentRepository.save(payment);
    }
}
