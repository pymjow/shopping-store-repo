package com.accountservice.infrastructure.repositories;

import com.accountservice.document.model.aggregates.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
