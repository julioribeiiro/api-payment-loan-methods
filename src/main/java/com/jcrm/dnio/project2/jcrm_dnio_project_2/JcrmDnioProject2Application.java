package com.jcrm.dnio.project2.jcrm_dnio_project_2;

import com.jcrm.dnio.project2.jcrm_dnio_project_2.importExport.DatabaseConnector;
import com.jcrm.dnio.project2.jcrm_dnio_project_2.importExport.FetchData;
import com.jcrm.dnio.project2.jcrm_dnio_project_2.importExport.PaymentImporter;
import com.jcrm.dnio.project2.jcrm_dnio_project_2.importExport.ToCSV;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JcrmDnioProject2Application {

	public static void main(String[] args) {
		PaymentImporter paymentImporter = new PaymentImporter();
		paymentImporter.execute();

		DatabaseConnector databaseConnector = new DatabaseConnector("jdbc:mysql://localhost:3306/jcrm_dnio", "root", "123456789");

		FetchData dataFrom = new FetchData(paymentImporter, databaseConnector);
		dataFrom.execute();

		ToCSV dataTo = new ToCSV(databaseConnector);
		dataTo.execute();

//		SpringApplication.run(JcrmDnioProject2Application.class, args);


	}

}
