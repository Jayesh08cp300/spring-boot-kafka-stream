package com.example.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VerifyCreditCardEvent {

	private final VerifyCreditCardEvent.eventType event = eventType.VERIFY_Credit_Card;
	private List<CreditCardVerificationStatus> creditCardVerificationStatus;

	enum eventType {
		VERIFY_Credit_Card
	}
}