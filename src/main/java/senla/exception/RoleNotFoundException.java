package senla.exception;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(Long id) {
        super("Role id " + id + " not found");
    }
}
