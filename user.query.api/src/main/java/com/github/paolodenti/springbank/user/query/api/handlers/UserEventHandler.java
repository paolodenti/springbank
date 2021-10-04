package com.github.paolodenti.springbank.user.query.api.handlers;

import com.github.paolodenti.springbank.user.core.events.UserRegisteredEvent;
import com.github.paolodenti.springbank.user.core.events.UserRemovedEvent;
import com.github.paolodenti.springbank.user.core.events.UserUpdatedEvent;

public interface UserEventHandler {

    void on(UserRegisteredEvent event);

    void on(UserUpdatedEvent event);

    void on(UserRemovedEvent event);
}
