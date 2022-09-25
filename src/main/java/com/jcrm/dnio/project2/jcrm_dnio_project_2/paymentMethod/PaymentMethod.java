package com.jcrm.dnio.project2.jcrm_dnio_project_2.paymentMethod;

import javax.persistence.*;

// usando spring data JPA para criar tabela no nosso banco de dados
@Entity
@Table(name = "payment_method")
public class PaymentMethod {
    // criamos uma sequencia chamada payment_method_sequence que comeca em 1 e incrementa 1
    @Id
    @SequenceGenerator(
            name = "payment_method_sequence",
            sequenceName = "payment_method_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "payment_method_sequence"
    )
    private Long id;
    @Column(length = 6)
    private String code;
    @Column(length = 50, nullable = false)
    private String description;
    private float interestRate;
    private float tax;

    public PaymentMethod(String code, String description, float interestRate, float tax) {
        this.code = code;
        this.description = description;
        this.interestRate = interestRate;
        this.tax = tax;
    }

    public PaymentMethod(Long id, String code, String description, float interestRate, float tax) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.interestRate = interestRate;
        this.tax = tax;
    }

    public PaymentMethod() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
    }

    public float getTax() {
        return tax;
    }

    public void setTax(float tax) {
        this.tax = tax;
    }
}
