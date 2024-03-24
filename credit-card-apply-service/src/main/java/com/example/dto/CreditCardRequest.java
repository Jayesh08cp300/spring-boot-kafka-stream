package com.example.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditCardRequest {
	@NonNull
	private String firstName;
	@NonNull
	private String lastName;
	@NonNull
	private Integer annualIncome;
	@NonNull
	private String address;
}
