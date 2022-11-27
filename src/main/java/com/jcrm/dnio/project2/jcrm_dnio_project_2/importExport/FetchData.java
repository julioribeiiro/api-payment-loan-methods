package com.jcrm.dnio.project2.jcrm_dnio_project_2.importExport;


import com.jcrm.dnio.project2.jcrm_dnio_project_2.paymentMethod.PaymentMethod;

import java.util.List;

public class FetchData {
    PaymentImporter paymentImporter;
    DatabaseConnector databaseConnector;

    public FetchData(PaymentImporter paymentImporter, DatabaseConnector databaseConnector) {
        this.paymentImporter = paymentImporter;
        this.databaseConnector = databaseConnector;
    }

    private void importPaymentMethod() {
        List<PaymentMethod> paymentMethods = paymentImporter.execute();
        this.databaseConnector.update("DELETE FROM payment_method;");

        paymentMethods.forEach(paymentMethod -> {
            this.databaseConnector
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
