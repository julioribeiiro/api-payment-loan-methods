package com.jcrm.dnio.project2.jcrm_dnio_project_2.paymentMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    @Autowired
    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    public ResponseEntity<List<PaymentMethod>> getPaymentMethods() {
        return new ResponseEntity<>(paymentMethodRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<PaymentMethod> addPaymentMethod(PaymentMethod paymentMethod) {
        if (paymentMethod.getTax() < 0 || paymentMethod.getTax() > 1 || paymentMethod.getInterestRate() < 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(paymentMethodRepository.save(paymentMethod), HttpStatus.CREATED);
    }

    public ResponseEntity<Optional<PaymentMethod>> getPaymentMethod(String code) {
        return new ResponseEntity<>(paymentMethodRepository.findPaymentMethodByCode(code), HttpStatus.OK);
    }

    public ResponseEntity<String> deletePaymentMethod(Long id) {
        if (!paymentMethodRepository.existsById(id)) {
            return new ResponseEntity<>("Payment Method's id does not exist", HttpStatus.BAD_REQUEST);
        }
        paymentMethodRepository.deleteById(id);
        return new ResponseEntity<>("Payment Method successfully deleted", HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<PaymentMethod> updatePaymentMethod(PaymentMethod paymentMethod) {
        if (paymentMethod.getTax() < 0 || paymentMethod.getTax() > 1 || paymentMethod.getInterestRate() < 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(paymentMethodRepository.save(paymentMethod), HttpStatus.OK);
    }
}
