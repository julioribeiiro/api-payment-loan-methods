package com.jcrm.dnio.project2.jcrm_dnio_project_2.loan;

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
        if (loanRepository.existsByCnpj(loan.getCnpj())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(loanRepository.save(loan), HttpStatus.CREATED);
    }

    public ResponseEntity<List<Loan>> getAllLoans() {
        return new ResponseEntity<>(loanRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<String> deletePaymentMethod(Long id) {
        if (!loanRepository.existsById(id)) {
            return new ResponseEntity<>("Loan's id does not exist", HttpStatus.BAD_REQUEST);
        }
        loanRepository.deleteById(id);
        return new ResponseEntity<>("Loan successfully deleted", HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Loan> updateLoan(Loan loan) {
        return new ResponseEntity<>(loanRepository.save(loan), HttpStatus.OK);
    }

}
