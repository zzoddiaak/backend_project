package senla.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionNotFoundHandler {

    @ExceptionHandler({
            ClientCardNotFoundException.class,
            CourseNotFoundException.class,
            OrderNotFoundException.class,
            PaymentNotFoundException.class,
            PaymentStatusNotFoundException.class,
            RequestStatusNotFoundException.class,
            ReviewNotFoundException.class,
            RoleNotFoundException.class,
            SessionNotFoundException.class,
            SessionRequestNotFoundException.class,
            UserNotFoundException.class,
            UserNotFoundId.class,
            OrderNotFoundId.class,
            SessionNotFoundId.class

    })
    public ResponseEntity<Object> handleException(Exception e) {
        return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
