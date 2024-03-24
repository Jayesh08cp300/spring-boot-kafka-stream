package com.example.publisher;

import com.example.event.NewCreditCardEvent;
import com.example.service.EventPublisherService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class CreditCardEventPublisher {

	private final KafkaTemplate<String, NewCreditCardEvent> kafkaTemplate;
	private EventPublisherService eventPublisherService;

	@Scheduled(cron = "*/10 * * * * *")
	public void newCreditCardEvent() {
		var newCreditCardEvent = eventPublisherService.publishEvent();
		if (!newCreditCardEvent.isEmpty()) {
			log.info("New Credit Card Application.");
			kafkaTemplate.send("creditCardApplicationTopic", newCreditCardEvent.get());
		}
	}

}