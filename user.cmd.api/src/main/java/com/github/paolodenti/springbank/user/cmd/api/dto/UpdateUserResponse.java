package com.github.paolodenti.springbank.user.cmd.api.dto;

import lombok.Getter;

@Getter
public class UpdateUserResponse extends BaseResponse {

    private final String id;

    public UpdateUserResponse(String id, String message) {
        super(message);
        this.id = id;
    }
}
