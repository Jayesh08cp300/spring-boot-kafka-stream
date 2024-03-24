package com.example.subscriber;

import com.example.event.VerifyCreditCardEvent;
import com.example.service.CreditCardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Slf4j
@Component
public class CreditCardVerificationEventSubscriber {
	private CreditCardService creditCardService;

	@KafkaListener(topics = "verifyCreditCardApplicationTopic", groupId = "verifyCreditCardApplicationGroupId")
	public void generateCreditCard(VerifyCreditCardEvent verifyCreditCardEvent) {
		log.info("Received credit card application : {}", verifyCreditCardEvent.getCreditCardVerificationStatus()
				.size());
		creditCardService.generateCreditCardNumberAndCvv(verifyCreditCardEvent.getCreditCardVerificationStatus());
	}
}
