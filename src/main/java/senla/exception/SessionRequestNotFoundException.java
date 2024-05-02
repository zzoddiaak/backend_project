package senla.exception;

public class SessionRequestNotFoundException extends RuntimeException {
    public SessionRequestNotFoundException(Long id) {
        super("Session request id " + id + " not found");
    }
}
