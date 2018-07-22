package com.cryptospreadhunter.importer.cexio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.cryptospreadhunter.core.CoreConfiguration;

@SpringBootApplication
@Import(CoreConfiguration.class)
public class CexioImporterApplication implements CommandLineRunner {

	@Autowired
	private CeoixImporter ceoixImporter;

	@Override
	public void run(String... args) {
		ceoixImporter.start();
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(CexioImporterApplication.class, args);
	}
}
