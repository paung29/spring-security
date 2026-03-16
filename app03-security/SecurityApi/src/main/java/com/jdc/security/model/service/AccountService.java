package com.jdc.security.model.service;

import com.jdc.security.api.input.SignUpForm;
import com.jdc.security.api.output.SignUpResult;
import com.jdc.security.exception.AppBusinessException;
import com.jdc.security.model.entity.Account;
import com.jdc.security.model.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class AccountService {

    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AccountConfirmationService confirmationService;

    @Transactional
    public SignUpResult signup(SignUpForm form) {

        if(accountRepo.findOneByEmail(form.email()).isPresent()) {
            throw new AppBusinessException("Your email is already used.");
        }

        var otpCode = generateOtp();

        var entity = new Account();
        entity.setPassword(passwordEncoder.encode(otpCode));
        accountRepo.save(entity);

        confirmationService.sendConfirmMail(form.email(), otpCode);

        return new SignUpResult(entity.getId(), "Please check your email.");
    }

    private String generateOtp() {
        var code = ThreadLocalRandom.current().nextInt(999999);
        return "%06d".formatted(code);
    }
}
