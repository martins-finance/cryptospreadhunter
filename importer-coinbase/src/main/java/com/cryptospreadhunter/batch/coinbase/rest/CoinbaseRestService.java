package com.cryptospreadhunter.batch.coinbase.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.cryptospreadhunter.core.model.Price;

@Component
public class CoinbaseRestService {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${coinbase.api.endpoint}")
	private String endpoint;

	@Value("${coinbase.api.key}")
	private String apiKey;

	@Value("${coinbase.api.secret}")
	private String secret;

	public Price getBTCUSDBuyingPrice() {
		RestTemplate restTemplate = new RestTemplate();
		String buyingResourceUrl = endpoint + "/prices/{currency_pair}/buy";
		CoinbasePriceResponse response = restTemplate.getForObject(buyingResourceUrl, CoinbasePriceResponse.class, CoinbaseCurrencyPair.BTCUSD);
		logger.debug(response.toString());
		return new Price(Price.EXCHANGE_COINBASE, Price.OPERATION_BUY, response.getData().getAmount(), Price.CURRENCY_USD, Price.CURRENCY_BTC);
	}

	public Price getBTCUSDSellingPrice() {
		RestTemplate restTemplate = new RestTemplate();
		String buyingResourceUrl = endpoint + "/prices/{currency_pair}/sell";
		CoinbasePriceResponse response = restTemplate.getForObject(buyingResourceUrl, CoinbasePriceResponse.class, CoinbaseCurrencyPair.BTCUSD);
		logger.debug(response.toString());
		return new Price(Price.EXCHANGE_COINBASE, Price.OPERATION_SELL, response.getData().getAmount(), Price.CURRENCY_USD, Price.CURRENCY_BTC);
	}

	public Price getLTCUSDBuyingPrice() {
		RestTemplate restTemplate = new RestTemplate();
		String buyingResourceUrl = endpoint + "/prices/{currency_pair}/buy";
		CoinbasePriceResponse response = restTemplate.getForObject(buyingResourceUrl, CoinbasePriceResponse.class, CoinbaseCurrencyPair.LTCUSD);
		logger.debug(response.toString());
		return new Price(Price.EXCHANGE_COINBASE, Price.OPERATION_BUY, response.getData().getAmount(), Price.CURRENCY_USD, Price.CURRENCY_LTC);
	}

	public Price getLTCUSDSellingPrice() {
		RestTemplate restTemplate = new RestTemplate();
		String buyingResourceUrl = endpoint + "/prices/{currency_pair}/sell";
		CoinbasePriceResponse response = restTemplate.getForObject(buyingResourceUrl, CoinbasePriceResponse.class, CoinbaseCurrencyPair.LTCUSD);
		logger.debug(response.toString());
		return new Price(Price.EXCHANGE_COINBASE, Price.OPERATION_SELL, response.getData().getAmount(), Price.CURRENCY_USD, Price.CURRENCY_LTC);
	}

	public Price getETHUSDBuyingPrice() {
		RestTemplate restTemplate = new RestTemplate();
		String buyingResourceUrl = endpoint + "/prices/{currency_pair}/buy";
		CoinbasePriceResponse response = restTemplate.getForObject(buyingResourceUrl, CoinbasePriceResponse.class, CoinbaseCurrencyPair.ETHUSD);
		logger.debug(response.toString());
		return new Price(Price.EXCHANGE_COINBASE, Price.OPERATION_BUY, response.getData().getAmount(), Price.CURRENCY_USD, Price.CURRENCY_ETH);
	}

	public Price getETHUSDSellingPrice() {
		RestTemplate restTemplate = new RestTemplate();
		String buyingResourceUrl = endpoint + "/prices/{currency_pair}/sell";
		CoinbasePriceResponse response = restTemplate.getForObject(buyingResourceUrl, CoinbasePriceResponse.class, CoinbaseCurrencyPair.ETHUSD);
		logger.debug(response.toString());
		return new Price(Price.EXCHANGE_COINBASE, Price.OPERATION_SELL, response.getData().getAmount(), Price.CURRENCY_USD, Price.CURRENCY_ETH);
	}
}
