package com.cryptospreadhunter.batch.coinbase;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.cryptospreadhunter.batch.coinbase.rest.CoinbaseRestService;
import com.cryptospreadhunter.core.CoreConfiguration;
import com.cryptospreadhunter.core.service.PriceService;

@SpringBootApplication
@EnableBatchProcessing
@Import(CoreConfiguration.class)
public class CoinbaseBatchImporterApplication {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private CoinbaseRestService coinbaseRestService;

	@Autowired
	private PriceService priceService;

	@Bean
	public Job coinbaseJob(Step coinbaseStep1) throws Exception {
		return jobBuilderFactory.get("coinbaseJob").incrementer(new RunIdIncrementer()).start(coinbaseStep1).build();
	}

	@Bean
	public Step coinbaseStep1() {
		return stepBuilderFactory.get("coinbaseStep1").tasklet(new Tasklet() {
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				priceService.insert(coinbaseRestService.getBTCUSDBuyingPrice());
				priceService.insert(coinbaseRestService.getBTCUSDSellingPrice());
				priceService.insert(coinbaseRestService.getLTCUSDBuyingPrice());
				priceService.insert(coinbaseRestService.getLTCUSDSellingPrice());
				priceService.insert(coinbaseRestService.getETHUSDBuyingPrice());
				priceService.insert(coinbaseRestService.getETHUSDSellingPrice());
				return RepeatStatus.CONTINUABLE;
			}
		}).build();
	}

	public static void main(String[] args) {
		System.exit(SpringApplication.exit(SpringApplication.run(CoinbaseBatchImporterApplication.class, args)));
	}
}
