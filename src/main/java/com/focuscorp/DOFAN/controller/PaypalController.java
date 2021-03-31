package com.focuscorp.DOFAN.controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.focuscorp.DOFAN.paypal.PaypalService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PaypalController {

    @Autowired
    PaypalService service;

    public static final String SUCCESS_URL = "success";
    public static final String CANCEL_URL = "cancel";


    public String payment(double price) {
        String cancelUrl = "http://localhost:8102/" + CANCEL_URL;
        String successUrl = "http://localhost:8102/" + SUCCESS_URL;
        try {
            Payment payment = service.createPayment(
                    price,
                    "USD",
                    "paypal",
                    "sale",
                    "pack payment",
                    cancelUrl,
                    successUrl);

            for(Links link:payment.getLinks()) {
                if(link.getRel().equals("approval_url")) {
                    return "redirect:"+link.getHref();
                }
            }

        } catch (PayPalRESTException e) {

            e.printStackTrace();
        }
        return "redirect:/";
    }

 /*   @RequestMapping(method = RequestMethod.POST, value = "pay")
    public String payment() {
        String cancelUrl = "http://localhost:8102/" + CANCEL_URL;
        String successUrl = "http://localhost:8102/" + SUCCESS_URL;
        try {
            Payment payment = service.createPayment(
                    4.00,
                    "USD",
                    "paypal",
                    "sale",
                    "pack payment",
                    cancelUrl,
                    successUrl);

            for(Links link:payment.getLinks()) {
                if(link.getRel().equals("approval_url")) {
                    return "redirect:"+link.getHref();
                }
            }

        } catch (PayPalRESTException e) {

            e.printStackTrace();
        }
        return "redirect:/";
    }*/

    @RequestMapping(method = RequestMethod.GET, value = CANCEL_URL)
    public String cancelPay() {
        return "cancel";
    }

    @RequestMapping(method = RequestMethod.GET, value = SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = service.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                return "success";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/";
    }

}
