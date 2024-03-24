package com.example.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardVerificationStatus {

	private String firstName;
	private String lastName;
	private Integer annualIncome;
	private String address;
	private String refId;
	private VerificationStatus status;

	public enum VerificationStatus {
		APPROVED, REJECTED
	}

}
