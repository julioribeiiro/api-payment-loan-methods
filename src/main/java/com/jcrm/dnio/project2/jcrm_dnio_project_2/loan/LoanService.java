package com.jcrm.dnio.project2.jcrm_dnio_project_2.loan;

import com.jcrm.dnio.project2.jcrm_dnio_project_2.paymentMethod.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {
    private final LoanRepository loanRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public ResponseEntity<Loan> addLoan(Loan loan) {
//        if (paymentMethod.getTax() < 0 || paymentMethod.getTax() > 1 || paymentMethod.getInterestRate() < 0) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
        return new ResponseEntity<>(loanRepository.save(loan), HttpStatus.CREATED);
    }

    public ResponseEntity<List<Loan>> getAllLoans() {
        return new ResponseEntity<>(loanRepository.findAll(), HttpStatus.OK);
    }
}
