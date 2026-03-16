package com.jdc.security.model.service;

import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;

@Service
public class AccountConfirmationService {

    public void sendConfirmMail(@NotBlank(message = "Please enter email for login.") String email, String otpCode) {
    }
}

