package com.github.paolodenti.springbank.user.query.api.controllers;

import com.github.paolodenti.springbank.user.query.api.dto.UserLookupResponse;
import com.github.paolodenti.springbank.user.query.api.queries.FindAllUsersQuery;
import com.github.paolodenti.springbank.user.query.api.queries.FindUserByIdQuery;
import com.github.paolodenti.springbank.user.query.api.queries.SearchUsersQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/userLookup")
public class UserLookupController {

    private final QueryGateway queryGateway;

    @Autowired
    public UserLookupController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping(path = "/")
    public ResponseEntity<UserLookupResponse> getAllUsers() {

        try {
            var query = new FindAllUsersQuery();
            var response = queryGateway.query(query, ResponseTypes.instanceOf(UserLookupResponse.class)).join();

            if (response == null || response.getUsers() == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            var safeMessage = "Failed to complete get all user request";
            System.out.println(e.getMessage());

            return new ResponseEntity<>(new UserLookupResponse(safeMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/byId/{id}")
    public ResponseEntity<UserLookupResponse> getUserById(@PathVariable(value = "id") String id) {

        try {
            var query = new FindUserByIdQuery(id);
            var response = queryGateway.query(query, ResponseTypes.instanceOf(UserLookupResponse.class)).join();

            if (response == null || response.getUsers() == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            var safeMessage = "Failed to complete get user by ID request";
            System.out.println(e.getMessage());

            return new ResponseEntity<>(new UserLookupResponse(safeMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/byFilter/{filter}")
    public ResponseEntity<UserLookupResponse> searchUsersByFilter(@PathVariable(value = "filter") String filter) {

        try {
            var query = new SearchUsersQuery(filter);
            var response = queryGateway.query(query, ResponseTypes.instanceOf(UserLookupResponse.class)).join();

            if (response == null || response.getUsers() == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            var safeMessage = "Failed to complete search user by filter request";
            System.out.println(e.getMessage());

            return new ResponseEntity<>(new UserLookupResponse(safeMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
