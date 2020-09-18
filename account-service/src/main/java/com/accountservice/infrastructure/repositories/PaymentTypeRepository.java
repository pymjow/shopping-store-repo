package com.accountservice.infrastructure.repositories;

import com.accountservice.document.model.entity.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentTypeRepository extends JpaRepository<PaymentType, Long> {


}
