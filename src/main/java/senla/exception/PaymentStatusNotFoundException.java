package senla.exception;

public class PaymentStatusNotFoundException extends RuntimeException {
    public PaymentStatusNotFoundException(Long id) {super(String.format("Payment status with id %s not found", id));}
}
