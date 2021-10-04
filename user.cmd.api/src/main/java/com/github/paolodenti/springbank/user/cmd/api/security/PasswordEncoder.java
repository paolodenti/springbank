package com.github.paolodenti.springbank.user.cmd.api.security;

public interface PasswordEncoder {

    String hashPassword(String password);
}
