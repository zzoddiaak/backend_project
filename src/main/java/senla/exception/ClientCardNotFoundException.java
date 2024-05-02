package senla.exception;

public class ClientCardNotFoundException extends RuntimeException {
    public ClientCardNotFoundException(Long id) {super(String.format("Client card id %s not found", id));}
}
