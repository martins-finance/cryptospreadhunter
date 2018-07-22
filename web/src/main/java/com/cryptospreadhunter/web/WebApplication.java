package com.cryptospreadhunter.web;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

import com.cryptospreadhunter.web.listener.PriceChangesListener;
import com.cryptospreadhunter.core.CoreConfiguration;

@EnableAsync
@SpringBootApplication
@Import(CoreConfiguration.class)
public class WebApplication implements InitializingBean {

	@Autowired
	private PriceChangesListener chatChangesListener;

	@Override
	public void afterPropertiesSet() throws Exception {
		chatChangesListener.pushChangesToWebSocket();
	}

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}
}
