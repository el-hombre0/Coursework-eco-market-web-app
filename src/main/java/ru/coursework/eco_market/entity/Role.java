package ru.coursework.eco_market.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN;
    @Override
    public String getAuthority(){
        return name();
    }
}
