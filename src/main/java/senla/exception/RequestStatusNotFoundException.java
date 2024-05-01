package senla.exception;

public class RequestStatusNotFoundException extends RuntimeException {
    public RequestStatusNotFoundException(Long id) {
        super(String.format("Request with id '%s' not found", id));
    }
}
