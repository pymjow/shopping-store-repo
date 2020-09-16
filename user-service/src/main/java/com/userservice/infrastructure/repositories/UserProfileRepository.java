package com.userservice.infrastructure.repositories;

import com.userservice.document.model.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {


}
