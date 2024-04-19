package com.PaymentService.payments.services;

import com.PaymentService.payments.models.PaymentStatus;
import com.PaymentService.payments.paymentgateways.PaymentGatewayFactory;
import com.PaymentService.payments.paymentgateways.PaymentGatewayStrategyPattern;
import com.PaymentService.payments.paymentgateways.RazorPaymentGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentGatewayFactory paymentGatewayFactory;

    public String createPaymentLink(Long orderId){

        //Using order id we can able to get details of the user and order as well and calculate amount and give price details

        Long amount = 1000L;
        String username = "vijay rao";
        String userphone = "+919999999999";
        String userEmail = "xyz@gmail.com";

        PaymentGatewayStrategyPattern paymentGatewayStrategyPattern = paymentGatewayFactory.getPaymentGateway();


        return paymentGatewayStrategyPattern.createPaymentLink(amount, username, userEmail, userphone, orderId);

    }

    public PaymentStatus getPaymentStatus(Long paymentId) {
        return null;
    }
}