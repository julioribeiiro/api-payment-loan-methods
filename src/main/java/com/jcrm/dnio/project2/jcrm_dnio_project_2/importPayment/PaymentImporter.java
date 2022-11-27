package com.jcrm.dnio.project2.jcrm_dnio_project_2.importPayment;

import com.jcrm.dnio.project2.jcrm_dnio_project_2.paymentMethod.PaymentMethod;

import java.util.ArrayList;
import java.util.List;

public class PaymentImporter {
    private ImporterService importer;
    private List<String> foundPaymentStrings;

    public PaymentImporter() {
        this.foundPaymentStrings = new ArrayList<String>();
        this.importer = new ImporterService("/Users/jmoraes/git/jcrm_dnio_project_2/src/main/java/com/jcrm/dnio/project2/jcrm_dnio_project_2/importExport/data.txt",
                (String t, String v) -> {

                    if (t.compareTo("paymentMethod") == 0) {
                        this.foundPaymentStrings.add("");
                    }

                    if (t.compareTo("paymentMethod") != 0 && !t.isEmpty() && !v.isEmpty()
                            && this.foundPaymentStrings.size() > 0) {
                        this.foundPaymentStrings.set(this.foundPaymentStrings.size() - 1,
                                this.foundPaymentStrings.get(this.foundPaymentStrings.size() - 1)
                                        + String.format("%s=%s;", t, v));
                        System.out.format("t: %s v: %s\n", t, v);
                    }
                }, java.util.Optional.empty());
    }

    String[] getKeyValue(String value) {
        return value.split("=");
    }

    PaymentMethod getPayment(String paymentStr) {
        String[] attrs = paymentStr.split(";");

        String code = "";
        String description = "";
        Double interestRate = 0.0;
        Double tax = 0.0;
        Long id = 0L;

        for (String keyValue : attrs) {
            String[] attr = getKeyValue(keyValue);
            String key = attr[0];
            String value = attr[1];

            if (key.equals("id")) {
                id = Long.parseLong(value);
            }

            if (key.equals("code")) {
                code = value;
            }

            if (key.equals("description")) {
                description = value;
            }

            if (key.equals("interest_rate")) {
                interestRate = Double.parseDouble(value);
            }

            if (key.equals("tax")) {
                tax = Double.parseDouble(value);
            }
        }

        return new PaymentMethod(id, code, description, interestRate, tax);
    }

    public List<PaymentMethod> execute() {
        List<PaymentMethod> paymentMethods = new ArrayList<PaymentMethod>();
        this.importer.execute();

        this.foundPaymentStrings.forEach(paymentString -> {
            PaymentMethod newPayment = getPayment(paymentString);
            paymentMethods.add(newPayment);
        });

        this.foundPaymentStrings.clear();

        return paymentMethods;
    }
}

