package com.cryptospreadhunter.batch.coinbase.rest;

public enum CoinbaseCurrencyPair {

	BTCUSD("BTC-USD"), LTCUSD("LTC-USD"), ETHUSD("ETH-USD");

	private final String name;

	private CoinbaseCurrencyPair(String s) {
		name = s;
	}

	public String toString() {
		return this.name;
	}
}
