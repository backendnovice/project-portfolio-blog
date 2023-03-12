package com.backendnovice.projectportfolioblog.global.enums;

/**
 * @name   : Role
 * @author : Juwon
 * @Date   : 2023-03-12
 * @Desc   : Member Role Enums.
**/

public enum Role {
    
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN");
    
    private String role;
    
    Role(String role) {
        this.role = role;
    }
    
    public String getRole() {
        return role;
    }
    
}
