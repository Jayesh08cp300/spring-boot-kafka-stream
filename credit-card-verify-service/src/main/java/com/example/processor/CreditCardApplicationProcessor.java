package com.example.processor;

import com.example.event.NewCreditCardEvent;
import com.example.event.VerifyCreditCardEvent;
import com.example.service.CreditCardVerificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class CreditCardApplicationProcessor {

	private final KafkaTemplate<String, VerifyCreditCardEvent> kafkaTemplate;
	private CreditCardVerificationService creditCardVerificationService;

	@KafkaListener(topics = "creditCardApplicationTopic", groupId = "creditCardApplicationGroupId")
	public void verifyCreditCardApplication(NewCreditCardEvent newCreditCardEvent) {
		var verifyCreditCardEvent = creditCardVerificationService.verifyCreditCardApplication(newCreditCardEvent);

		log.info("**** Publishing credit card applications verification status : {} **** ",
				verifyCreditCardEvent.getCreditCardVerificationStatus()
						.size());

		if (!(verifyCreditCardEvent.getCreditCardVerificationStatus()
				.isEmpty())) {
			log.info("New Credit Card Application verify.");
			kafkaTemplate.send("verifyCreditCardApplicationTopic", verifyCreditCardEvent);
		}
	}

}