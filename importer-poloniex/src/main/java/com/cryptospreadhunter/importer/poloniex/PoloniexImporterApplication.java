package com.cryptospreadhunter.importer.poloniex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.cryptospreadhunter.core.CoreConfiguration;

@SpringBootApplication
@Import(CoreConfiguration.class)
public class PoloniexImporterApplication implements CommandLineRunner {

	@Autowired
	private PoloniexImporter poloniexImporter;

	@Override
	public void run(String... args) {
		poloniexImporter.start();
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(PoloniexImporterApplication.class, args);
	}
}
