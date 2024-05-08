package senla.exception;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(Long id) {
        super("Role id " + id + " not found");
    }
    public RoleNotFoundException(String roleName) {super("Role with name "+ roleName +" not found" );}
}
