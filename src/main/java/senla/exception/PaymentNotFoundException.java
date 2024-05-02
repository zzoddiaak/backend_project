package senla.exception;

public class PaymentNotFoundException extends RuntimeException {
    public PaymentNotFoundException(Long id) {super(String.format("Payment with id %s not found", id));}
}
