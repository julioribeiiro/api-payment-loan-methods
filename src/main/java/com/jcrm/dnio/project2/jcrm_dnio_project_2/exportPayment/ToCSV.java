package com.jcrm.dnio.project2.jcrm_dnio_project_2.exportPayment;

import com.jcrm.dnio.project2.jcrm_dnio_project_2.databaseSetup.DatabaseLink;
import com.jcrm.dnio.project2.jcrm_dnio_project_2.paymentMethod.PaymentMethod;

public class ToCSV {
    DatabaseLink databaseLink;
    ExporterService paymentExporter;
    String paymentContent = "id, code, description, tax\n";

    public ToCSV(DatabaseLink databaseLink) {
        this.databaseLink = databaseLink;
        try {
            this.paymentExporter = new ExporterService(
                    "/Users/jmoraes/git/jcrm_dnio_project_2/src/main/java/com/jcrm/dnio/project2/jcrm_dnio_project_2/importPayment/paymentMethods.txt");
        } catch (Exception e) {
            System.out.format("\n[ERROR]:[DATA TO CSV]: %s\n", e.getMessage());
        }
    }

    private void paymentMethod() {
        this.databaseLink.query("SELECT * FROM payment_method", (result) -> {
            try {
                Long id = result.getLong("id");
                String code = result.getString("code");
                String description = result.getString("description");
                Double interestRate = result.getDouble("interest_rate");
                Double tax = result.getDouble("tax");

                String row = "" + String.format("'%s', '%s','%s','%s','%s'", id, code, description, interestRate, tax) + "\n";

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
