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
public class NewCreditCardEvent {

	private final eventType event = eventType.New_Credit_Card;
	private List<ApplicationDetail> creditCardApplications;

	enum eventType {
		New_Credit_Card
	}
}
