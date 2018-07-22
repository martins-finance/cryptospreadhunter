package com.cryptospreadhunter.batch.coinbase.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinbasePriceResponse {

	private CoinbasePrice data;

	public CoinbasePrice getData() {
		return data;
	}

	public void setData(CoinbasePrice data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "CoinbasePriceResponse [data=" + data + "]";
	}

}
