package com.jcrm.dnio.project2.jcrm_dnio_project_2.importPayment;

import com.jcrm.dnio.project2.jcrm_dnio_project_2.paymentMethod.PaymentMethod;

import java.util.ArrayList;
import java.util.List;

public class PaymentImporter {
    private ImporterService importer;
    private List<PaymentMethod> paymentMethods;

    public PaymentImporter() {
        this.paymentMethods = new ArrayList<PaymentMethod>();

        this.importer = new ImporterService("/Users/jmoraes/git/jcrm_dnio_project_2/src/main/java/com/jcrm/dnio/project2/jcrm_dnio_project_2/importPayment/data.txt",
                (String t, String v) -> {

                    if (t.compareTo("paymentMethod") == 0) {
                        this.paymentMethods.add(new PaymentMethod());
                    }

                    if (t.compareTo("paymentMethod") != 0 && !t.isEmpty() && !v.isEmpty()
                            && this.paymentMethods.size() > 0) {
                        fillPaymentMethod(this.paymentMethods.get(paymentMethods.size() - 1), t, v);
                        System.out.format("%s: %s\n", t, v);
                    }
                });
    }

    void fillPaymentMethod(PaymentMethod paymentMethod, String attr, String value) {
        switch (attr){
            case "id":
                paymentMethod.setId(Long.parseLong(value));
                break;
            case "code":
                paymentMethod.setCode(value);
                break;
            case "description":
                paymentMethod.setDescription(value);
                break;
            case "interest_rate":
                paymentMethod.setInterestRate(Double.parseDouble(value));
                break;
            case "tax":
                paymentMethod.setTax(Double.parseDouble(value));
                break;
        }
    }

    public List<PaymentMethod> execute() {
        this.importer.execute();

        return this.paymentMethods;
    }
}

