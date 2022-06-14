package com.example.hospital_management_system.domain.enums;

public enum Permission {
    USER_WRITE("user:write"),
    EMPLOYEE_WRITE("employee:write"),
    ADMIN_WRITE("admin:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
