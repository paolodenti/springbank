package com.github.paolodenti.springbank.user.query.api.dto;

import com.github.paolodenti.springbank.user.core.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class UserLookupResponse {

    private List<User> users;

    public UserLookupResponse(User user) {
        this.users = new ArrayList<>();
        users.add(user);
    }
}
