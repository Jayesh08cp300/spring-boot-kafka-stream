package com.example.service;

import com.example.dto.CreditCardRequest;
import com.example.entity.CreditCard;
import com.example.repository.CreditCardRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class CreditCardService {

	private CreditCardRepository creditCardRepository;

	public Optional<String> saveApplication(CreditCardRequest creditCardRequest) {
		try {
			var creditCard = new CreditCard();
			BeanUtils.copyProperties(creditCardRequest, creditCard);
			creditCard.setPublishStatus(false);
			creditCard.setRefId(RandomStringUtils.random(10, false, true));
			var creditCardSaved = creditCardRepository.save(creditCard);
			return Optional.of(creditCardSaved.getRefId());
		} catch (Exception exception) {
			log.error(exception.getMessage());
			return Optional.empty();
		}
	}
}