package com.PaymentService.payments.repository;

import com.PaymentService.payments.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRespository extends JpaRepository<Payment, Long> {

    Payment findByPaymentGatewayReferenceId(String paymentId);
}
