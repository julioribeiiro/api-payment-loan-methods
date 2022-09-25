package com.jcrm.dnio.project2.jcrm_dnio_project_2.paymentMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/payment_method")
public class PaymentMethodController {

    @Autowired
    private final PaymentMethodService paymentMethodService;

    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @GetMapping
    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethodService.getPaymentMethods();
    }

    @PostMapping
    public void addPaymentMethod(@RequestBody PaymentMethod paymentMethod) throws IllegalAccessException {
        paymentMethodService.addPaymentMethod(paymentMethod);
    }

}
