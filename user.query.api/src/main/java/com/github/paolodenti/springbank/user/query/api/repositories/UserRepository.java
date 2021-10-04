package com.github.paolodenti.springbank.user.query.api.repositories;

import com.github.paolodenti.springbank.user.core.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}
