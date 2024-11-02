package com.PaymentService.payments.dtos;

import com.PaymentService.payments.models.PaymentGateway;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePaymentLinkRequestDto {

    private Long orderId;
    private PaymentGateway paymentGateway;
    private String paymentGatewayReferenceId;

}
