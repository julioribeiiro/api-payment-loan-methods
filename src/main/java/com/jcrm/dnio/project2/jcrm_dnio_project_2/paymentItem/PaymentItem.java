package com.jcrm.dnio.project2.jcrm_dnio_project_2.paymentItem;

import com.jcrm.dnio.project2.jcrm_dnio_project_2.loan.Loan;

import javax.persistence.*;

@Entity
public class PaymentItem {
    @Id
    @SequenceGenerator(
            name = "payment_item_sequence",
            sequenceName = "payment_item_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "payment_item_sequence"
    )
    private Long id;

    @ManyToOne(cascade={CascadeType.PERSIST})
    @JoinColumn(name="loan_id")
    private Loan loan;

    private String paymentMethodCode;

    private double value;

    private Long monthsDeadLine;

    private double taxPaid;

    private double finalValue;

    public PaymentItem(Loan loan, double value, Long monthsDeadLine, double taxPaid) {
        this.loan = loan;
        this.value = value;
        this.monthsDeadLine = monthsDeadLine;
        this.taxPaid = taxPaid;
    }

    public PaymentItem(Long id, String paymentMethodCode, double value, Long monthsDeadLine, double taxPaid, double finalValue) {
        this.id = id;
        this.paymentMethodCode = paymentMethodCode;
        this.value = value;
        this.monthsDeadLine = monthsDeadLine;
        this.taxPaid = taxPaid;
        this.finalValue = finalValue;
    }

    public PaymentItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public String getPaymentMethodCode() {
        return paymentMethodCode;
    }

    public void setPaymentMethodCode(String paymentMethodCode) {
        this.paymentMethodCode = paymentMethodCode;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Long getMonthsDeadLine() {
        return monthsDeadLine;
    }

    public void setMonthsDeadLine(Long monthsDeadLine) {
        this.monthsDeadLine = monthsDeadLine;
    }

    public double getTaxPaid() {
        return taxPaid;
    }

    public void setTaxPaid(double taxPaid) {
        this.taxPaid = taxPaid;
    }

    public double getFinalValue() {
        return finalValue;
    }

    public void setFinalValue(double finalValue) {
        this.finalValue = finalValue;
    }
}
