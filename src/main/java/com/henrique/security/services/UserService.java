package com.henrique.security.services;

import com.henrique.security.UserSS;
import org.springframework.security.core.context.SecurityContextHolder;


public class UserService {

    public static UserSS authenticated() {
        try {
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        catch (Exception e) {
            return null;
        }
    }
}

