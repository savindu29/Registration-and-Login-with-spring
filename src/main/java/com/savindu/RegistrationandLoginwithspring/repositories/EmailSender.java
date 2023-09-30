package com.savindu.RegistrationandLoginwithspring.repositories;

public interface EmailSender {
    void send(String to, String email);
}
