package com.userservice.infrastructure.repositories;

import com.userservice.document.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u join fetch u.userRoleList ul where u.userCredentials.username=lower(:username)")
    Optional<User> findByUsername(String username);

}
