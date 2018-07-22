package com.cryptospreadhunter.importer.cexio.message;

import java.util.Arrays;

public class MarketDataUpdateResponse {

	private long id;
	private String pair;
	private long time;
	private Double[][] bids;
	private Double[][] asks;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPair() {
		return pair;
	}

	public void setPair(String pair) {
		this.pair = pair;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public Double[][] getBids() {
		return bids;
	}

	public void setBids(Double[][] bids) {
		this.bids = bids;
	}

	public Double[][] getAsks() {
		return asks;
	}

	public void setAsks(Double[][] asks) {
		this.asks = asks;
	}

	private String print(Double[][] array) {
		StringBuilder stringBuilder = new StringBuilder("[");
		for (int i = 0; i < array.length; i++) {
			if (i > 0) {
				stringBuilder.append(", ");
			}
			stringBuilder.append(Arrays.toString(array[i]));
		}
		return stringBuilder.append("]").toString();
	}

	@Override
	public String toString() {
		return "MarketDataUpdateResponse [id=" + id + ", pair=" + pair + ", time=" + time + ", bids=" + print(bids) + ", asks=" + print(asks) + "]";
	}

}
