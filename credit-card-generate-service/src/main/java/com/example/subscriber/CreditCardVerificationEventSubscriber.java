package com.example.subscriber;

import com.example.event.VerifyCreditCardEvent;
import com.example.service.CreditCardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@AllArgsConstructor
@Slf4j
@Configuration
public class CreditCardVerificationEventSubscriber {
	private CreditCardService creditCardService;

	@Bean
	public Consumer<VerifyCreditCardEvent> generateCreditCard() {
		return verifyCreditCardEvent -> {
			log.info("Received credit card application : {}", verifyCreditCardEvent.getCreditCardVerificationStatus()
					.size());
			creditCardService.generateCreditCardNumberAndCvv(verifyCreditCardEvent.getCreditCardVerificationStatus());
		};
	}
}
