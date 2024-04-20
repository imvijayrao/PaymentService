package com.PaymentService.payments.configuration;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RazorpayConfig {

    @Value("${razorpay.key_id}")
    private String razorKeyId;

    @Value("${razorpay.key_secret}")
    private String razorKeySecret;

    @Bean
    public RazorpayClient createRazorpayClient() throws RazorpayException {
        return new RazorpayClient(razorKeyId,razorKeySecret);
    }

}
