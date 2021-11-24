package web.domain;

public enum Permission {
    RECORD_READ("read"),
    RECORD_WRITE("write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
