package com.savindu.RegistrationandLoginwithspring.services.impl;

import com.savindu.RegistrationandLoginwithspring.entities.ConfirmationToken;
import com.savindu.RegistrationandLoginwithspring.repositories.ConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;
    public void saveConfirmationToken(ConfirmationToken token){
        confirmationTokenRepository.save(token);
    }

}
