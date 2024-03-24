package com.example.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDetail {
	private String firstName;
	private String lastName;
	private Integer annualIncome;
	private String address;
	private String refId;
}
