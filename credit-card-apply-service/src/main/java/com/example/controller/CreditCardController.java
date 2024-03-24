package com.example.controller;

import com.example.dto.CreditCardRequest;
import com.example.service.CreditCardService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/creditCard")
public class CreditCardController {

	private CreditCardService creditCardService;

	@PostMapping(path = "/apply")
	public ResponseEntity<String> createNewCreditCardRequest(@RequestBody CreditCardRequest creditCardRequest) {

		if (StringUtils.isEmpty(creditCardRequest.getFirstName()) || StringUtils.isEmpty(creditCardRequest.getLastName())
				|| StringUtils.isEmpty(creditCardRequest.getAddress())) {
			return ResponseEntity.badRequest()
					.build();
		}

		var applicationRefId = creditCardService.saveApplication(creditCardRequest);
		return applicationRefId.isEmpty() ?
				ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.build() :
				ResponseEntity.status(HttpStatus.CREATED)
						.body("Your application reference number is :" + applicationRefId.get());
	}

}