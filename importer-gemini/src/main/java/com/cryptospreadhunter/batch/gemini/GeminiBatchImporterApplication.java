package com.cryptospreadhunter.batch.gemini;

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

import com.cryptospreadhunter.batch.gemini.rest.GeminiRestService;
import com.cryptospreadhunter.core.CoreConfiguration;

@SpringBootApplication
@EnableBatchProcessing
@Import(CoreConfiguration.class)
public class GeminiBatchImporterApplication {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private GeminiRestService geminiRestService;

	@Bean
	public Job job(Step geminiStep1) throws Exception {
		return jobBuilderFactory.get("geminiJob").incrementer(new RunIdIncrementer()).start(geminiStep1).build();
	}

	@Bean
	public Step geminiStep1() {
		return stepBuilderFactory.get("geminiStep1").tasklet(new Tasklet() {
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				geminiRestService.processBTCUSDPrice();
				geminiRestService.processETHUSDPrice();
				return RepeatStatus.CONTINUABLE;
			}
		}).build();
	}

	public static void main(String[] args) {
		System.exit(SpringApplication.exit(SpringApplication.run(GeminiBatchImporterApplication.class, args)));
	}
}
