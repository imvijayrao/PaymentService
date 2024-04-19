package com.PaymentService.payments.paymentgateways;

import com.PaymentService.payments.models.PaymentStatus;

public interface PaymentGatewayStrategyPattern {
    String createPaymentLink(Long amount, String username, String userEmail, String userPhone, Long orderId);
    PaymentStatus getPaymentStatus(Long paymentId);
}
