package com.example.service;

import com.example.entity.CreditCard;
import com.example.event.CreditCardVerificationStatus;
import com.example.repository.CreditCardRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CreditCardService {

	private CreditCardRepository creditCardRepository;

	public void generateCreditCardNumberAndCvv(List<CreditCardVerificationStatus> creditCardVerificationStatus) {

		var creditCards = creditCardVerificationStatus.stream()
				.map(ccVerificationStatus -> {
					var creditCard = new CreditCard();
					BeanUtils.copyProperties(ccVerificationStatus, creditCard);
					if (ccVerificationStatus.getStatus()
							.equals(CreditCardVerificationStatus.VerificationStatus.APPROVED)) {
						log.info("**** Credit card number will generated as application is approved : {}", ccVerificationStatus.getRefId());
						var creditCardNumber = RandomStringUtils.random(12, false, true);
						creditCard.setCreditCardNumber(Long.parseLong(creditCardNumber));
						var cvv = RandomStringUtils.random(3, false, true);
						creditCard.setCvv(Long.parseLong(cvv));
					} else {
						log.info("**** Credit card number will not be generated as application is rejected : {}",
								ccVerificationStatus.getRefId());
						creditCard.setCreditCardNumber(0L);
						creditCard.setCvv(0l);
					}

					creditCard.setStatus(ccVerificationStatus.getStatus()
							.name());
					return creditCard;
				})
				.collect(Collectors.toList());

		log.info("***** Saving Credit Cards ****");
		creditCardRepository.saveAll(creditCards);
		log.info("***** Saved Credit Cards ****");
	}

	public String getApplicationStatus(String applicationRefId) {
		log.info("*** Searching application status for reference id : {}", applicationRefId);
		var creditCard = creditCardRepository.findByRefId(applicationRefId);
		return creditCard.getStatus();
	}
}