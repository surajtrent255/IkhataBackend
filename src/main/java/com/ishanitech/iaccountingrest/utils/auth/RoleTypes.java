package com.ishanitech.iaccountingrest.utils.auth;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public enum RoleTypes {
    USER("2"),
    ADMIN("1");
    @JsonValue
    private final String roleId;
    public String getRoleId() {
        return roleId;
    }
}
