package com.userservice.infrastructure.repositories;

import com.userservice.document.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


}
