package web.esame.gruppo35.helperClasses;

public enum UserRole {
    AMMINISTRATORE("Amministratore"),
    ADERENTE("Aderente"),
    SIMPATIZZANTE("Simpatizzante");

    private final String displayName;

    UserRole(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
