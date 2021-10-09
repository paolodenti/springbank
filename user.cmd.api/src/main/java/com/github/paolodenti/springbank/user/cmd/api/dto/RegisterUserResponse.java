package com.github.paolodenti.springbank.user.cmd.api.dto;

import com.github.paolodenti.springbank.user.core.dto.BaseResponse;
import lombok.Getter;

@Getter
public class RegisterUserResponse extends BaseResponse {

    private final String id;

    public RegisterUserResponse(String id, String message) {
        super(message);
        this.id = id;
    }
}
