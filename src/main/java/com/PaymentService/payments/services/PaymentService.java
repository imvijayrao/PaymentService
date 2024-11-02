package com.PaymentService.payments.services;

import com.PaymentService.payments.dtos.CreatePaymentLinkRequestDto;
import com.PaymentService.payments.models.Payment;
import com.PaymentService.payments.models.PaymentGateway;
import com.PaymentService.payments.models.PaymentStatus;
import com.PaymentService.payments.services.paymentgateways.PaymentGatewayFactory;
import com.PaymentService.payments.services.paymentgateways.PaymentGatewayStrategyPattern;
import com.PaymentService.payments.services.paymentgateways.RazorPaymentGateway;
import com.PaymentService.payments.repository.PaymentRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

@Service
public class PaymentService {

    private final RazorPaymentGateway razorPaymentGateway;

    @Autowired
    private PaymentGatewayFactory paymentGatewayFactory;

    @Autowired
    private PaymentRespository paymentRespository;

    public PaymentService(RazorPaymentGateway razorPaymentGateway) {
        this.razorPaymentGateway = razorPaymentGateway;
    }

    public String createPaymentLink(CreatePaymentLinkRequestDto createPaymentLinkRequestDto){

        //Using order id we can able to get details of the user and order as well and calculate amount and give price details

        Long amount = 1000L;
        String username = "vijay rao";
        String userphone = "+919999999999";
        String userEmail = "xyz@gmail.com";

        PaymentGatewayStrategyPattern paymentGatewayStrategyPattern = paymentGatewayFactory.getPaymentGateway();

        String paymentLink =  paymentGatewayStrategyPattern.createPaymentLink(amount, username, userEmail, userphone, createPaymentLinkRequestDto.getOrderId());

        Payment payment = new Payment();
        payment.setPaymentLink(paymentLink);
        payment.setOrderId(createPaymentLinkRequestDto.getOrderId());
        payment.setAmount(amount);
        payment.setPaymentGateway(createPaymentLinkRequestDto.getPaymentGateway());
        payment.setPaymentStatus(PaymentStatus.PENDING);
        payment.setPaymentGatewayReferenceId(createPaymentLinkRequestDto.getPaymentGatewayReferenceId());

        paymentRespository.save(payment);

        return paymentLink;

    }

    public PaymentStatus getPaymentStatus(String paymentId) {
        Payment payment = paymentRespository.findByPaymentGatewayReferenceId(paymentId);
        PaymentGatewayStrategyPattern paymentGateway = null;

        if (payment.getPaymentGateway().equals(PaymentGateway.RAZORPAY)) {
            paymentGateway = razorPaymentGateway;
        }

        PaymentStatus paymentStatus = paymentGateway.getPaymentStatus(paymentId);

        payment.setPaymentStatus(paymentStatus);

        paymentRespository.save(payment);

        return paymentStatus;
    }
}