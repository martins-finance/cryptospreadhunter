package com.cryptospreadhunter.importer.cexio.message;

public class TickerRequest {

	private String e = "ticker";
	private String[] data;
	private String oid;

	public TickerRequest(String[] data, String oid) {
		this.data = data;
		this.oid = oid;
	}

	public String getE() {
		return e;
	}

	public void setE(String e) {
		this.e = e;
	}

	public String[] getData() {
		return data;
	}

	public void setData(String[] data) {
		this.data = data;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

}
