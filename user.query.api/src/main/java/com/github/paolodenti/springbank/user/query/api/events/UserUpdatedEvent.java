package com.github.paolodenti.springbank.user.query.api.events;

import com.github.paolodenti.springbank.user.core.models.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserUpdatedEvent {

    private String id;

    private User user;
}
