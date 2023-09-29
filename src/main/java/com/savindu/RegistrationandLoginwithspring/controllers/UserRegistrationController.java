package com.savindu.RegistrationandLoginwithspring.controllers;

import com.savindu.RegistrationandLoginwithspring.entities.RegistrationRequest;
import com.savindu.RegistrationandLoginwithspring.services.impl.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class UserRegistrationController {
private RegistrationService registrationService;

    public String register(@RequestBody RegistrationRequest request){
        return registrationService.register(request);
    }


}
