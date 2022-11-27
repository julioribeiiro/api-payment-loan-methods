package com.jcrm.dnio.project2.jcrm_dnio_project_2.paymentMethod;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

// usando spring data JPA para criar tabela no nosso banco de dados
@Entity
@Table (name = "payment_method")
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


    @Size(min = 1, max = 50, message = "Description length max is 50 and cannot be blank")
    @NotNull
    private String description;

    @Size(min = 6, max = 6, message = "Code length need to be 6")
    @NotNull
    private String code;

    @DecimalMin(value = "0.0", message = "Interest Rate can't be lower than 0")
    @NotNull
    private Double interestRate;

    @DecimalMin(value = "0.0", message = "Tax can't be lower than 0")
    @DecimalMax(value = "1.0", message = "Tax can't be greater than 1")
    @NotNull
    private Double tax;

    public PaymentMethod(String code, String description, Double interestRate, Double tax) {
        this.code = code;
        this.description = description;
        this.interestRate = interestRate;
        this.tax = tax;
    }

    public PaymentMethod(Long id, String code, String description, double interestRate, double tax) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.interestRate = interestRate;
        this.tax = tax;
    }

    public PaymentMethod(Long id) {
        this.id = id;
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

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public String toCSV() {
        return String.format("'%s', '%s','%s','%s','%s'", this.id, this.code,
                this.description, this.interestRate, this.tax);
    }
}
