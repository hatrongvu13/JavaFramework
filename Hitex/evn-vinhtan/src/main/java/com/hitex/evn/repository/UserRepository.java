package com.hitex.evn.repository;

import com.hitex.evn.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Chidq
 * @project evn-vinhtan
 * @created 22/05/2021 - 1:15 PM
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query (value = "Select count(u) from User u")
    int countUser();
}
