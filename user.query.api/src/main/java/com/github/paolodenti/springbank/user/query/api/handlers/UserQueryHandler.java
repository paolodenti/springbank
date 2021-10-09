package com.github.paolodenti.springbank.user.query.api.handlers;

import com.github.paolodenti.springbank.user.query.api.dto.UserLookupResponse;
import com.github.paolodenti.springbank.user.query.api.queries.FindAllUsersQuery;
import com.github.paolodenti.springbank.user.query.api.queries.FindUserByIdQuery;
import com.github.paolodenti.springbank.user.query.api.queries.SearchUsersQuery;

public interface UserQueryHandler {

    UserLookupResponse getUserById(FindUserByIdQuery query);

    UserLookupResponse searchUsers(SearchUsersQuery query);

    UserLookupResponse getAllUsers(FindAllUsersQuery query);
}
