package com.PaymentService.payments.services.paymentgateways;

import com.PaymentService.payments.models.PaymentStatus;

public class StripePaymentGateway implements PaymentGatewayStrategyPattern{
    @Override
    public String createPaymentLink(Long amount, String username, String userEmail, String userPhone, Long orderId) {
        return null;
    }

    @Override
    public PaymentStatus getPaymentStatus(String paymentId) {
        return null;
    }
}
