package com.example.ecommerceproject.controller;

import com.example.ecommerceproject.dto.PaymentsDTO;
import com.example.ecommerceproject.services.PaymentsService;
import com.stripe.exception.StripeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentsController {

    PaymentsService paymentsService;
    public PaymentsController(PaymentsService paymentsService) {
        this.paymentsService = paymentsService;
    }

    @PostMapping("/payments")
    public ResponseEntity<String> createPaymentLink(@RequestBody PaymentsDTO paymentsDTO) throws StripeException {
        String url = paymentsService.makePayment(paymentsDTO.getOrderId(), paymentsDTO.getAmount());
        ResponseEntity<String> response = new ResponseEntity<>(url, HttpStatus.OK);
        return response;
    }
}
