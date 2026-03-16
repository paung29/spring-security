package com.jdc.security.utils;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppAuthenticationProvider extends DaoAuthenticationProvider {

    public AppAuthenticationProvider(AppUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        super(userDetailsService);
        setPasswordEncoder(passwordEncoder);
        setHideUserNotFoundExceptions(false);
    }
}
