package com.jcrm.dnio.project2.jcrm_dnio_project_2.exportPayment;

import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class ExporterService {
    BufferedWriter writer;
    String filePath;

    public ExporterService(String filePath) throws IOException {
        this.writer = new BufferedWriter(new FileWriter(filePath));
    }

    public void execute(String content) throws IOException {
        if (this.writer == null) {
            this.writer = new BufferedWriter(new FileWriter(filePath));
        }
        this.writer.write(content);
        this.writer.close();
        this.writer = null;
    }
}
