package com.cryptospreadhunter.batch.gemini.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.cryptospreadhunter.core.model.Price;
import com.cryptospreadhunter.core.service.PriceService;

@Component
public class GeminiRestService {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${gemini.api.endpoint}")
	private String endpoint;

	@Autowired
	private PriceService priceService;

	public void processBTCUSDPrice() {
		RestTemplate restTemplate = new RestTemplate();
		String buyingResourceUrl = endpoint + "/pubticker/{symbol}";
		GeminiPubTickerResponse response = restTemplate.getForObject(buyingResourceUrl, GeminiPubTickerResponse.class, GeminiSymbol.btcusd);
		logger.debug(response.toString());
		priceService.insert(new Price(Price.EXCHANGE_GEMINI, Price.OPERATION_BUY, response.getBid(), Price.CURRENCY_USD, Price.CURRENCY_BTC));
		priceService.insert(new Price(Price.EXCHANGE_GEMINI, Price.OPERATION_SELL, response.getAsk(), Price.CURRENCY_USD, Price.CURRENCY_BTC));
	}

	public void processETHUSDPrice() {
		RestTemplate restTemplate = new RestTemplate();
		String buyingResourceUrl = endpoint + "/pubticker/{symbol}";
		GeminiPubTickerResponse response = restTemplate.getForObject(buyingResourceUrl, GeminiPubTickerResponse.class, GeminiSymbol.ethusd);
		logger.debug(response.toString());
		priceService.insert(new Price(Price.EXCHANGE_GEMINI, Price.OPERATION_BUY, response.getBid(), Price.CURRENCY_USD, Price.CURRENCY_ETH));
		priceService.insert(new Price(Price.EXCHANGE_GEMINI, Price.OPERATION_SELL, response.getAsk(), Price.CURRENCY_USD, Price.CURRENCY_ETH));
	}
}
