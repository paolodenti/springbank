package com.github.paolodenti.springbank.user.query.api.dto;

import com.github.paolodenti.springbank.user.core.dto.BaseResponse;
import com.github.paolodenti.springbank.user.core.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserLookupResponse extends BaseResponse {

    private List<User> users;

    public UserLookupResponse(List<User> users) {
        super(null);
        this.users = users;
    }

    public UserLookupResponse(String message, User user) {
        super(message);
        this.users = new ArrayList<>();
        if (user != null) {
            users.add(user);
        }
    }

    public UserLookupResponse(User user) {
        this(null, user);
    }

    public UserLookupResponse(String message) {
        this(message, null);
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
