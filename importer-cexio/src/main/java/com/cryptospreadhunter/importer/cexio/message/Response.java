package com.cryptospreadhunter.importer.cexio.message;

import com.google.gson.JsonObject;

public class Response {
	String e;
	JsonObject data;
	String oid;
	String ok;

	public String getE() {
		return e;
	}

	public void setE(String e) {
		this.e = e;
	}

	public JsonObject getData() {
		return data;
	}

	public void setData(JsonObject data) {
		this.data = data;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getOk() {
		return ok;
	}

	public void setOk(String ok) {
		this.ok = ok;
	}

	@Override
	public String toString() {
		return "Response [e=" + e + ", data=" + data + ", oid=" + oid + ", ok=" + ok + "]";
	}

}
