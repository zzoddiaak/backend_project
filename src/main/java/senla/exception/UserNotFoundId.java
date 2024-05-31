package senla.exception;

public class UserNotFoundId extends RuntimeException{
    public UserNotFoundId(Long id){
        super("User " + id + " not found");
    }
}
