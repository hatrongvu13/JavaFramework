package com.jax.authentication.repository;

import com.jax.authentication.models.ERoles;
import com.jax.authentication.models.Role;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, ObjectId> {

    Optional<Role> findRoleByName(ERoles name);

}
