package web.esame.gruppo35.helperClasses;

public enum UserRole {
    ADMIN("Amministratore"),
    MEMBER("Membro"),
    SUPPORTER("Simpatizzante");

    private final String displayName;

    UserRole(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
