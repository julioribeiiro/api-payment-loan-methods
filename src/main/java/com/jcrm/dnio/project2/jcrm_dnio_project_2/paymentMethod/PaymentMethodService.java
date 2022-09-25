package com.jcrm.dnio.project2.jcrm_dnio_project_2.paymentMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    @Autowired

    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethodRepository.findAll();
    }

    public void addPaymentMethod(PaymentMethod paymentMethod) throws IllegalAccessException {
        if (paymentMethod.getTax() < 0 || paymentMethod.getTax() > 1) {
            throw new IllegalAccessException("Invalid tax");
        }
        paymentMethodRepository.save(paymentMethod);
    }
}
