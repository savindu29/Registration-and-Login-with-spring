package com.savindu.RegistrationandLoginwithspring.services.impl;

import com.savindu.RegistrationandLoginwithspring.entities.AppUser;
import com.savindu.RegistrationandLoginwithspring.entities.RegistrationRequest;
import com.savindu.RegistrationandLoginwithspring.entities.enums.AppUserRole;
import com.savindu.RegistrationandLoginwithspring.util.EmailValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final  EmailValidator emailValidator;
    private final AppUserService appUserService;
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
}
