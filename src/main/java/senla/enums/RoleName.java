package senla.enums;


public enum RoleName {
    ADMIN("ADMIN"),
    PSYCHOLOGIST("PSYCHOLOGIST"),
    CLIENT("CLIENT");

    private final String authority;

    RoleName(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }
}

