package com.jcrm.dnio.project2.jcrm_dnio_project_2.loan;

import com.jcrm.dnio.project2.jcrm_dnio_project_2.paymentItem.PaymentItem;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;

import java.time.Instant;

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
    @NotNull
    @Size(max = 14, min = 14, message = "CNPJ need 14 digits")
    private String cnpj;
    @Temporal(TemporalType.DATE)
    @NotNull
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
        if (isCnpj(cnpj)) {
            // System.out.println("Cnpj está válido");
            this.cnpj = cnpj;
        } else {
            System.out.println("Cnpj está inválido");
        }
    }

    public Date getLoanDate() {
        return loanDate;
    }

    @Temporal(TemporalType.DATE)
    public void setLoanDate(Date loanDate) {
        if (loanDate.toInstant().isBefore(Instant.now())) {
            System.out.println("Date needs to be on future");
        } else {
            this.loanDate = loanDate;
        }
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

    private boolean isCnpj(String cnpj) {

        try{
            Long.parseLong(cnpj);
        } catch(NumberFormatException e){
            return false;
        }

        // considera-se erro CNPJ's formados por uma sequencia de numeros iguais
        if (cnpj.equals("00000000000000") || cnpj.equals("11111111111111")
                || cnpj.equals("22222222222222") || cnpj.equals("33333333333333")
                || cnpj.equals("44444444444444") || cnpj.equals("55555555555555")
                || cnpj.equals("66666666666666") || cnpj.equals("77777777777777")
                || cnpj.equals("88888888888888") || cnpj.equals("99999999999999") || (cnpj.length() != 14))
            return (false);
        char dig13, dig14;
        int sm, i, r, num, peso; // "try" - protege o código para eventuais
        // erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i = 11; i >= 0; i--) {
                // converte o i-ésimo caractere do CNPJ em um número: // por
                // exemplo, transforma o caractere '0' no inteiro 0 // (48 eh a
                // posição de '0' na tabela ASCII)
                num = (int) (cnpj.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10)
                    peso = 2;
            }

            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig13 = '0';
            else
                dig13 = (char) ((11 - r) + 48);

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i = 12; i >= 0; i--) {
                num = (int) (cnpj.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10)
                    peso = 2;
            }
            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig14 = '0';
            else
                dig14 = (char) ((11 - r) + 48);
            // Verifica se os dígitos calculados conferem com os dígitos
            // informados.
            if ((dig13 == cnpj.charAt(12)) && (dig14 == cnpj.charAt(13)))
                return (true);
            else
                return (false);
        } catch (InputMismatchException erro) {
            return (false);
        }
    }
}
