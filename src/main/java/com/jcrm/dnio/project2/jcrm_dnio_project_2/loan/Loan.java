package com.jcrm.dnio.project2.jcrm_dnio_project_2.loan;

import com.jcrm.dnio.project2.jcrm_dnio_project_2.paymentItem.PaymentItem;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "loan")
public class Loan {
    @Id
    @SequenceGenerator(
            name = "loan_sequence",
            sequenceName = "loan_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "loan_sequence"
    )
    private Long bankReference;
    private String cnpj;
    private Date loanDate;

    @OneToMany(mappedBy = "loan", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<PaymentItem> paymentItems = new ArrayList<>();

    public Loan() {
    }

    public Loan(String cnpj, Date loanDate) {
        this.cnpj = cnpj;
        this.loanDate = loanDate;
    }

    public Loan(String cnpj, Date loanDate, List<PaymentItem> paymentItems) {
        this.cnpj = cnpj;
        this.loanDate = loanDate;
        this.paymentItems = paymentItems;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    @Temporal(TemporalType.DATE)
    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Long getBankReference() {
        return bankReference;
    }

    public void setBankReference(Long bankReference) {
        this.bankReference = bankReference;
    }

    public List<PaymentItem> getPaymentItems() {
        return paymentItems;
    }

    public void setPaymentItems(List<PaymentItem> paymentItems) {
        this.paymentItems = paymentItems;
    }
}
