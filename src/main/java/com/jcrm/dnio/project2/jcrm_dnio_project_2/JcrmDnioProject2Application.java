package com.jcrm.dnio.project2.jcrm_dnio_project_2;

import com.jcrm.dnio.project2.jcrm_dnio_project_2.databaseSetup.DatabaseLink;
import com.jcrm.dnio.project2.jcrm_dnio_project_2.importPayment.FetchData;
import com.jcrm.dnio.project2.jcrm_dnio_project_2.importPayment.PaymentImporter;
import com.jcrm.dnio.project2.jcrm_dnio_project_2.exportPayment.ToCSV;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JcrmDnioProject2Application {

	public static void main(String[] args) {
//		final String databaseUrl = "jdbc:mysql://localhost:3306/jcrm_dnio";
//		final String xmlPath = "/Users/jmoraes/git/jcrm_dnio_project_2/src/main/java/com/jcrm/dnio/project2/jcrm_dnio_project_2/importPayment/data.xml";
//		final String csvUrl = "/Users/jmoraes/git/jcrm_dnio_project_2/src/main/java/com/jcrm/dnio/project2/jcrm_dnio_project_2/importPayment/paymentMethods.txt";
//
//		DatabaseLink databaseLink = new DatabaseLink(databaseUrl, "root", "123456789");
//
//		FetchData dataFrom = new FetchData(databaseLink, xmlPath);
//		dataFrom.execute();
//
//		ToCSV dataTo = new ToCSV(databaseLink, csvUrl);
//		dataTo.execute();

		SpringApplication.run(JcrmDnioProject2Application.class, args);

	}

}
