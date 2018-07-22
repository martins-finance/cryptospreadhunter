package com.cryptospreadhunter.importer.cexio.message;

import java.util.Arrays;

public class OrderBookSubscriptionRequest {

	private String e = "order-book-subscribe";
	private OrderBookSubscriptionData data;
	private String oid;

	public OrderBookSubscriptionRequest(String[] pair, boolean subscribe, int depth, String oid) {
		this.data = new OrderBookSubscriptionData(pair, subscribe, depth);
		this.oid = oid;
	}

	public String getE() {
		return e;
	}

	public void setE(String e) {
		this.e = e;
	}

	public OrderBookSubscriptionData getData() {
		return data;
	}

	public void setData(OrderBookSubscriptionData data) {
		this.data = data;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

}

class OrderBookSubscriptionData {
	private String[] pair;
	private boolean subscribe;
	private int depth;

	public OrderBookSubscriptionData(String[] pair, boolean subscribe, int depth) {
		this.pair = pair;
		this.subscribe = subscribe;
		this.depth = depth;
	}

	public String[] getPair() {
		return pair;
	}

	public void setPair(String[] pair) {
		this.pair = pair;
	}

	public boolean isSubscribe() {
		return subscribe;
	}

	public void setSubscribe(boolean subscribe) {
		this.subscribe = subscribe;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	@Override
	public String toString() {
		return "OrderBookSubscriptionData [pair=" + Arrays.toString(pair) + ", subscribe=" + subscribe + ", depth=" + depth + "]";
	}

}
