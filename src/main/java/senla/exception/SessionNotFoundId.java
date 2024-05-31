package senla.exception;

public class SessionNotFoundId extends RuntimeException{
    public SessionNotFoundId(Long id){
        super("Session" + id + " Not Found");
    }
}
