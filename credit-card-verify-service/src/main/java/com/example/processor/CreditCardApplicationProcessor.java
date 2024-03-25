package com.example.processor;

import com.example.event.NewCreditCardEvent;
import com.example.event.VerifyCreditCardEvent;
import com.example.service.CreditCardVerificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
@AllArgsConstructor
@Slf4j
public class CreditCardApplicationProcessor {

	private CreditCardVerificationService creditCardVerificationService;

	@Bean
	public Function<NewCreditCardEvent, VerifyCreditCardEvent> verifyCreditCardApplication() {

		return newCreditCardEvent -> {

			var verifyCreditCardEvent = creditCardVerificationService.verifyCreditCardApplication(newCreditCardEvent);

			log.info("**** Publishing credit card applications verification status : {} **** ",
					verifyCreditCardEvent.getCreditCardVerificationStatus()
							.size());

			return (verifyCreditCardEvent.getCreditCardVerificationStatus()
					.isEmpty()) ? null : verifyCreditCardEvent;
		};
	}

}