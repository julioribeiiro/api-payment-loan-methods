package com.jcrm.dnio.project2.jcrm_dnio_project_2.paymentMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/payment_method")
@CrossOrigin("*")
public class PaymentMethodController {

    @Autowired
    private final PaymentMethodService paymentMethodService;

    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @GetMapping
    public ResponseEntity<List<PaymentMethod>> getPaymentMethods() {
        return paymentMethodService.getPaymentMethods();
    }

    @PostMapping
    public ResponseEntity<PaymentMethod> addPaymentMethod(@RequestBody PaymentMethod paymentMethod) {
        return paymentMethodService.addPaymentMethod(paymentMethod);
    }

    @GetMapping(path = "{code}")
    public ResponseEntity<Optional<PaymentMethod>> getPaymentMethod(@PathVariable("code") String code) {
        return paymentMethodService.getPaymentMethod(code);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> deletePaymentMethod(@PathVariable("id") Long id) {
        return paymentMethodService.deletePaymentMethod(id);
    }

    @PutMapping(path = "edit")
    public ResponseEntity<PaymentMethod> updatePaymentMethod(@RequestBody PaymentMethod paymentMethod) {
        return paymentMethodService.updatePaymentMethod(paymentMethod);
    }

}
