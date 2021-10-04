package com.github.paolodenti.springbank.user.cmd.api.aggregates;

import com.github.paolodenti.springbank.user.cmd.api.commands.RegisterUserCommand;
import com.github.paolodenti.springbank.user.cmd.api.commands.RemoveUserCommand;
import com.github.paolodenti.springbank.user.cmd.api.commands.UpdateUserCommand;
import com.github.paolodenti.springbank.user.cmd.api.security.PasswordEncoder;
import com.github.paolodenti.springbank.user.cmd.api.security.PasswordEncoderImpl;
import com.github.paolodenti.springbank.user.core.events.UserRegisteredEvent;
import com.github.paolodenti.springbank.user.core.events.UserRemovedEvent;
import com.github.paolodenti.springbank.user.core.events.UserUpdatedEvent;
import com.github.paolodenti.springbank.user.core.models.User;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Aggregate
public class UserAggregate {

    private PasswordEncoder passwordEncoder = new PasswordEncoderImpl();

    @AggregateIdentifier
    private String id;

    private User user;

    public UserAggregate() {
    }

    @CommandHandler
    public UserAggregate(RegisterUserCommand command) {
        var newUser = command.getUser();
        newUser.setId(command.getId());
        var hashedPassword = passwordEncoder.hashPassword(newUser.getAccount().getPassword());
        newUser.getAccount().setPassword(hashedPassword);

        var event = UserRegisteredEvent.builder().id(command.getId()).user(newUser).build();

        AggregateLifecycle.apply(event);
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @CommandHandler
    public void handle(UpdateUserCommand command) {
        var updatedUser = command.getUser();
        updatedUser.setId(command.getId());
        var hashedPassword = passwordEncoder.hashPassword(updatedUser.getAccount().getPassword());
        updatedUser.getAccount().setPassword(hashedPassword);

        var event = UserUpdatedEvent.builder().id(UUID.randomUUID().toString()).user(updatedUser).build();

        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(RemoveUserCommand command) {
        var event = new UserRemovedEvent();
        event.setId(command.getId());

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(UserRegisteredEvent event) {
        this.id = event.getId();
        this.user = event.getUser();
    }

    @EventSourcingHandler
    public void on(UserUpdatedEvent event) {
        this.user = event.getUser();
    }

    @EventSourcingHandler
    public void on(UserRemovedEvent event) {
        AggregateLifecycle.markDeleted();
    }
}
