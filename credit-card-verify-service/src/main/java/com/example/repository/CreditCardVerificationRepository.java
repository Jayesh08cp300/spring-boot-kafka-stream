package com.example.repository;

import com.example.entity.CreditCardVerification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardVerificationRepository extends JpaRepository<CreditCardVerification, Long> {
}