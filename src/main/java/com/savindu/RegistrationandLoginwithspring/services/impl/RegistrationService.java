package com.savindu.RegistrationandLoginwithspring.services.impl;

import com.savindu.RegistrationandLoginwithspring.entities.AppUser;
import com.savindu.RegistrationandLoginwithspring.entities.ConfirmationToken;
import com.savindu.RegistrationandLoginwithspring.entities.RegistrationRequest;
import com.savindu.RegistrationandLoginwithspring.entities.enums.AppUserRole;
import com.savindu.RegistrationandLoginwithspring.util.EmailValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final  EmailValidator emailValidator;
    private final AppUserService appUserService;
    private final ConfirmationTokenService confirmationTokenService;
    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if(!isValidEmail){
            throw new IllegalStateException("email not valid");
        }
        return appUserService.signUpUser(
                new AppUser(
                        request.getFirstname(),
                        request.getLastname(),
                        request.getEmail(),
                        request.getPassword(),
                        AppUserRole.USER
                )
        );
    }
    @Transactional
    public String confirmToken(String token){
        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token)
                .orElseThrow(()->
                    new IllegalStateException("Token Not found"));
        if(confirmationToken.getConfirmedAt()!=null){
            throw new IllegalStateException("email already confirmed");
        }
        LocalDateTime expiredAt = confirmationToken.getExpiresAt();
        if(expiredAt.isBefore(LocalDateTime.now())){
            throw new IllegalStateException("token Expired");
        }
        confirmationTokenService.setConfirmedAt(token);
        appUserService.enableAppUser(
                confirmationToken.getAppUser().getEmail());
        return "confirmed";
    }




}
