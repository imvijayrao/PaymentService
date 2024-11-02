package com.PaymentService.payments.controllers;

import com.PaymentService.payments.dtos.CreatePaymentLinkRequestDto;
import com.PaymentService.payments.dtos.CreatePaymentLinkResponseDto;
import com.PaymentService.payments.models.PaymentStatus;
import com.PaymentService.payments.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping()
    public CreatePaymentLinkResponseDto createPaymentLink(@RequestBody CreatePaymentLinkRequestDto createPaymentLinkRequestDto){
        String redirectUrl = paymentService.createPaymentLink(createPaymentLinkRequestDto);

        CreatePaymentLinkResponseDto responseDto = new CreatePaymentLinkResponseDto();
        responseDto.setPaymentUrl(redirectUrl);
        return responseDto;
    }

    @GetMapping("/{id}")
    public PaymentStatus checkPaymentStatus(@PathVariable("id") String paymentId){
        return paymentService.getPaymentStatus(paymentId);
    }

}

//User - createOrder() -> OrderService
//User - createPaymentLink() -> PaymentService
//User (order details page) -> check payment status -> paymentservice
//PaymentGateway(webhook) -> payment service