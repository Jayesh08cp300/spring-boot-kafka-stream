package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "T_CREDIT_CARD_VERIFICATION")
public class CreditCardVerification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "REFERENCE_ID")
	private String refId;
	@Column(name = "FIRST_NAME")
	private String firstName;
	@Column(name = "LAST_NAME")
	private String lastName;
	@Column(name = "ANNUAL_INCOME")
	private Integer annualIncome;
	@Column(name = "ADDRESS")
	private String address;
	@Column(name = "APPLICATION_VERIFICATION_STATUS")
	private String status;

}
