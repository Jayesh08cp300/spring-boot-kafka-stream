package com.example.controller;

import com.example.service.CreditCardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/creditCard")
public class CreditCardController {

	private CreditCardService creditCardService;

	@GetMapping("/status")
	public ResponseEntity<String> creditCardApplicationStatus(@RequestParam String applicationRefId) {

		if (StringUtils.isEmpty(applicationRefId)) {
			ResponseEntity.badRequest()
					.build();
		}
		var status = creditCardService.getApplicationStatus(applicationRefId);

		return StringUtils.isEmpty(status) ?
				ResponseEntity.status(HttpStatus.OK)
						.body("Your application is still under progress") :
				ResponseEntity.status(HttpStatus.OK)
						.body("Your application with reference Id " + applicationRefId + "has been " + status);
	}
}
