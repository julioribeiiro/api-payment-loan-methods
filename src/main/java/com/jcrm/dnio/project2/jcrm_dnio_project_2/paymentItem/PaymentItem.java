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

    public PaymentItem(Long id, Loan loan, String paymentMethodCode, double value, Long monthsDeadLine, double taxPaid, double finalValue) {
        this.id = id;
        this.loan = loan;
        this.paymentMethodCode = paymentMethodCode;
        this.value = value;
        this.monthsDeadLine = monthsDeadLine;
        this.taxPaid = taxPaid;
        this.finalValue = finalValue;
    }

    public PaymentItem() {
    }
}
