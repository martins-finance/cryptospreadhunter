package com.cryptospreadhunter.batch.coinbase.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinbasePrice {

	private Double amount;
	private String currency;
	private String base;

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Override
	public String toString() {
		return "CoinbasePrice [amount=" + amount + ", currency=" + currency + ", base=" + base + "]";
	}

}
