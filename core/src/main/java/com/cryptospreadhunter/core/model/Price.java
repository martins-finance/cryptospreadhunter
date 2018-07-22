package com.cryptospreadhunter.core.model;

import java.time.OffsetDateTime;

public class Price {

	public final static String CURRENCY_USD = "USD";
	public final static String CURRENCY_BRL = "BRL";

	public final static String CURRENCY_BTC = "BTC";
	public final static String CURRENCY_LTC = "LTC";
	public static final String CURRENCY_ETH = "ETH";
	public final static String CURRENCY_XPM = "XPM";
	public final static String CURRENCY_ETC = "ETC";
	public final static String CURRENCY_BCH = "BCH";
	public final static String CURRENCY_OMG = "OMG";
	public final static String CURRENCY_GNT = "GNT";
	public final static String CURRENCY_STR = "STR";
	public final static String CURRENCY_FLO = "FLO";
	public final static String CURRENCY_NMC = "NMC";
	public final static String CURRENCY_BELA = "BELA";
	public final static String CURRENCY_POT = "POT";
	public final static String CURRENCY_XCP = "XCP";
	public final static String CURRENCY_XMR = "XMR";
	public final static String CURRENCY_ZRX = "ZRX";
	public final static String CURRENCY_EMC2 = "EMC2";
	public final static String CURRENCY_PPC = "PPC";
	public final static String CURRENCY_XBC = "XBC";
	public final static String CURRENCY_FCT = "FCT";
	public final static String CURRENCY_ZEC = "ZEC";
	public final static String CURRENCY_DASH = "DASH";
	public final static String CURRENCY_PASC = "PASC";
	public final static String CURRENCY_BCN = "BCN";
	public final static String CURRENCY_BLK = "BLK";
	public final static String CURRENCY_BTCD = "BTCD";
	public final static String CURRENCY_BTS = "BTS";
	public final static String CURRENCY_BURST = "BURST";
	public final static String CURRENCY_SYS = "SYS";
	public final static String CURRENCY_CLAM = "CLAM";
	public final static String CURRENCY_DGB = "DGB";
	public final static String CURRENCY_DOGE = "DOGE";
	public final static String CURRENCY_GAME = "GAME";
	public final static String CURRENCY_MAID = "MAID";
	public final static String CURRENCY_NAV = "NAV";
	public final static String CURRENCY_NXT = "NXT";
	public final static String CURRENCY_RIC = "RIC";
	public final static String CURRENCY_VIA = "VIA";
	public final static String CURRENCY_VRC = "VRC";
	public final static String CURRENCY_XRP = "XRP";
	public final static String CURRENCY_XEM = "XEM";
	public final static String CURRENCY_SC = "SC";
	public final static String CURRENCY_EXP = "EXP";
	public final static String CURRENCY_RADS = "RADS";
	public final static String CURRENCY_DCR = "DCR";
	public final static String CURRENCY_LSK = "LSK";
	public final static String CURRENCY_LBC = "LBC";
	public final static String CURRENCY_STEEM = "STEEM";
	public final static String CURRENCY_STRAT = "STRAT";
	public final static String CURRENCY_REP = "REP";
	public final static String CURRENCY_ARDR = "ARDR";
	public final static String CURRENCY_NXC = "NXC";
	public final static String CURRENCY_CVC = "CVC";
	public final static String CURRENCY_GAS = "GAS";
	public final static String CURRENCY_VTC = "VTC";
	public final static String CURRENCY_PINK = "PINK";
	public final static String CURRENCY_NEOS = "NEOS";
	public final static String CURRENCY_GNO = "GNO";

	public final static String OPERATION_BUY = "BUY";
	public final static String OPERATION_SELL = "SELL";

	public final static String EXCHANGE_COINBASE = "COINBASE";
	public final static String EXCHANGE_GEMINI = "GEMINI";
	public final static String EXCHANGE_POLONIEX = "POLONIEX";
	public final static String EXCHANGE_CEXIO = "CEXIO";

	private String id;
	private String exchange;
	private String operation;
	private String currency;
	private String base;
	private Number amount;
	private OffsetDateTime time;

	public Price() {
	};

	public Price(String exchange, String operation, Number amount, String currency, String base) {
		this.exchange = exchange;
		this.operation = operation;
		this.amount = amount;
		this.currency = currency;
		this.base = base;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Number getAmount() {
		return amount;
	}

	public void setAmount(Number amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public OffsetDateTime getTime() {
		return time;
	}

	public void setTime(OffsetDateTime time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Price [id=" + id + ", exchange=" + exchange + ", operation=" + operation + ", currency=" + currency + ", base=" + base + ", amount=" + amount + ", time=" + time + "]";
	}

}
