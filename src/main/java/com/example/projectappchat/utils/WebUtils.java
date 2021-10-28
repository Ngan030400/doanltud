package com.example.projectappchat.utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class WebUtils {
    public static String toString(User user) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UserAccount: ").append(user.getUsername());

        Collection<GrantedAuthority> authorities = user.getAuthorities();

        if(authorities != null && !authorities.isEmpty()) {
            stringBuilder.append(" (");
            boolean first = true;
            for (GrantedAuthority a : authorities) {
                if (first) {
                    stringBuilder.append(a.getAuthority());
                    first = false;
                } else  {
                    stringBuilder.append(", ").append(a.getAuthority());
                }
            }
            stringBuilder.append(")");
        }
        return stringBuilder.toString();
    }
}