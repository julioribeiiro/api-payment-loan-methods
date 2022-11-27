package com.jcrm.dnio.project2.jcrm_dnio_project_2;

import com.jcrm.dnio.project2.jcrm_dnio_project_2.databaseSetup.DatabaseLink;
import com.jcrm.dnio.project2.jcrm_dnio_project_2.importPayment.FetchData;
import com.jcrm.dnio.project2.jcrm_dnio_project_2.importPayment.PaymentImporter;
import com.jcrm.dnio.project2.jcrm_dnio_project_2.exportPayment.ToCSV;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JcrmDnioProject2Application {

	public static void main(String[] args) {
		PaymentImporter paymentImporter = new PaymentImporter();

		DatabaseLink databaseLink = new DatabaseLink("jdbc:mysql://localhost:3306/jcrm_dnio", "root", "123456789");

		FetchData dataFrom = new FetchData(paymentImporter, databaseLink);
		dataFrom.execute();

		ToCSV dataTo = new ToCSV(databaseLink);
		dataTo.execute();

//		SpringApplication.run(JcrmDnioProject2Application.class, args);

	}

}
