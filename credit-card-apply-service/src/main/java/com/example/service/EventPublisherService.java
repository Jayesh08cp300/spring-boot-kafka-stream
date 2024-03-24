package com.example.service;

import com.example.event.ApplicationDetail;
import com.example.event.NewCreditCardEvent;
import com.example.repository.CreditCardRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class EventPublisherService {

	private CreditCardRepository creditCardRepository;

	public Optional<NewCreditCardEvent> publishEvent() {
		var creditCards = creditCardRepository.findByPublishStatus(false);
		var applicationDetails = creditCards.stream()
				.map(creditCard -> {
					var applicationDetail = ApplicationDetail.builder()
							.build();
					BeanUtils.copyProperties(creditCard, applicationDetail);
					creditCard.setPublishStatus(true);
					return applicationDetail;
				})
				.collect(Collectors.toList());

		log.info("#####  Checked for new credit card request , available record to publish : {}", creditCards.size());
		Optional eventToPublish = Optional.empty();

		if (applicationDetails.size() > 0) {
			log.info("****** Updating credit card status as published ******");
			var newCreditCardEvent = NewCreditCardEvent.builder()
					.creditCardApplications(applicationDetails)
					.build();
			creditCardRepository.saveAll(creditCards);
			eventToPublish = Optional.of(newCreditCardEvent);
		}
		return eventToPublish;
	}
}