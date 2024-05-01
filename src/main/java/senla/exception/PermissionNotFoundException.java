package senla.exception;

public class PermissionNotFoundException extends RuntimeException {
    public PermissionNotFoundException(Long id) {super(String.format("Permission '%s' not found", id));}
}
