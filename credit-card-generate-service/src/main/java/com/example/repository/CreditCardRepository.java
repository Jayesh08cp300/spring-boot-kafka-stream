package com.example.repository;

import com.example.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
	CreditCard findByRefId(String applicationRefId);
}