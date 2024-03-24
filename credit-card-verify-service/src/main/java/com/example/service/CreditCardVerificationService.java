package com.example.service;

import com.example.entity.CreditCardVerification;
import com.example.event.CreditCardVerificationStatus;
import com.example.event.NewCreditCardEvent;
import com.example.event.VerifyCreditCardEvent;
import com.example.repository.CreditCardVerificationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CreditCardVerificationService {

	private CreditCardVerificationRepository creditCardVerificationRepository;

	public VerifyCreditCardEvent verifyCreditCardApplication(NewCreditCardEvent newCreditCardEvent) {

		var creditCardApplications = newCreditCardEvent.getCreditCardApplications();
		var creditCardVerificationStatusList = creditCardApplications.stream()
				.map(creditCardApplication -> {
					var creditCardVerificationStatus = CreditCardVerificationStatus.builder()
							.build();
					BeanUtils.copyProperties(creditCardApplication, creditCardVerificationStatus);
					if (creditCardApplication.getAnnualIncome() > 4000) {
						creditCardVerificationStatus.setStatus(CreditCardVerificationStatus.VerificationStatus.APPROVED);
					} else {
						creditCardVerificationStatus.setStatus(CreditCardVerificationStatus.VerificationStatus.REJECTED);
					}
					return creditCardVerificationStatus;
				})
				.collect(Collectors.toList());

		var verifyCreditCardEvent = VerifyCreditCardEvent.builder()
				.creditCardVerificationStatus(creditCardVerificationStatusList)
				.build();

		saveCreditCardVerificationStatus(creditCardVerificationStatusList);

		return verifyCreditCardEvent;
	}

	private void saveCreditCardVerificationStatus(List<CreditCardVerificationStatus> creditCardVerificationStatusList) {

		var creditCardVerifications = creditCardVerificationStatusList.stream()
				.map(creditCardVerificationStatus -> {
					var creditCardVerification = new CreditCardVerification();
					BeanUtils.copyProperties(creditCardVerificationStatus, creditCardVerification);
					creditCardVerification.setStatus(creditCardVerificationStatus.getStatus()
							.name());
					return creditCardVerification;
				})
				.collect(Collectors.toList());

		log.info("**** Saving credit card application status ******");
		creditCardVerificationRepository.saveAll(creditCardVerifications);
	}
}