package com.cryptospreadhunter.batch.gemini.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeminiPubTickerResponse {

	private Double ask;
	private Double bid;

	public Double getAsk() {
		return ask;
	}

	public void setAsk(Double ask) {
		this.ask = ask;
	}

	public Double getBid() {
		return bid;
	}

	public void setBid(Double bid) {
		this.bid = bid;
	}

	@Override
	public String toString() {
		return "GeminiPubTickerResponse [ask=" + ask + ", bid=" + bid + "]";
	}

}
