package com.example.ecommerceproject.services;

import com.stripe.exception.StripeException;

public interface PaymentsService {
    String makePayment(String orderId, Long amount) throws StripeException;
}
