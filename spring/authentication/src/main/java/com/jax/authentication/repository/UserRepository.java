package com.jax.authentication.repository;

import com.jax.authentication.models.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    Optional<User> findUserByUsername(String username);

    Boolean existsUserByUsername(String username);

    Boolean existsUserByEmail(String email);
}
