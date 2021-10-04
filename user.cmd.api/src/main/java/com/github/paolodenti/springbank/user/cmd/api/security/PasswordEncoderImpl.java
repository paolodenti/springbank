package com.github.paolodenti.springbank.user.cmd.api.security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Qualifier("passwordEncoder")
public class PasswordEncoderImpl implements PasswordEncoder {

    @Override
    public String hashPassword(String password) {
        var encoder = new BCryptPasswordEncoder(12);

        return encoder.encode(password);
    }
}
