package com.PaymentService.payments.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePaymentLinkResponseDto {

    private String paymentUrl;
}
