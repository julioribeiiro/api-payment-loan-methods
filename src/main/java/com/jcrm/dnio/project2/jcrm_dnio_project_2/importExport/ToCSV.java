package com.jcrm.dnio.project2.jcrm_dnio_project_2.importExport;

import com.jcrm.dnio.project2.jcrm_dnio_project_2.paymentMethod.PaymentMethod;

public class ToCSV {
    DatabaseConnector databaseConnector;
    ExporterService paymentExporter;
    String paymentContent = "id, aliquot, estimated_value, item_name\n";

    public ToCSV(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
        try {
            this.paymentExporter = new ExporterService(
                    "/Users/jmoraes/git/jcrm_dnio_project_2/src/main/java/com/jcrm/dnio/project2/jcrm_dnio_project_2/importExport/paymentMethods.txt");
        } catch (Exception e) {
            System.out.format("\n[ERROR]:[DATA TO CSV]: %s\n", e.getMessage());
        }
    }

    private void paymentMethod() {
        this.databaseConnector.query("SELECT * FROM payment_method", (result) -> {
            try {
                Long id = result.getLong("id");
                String code = result.getString("code");
                String description = result.getString("description");
                Double interestRate = result.getDouble("interest_rate");
                Double tax = result.getDouble("tax");

                PaymentMethod paymentMethod = new PaymentMethod(id, code, description, interestRate, tax);

                String row = "" + paymentMethod.toCSV() + "\n";

                this.paymentContent = paymentContent + row;
            } catch (Exception e) {
                System.out.println(e);
            }

        });
        try {
            this.paymentExporter.execute(this.paymentContent);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void execute() {
        this.paymentMethod();
    }
}
