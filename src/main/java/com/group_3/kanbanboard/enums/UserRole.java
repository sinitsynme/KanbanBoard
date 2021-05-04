package com.group_3.kanbanboard.enums;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    NOT_ALLOWED, DEVELOPER, LEAD;

    @Override
    public String getAuthority() {
        return name();
    }
}
