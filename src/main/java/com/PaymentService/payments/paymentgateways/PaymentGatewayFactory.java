package com.PaymentService.payments.paymentgateways;

import com.razorpay.RazorpayClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PaymentGatewayFactory {

    @Autowired
    private RazorPaymentGateway razorPaymentGateway;


    public PaymentGatewayStrategyPattern getPaymentGateway(){

//        int random = new Random().nextInt();
//
//        if(random % 2 == 0) return new RazorPaymentGateway();
//
//        return new StripePaymentGateway();

        return razorPaymentGateway;

    }
}
