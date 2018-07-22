package com.cryptospreadhunter.importer.cexio.message;

import java.util.Vector;

public class TickerResponse {

	private long timestamp;
	private Double low;
	private Double high;
	private Double last;
	private String volume;
	private String volum30d;
	private Double bid;
	private Double ask;
	private Vector<String> pair;

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public Double getLow() {
		return low;
	}

	public void setLow(Double low) {
		this.low = low;
	}

	public Double getHigh() {
		return high;
	}

	public void setHigh(Double high) {
		this.high = high;
	}

	public Double getLast() {
		return last;
	}

	public void setLast(Double last) {
		this.last = last;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getVolum30d() {
		return volum30d;
	}

	public void setVolum30d(String volum30d) {
		this.volum30d = volum30d;
	}

	public Double getBid() {
		return bid;
	}

	public void setBid(Double bid) {
		this.bid = bid;
	}

	public Double getAsk() {
		return ask;
	}

	public void setAsk(Double ask) {
		this.ask = ask;
	}

	public Vector<String> getPair() {
		return pair;
	}

	public void setPair(Vector<String> pair) {
		this.pair = pair;
	}

	@Override
	public String toString() {
		return "TickerResponse [timestamp=" + timestamp + ", low=" + low + ", high=" + high + ", last=" + last + ", volume=" + volume + ", volum30d=" + volum30d + ", bid=" + bid + ", ask=" + ask
				+ ", pair=" + pair + "]";
	}

}
