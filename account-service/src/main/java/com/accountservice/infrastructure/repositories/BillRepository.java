package com.accountservice.infrastructure.repositories;

import com.accountservice.document.model.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {


}
