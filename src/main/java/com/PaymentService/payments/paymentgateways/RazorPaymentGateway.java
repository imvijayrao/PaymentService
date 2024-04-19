package com.PaymentService.payments.paymentgateways;

import com.PaymentService.payments.models.PaymentStatus;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RazorPaymentGateway implements PaymentGatewayStrategyPattern{

    @Autowired
    private RazorpayClient razorpayClient;



    @Override
    public String createPaymentLink(Long amount, String username, String userEmail, String userPhone, Long orderId) {

        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount",amount);
        paymentLinkRequest.put("currency","INR");
        paymentLinkRequest.put("accept_partial",false);
//        paymentLinkRequest.put("first_min_partial_amount",100);
        paymentLinkRequest.put("expire_by",System.currentTimeMillis()/1000 + 30 * 60); // epoch timestamp
        paymentLinkRequest.put("reference_id",orderId);
//        paymentLinkRequest.put("description","");
        JSONObject customer = new JSONObject();
        customer.put("name",userPhone);
        customer.put("contact",username);
        customer.put("email",userEmail);
        paymentLinkRequest.put("customer",customer);
        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);
        paymentLinkRequest.put("reminder_enable",true);
//        JSONObject notes = new JSONObject();
//        notes.put("policy_name","Jeevan Bima");
//        paymentLinkRequest.put("notes",notes);
        paymentLinkRequest.put("callback_url","https://scaler.com/");
        paymentLinkRequest.put("callback_method","get");

        PaymentLink payment = null;

        try {
            payment = razorpayClient.paymentLink.create(paymentLinkRequest);
        } catch (RazorpayException exception) {
            System.out.println(exception);
            System.out.println("Something went wrong");
        }

        return payment.toString();
    }

    @Override
    public PaymentStatus getPaymentStatus(Long paymentId) {
        return null;
    }
}
