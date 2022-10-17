package com.jcrm.dnio.project2.jcrm_dnio_project_2.loan;

import com.jcrm.dnio.project2.jcrm_dnio_project_2.paymentItem.PaymentItem;

import javax.persistence.*;
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
    private Long id;
    private String cnpj;
    private Date loanDate;

    private Long bankReference;

    @OneToMany
    private List<PaymentItem> paymentItems;

    public Loan() {
    }

    public Loan(Long id, String cnpj, Date loanDate, List<PaymentItem> paymentItems) {
        this.id = id;
        this.cnpj = cnpj;
        this.loanDate = loanDate;
        this.paymentItems = paymentItems;
    }

    public Loan(String cnpj, Date loanDate, List<PaymentItem> paymentItems) {
        this.cnpj = cnpj;
        this.loanDate = loanDate;
        this.paymentItems = paymentItems;
    }

    public Loan(Long id, String cnpj, Date loanDate, Long bankReference, List<PaymentItem> paymentItems) {
        this.id = id;
        this.cnpj = cnpj;
        this.loanDate = loanDate;
        this.bankReference = bankReference;
        this.paymentItems = paymentItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
