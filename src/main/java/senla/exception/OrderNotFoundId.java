package senla.exception;

public class OrderNotFoundId extends RuntimeException{
    public OrderNotFoundId(Long id){
        super("Order" + id + "Not Found");
    }
}
