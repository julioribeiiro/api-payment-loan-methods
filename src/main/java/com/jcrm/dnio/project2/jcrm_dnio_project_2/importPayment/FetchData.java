package com.jcrm.dnio.project2.jcrm_dnio_project_2.importPayment;


import com.jcrm.dnio.project2.jcrm_dnio_project_2.databaseSetup.DatabaseLink;
import com.jcrm.dnio.project2.jcrm_dnio_project_2.paymentMethod.PaymentMethod;

import java.util.List;

public class FetchData {
    PaymentImporter paymentImporter;
    DatabaseLink databaseLink;

    public FetchData(DatabaseLink databaseLink, String path) {
        this.paymentImporter = new PaymentImporter(path);
        this.databaseLink = databaseLink;
    }

    private void importPaymentMethod() {
        List<PaymentMethod> paymentMethods = paymentImporter.execute();
        this.databaseLink.update("DELETE FROM payment_method;");

        paymentMethods.forEach(paymentMethod -> {
            this.databaseLink
                    .update(
                            String.format(
                                    "INSERT INTO payment_method VALUES (%s);",
                                    paymentMethod.toCSV()));
        });
    }

    public void execute() {
        this.importPaymentMethod();
    }
}
