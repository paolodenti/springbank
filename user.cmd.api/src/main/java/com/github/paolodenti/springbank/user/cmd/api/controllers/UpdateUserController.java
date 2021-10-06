package com.github.paolodenti.springbank.user.cmd.api.controllers;

import com.github.paolodenti.springbank.user.cmd.api.commands.UpdateUserCommand;
import com.github.paolodenti.springbank.user.cmd.api.dto.BaseResponse;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/updateUser")
public class UpdateUserController {

    private final CommandGateway commandGateway;

    @Autowired
    public UpdateUserController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<BaseResponse> updateUser(@PathVariable(value = "id") String id, @Valid @RequestBody UpdateUserCommand command) {
        try {
            command.setId(id);

            commandGateway.sendAndWait(command);
            return new ResponseEntity<>(new BaseResponse("user succesfully updated"), HttpStatus.OK);
        } catch (Exception e) {
            var safeErrorMessage = "Error while processing register user request for id  - " + id;
            System.out.println(e.getMessage());

            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
