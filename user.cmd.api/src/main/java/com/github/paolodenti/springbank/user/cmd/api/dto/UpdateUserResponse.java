package com.github.paolodenti.springbank.user.cmd.api.dto;

import com.github.paolodenti.springbank.user.core.dto.BaseResponse;
import lombok.Getter;

@Getter
public class UpdateUserResponse extends BaseResponse {

    private final String id;

    public UpdateUserResponse(String id, String message) {
        super(message);
        this.id = id;
    }
}
