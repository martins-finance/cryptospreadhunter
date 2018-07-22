package com.cryptospreadhunter.importer.cexio.message;

public class AuthRequest {

	private String e = "auth";
	private AuthData auth;

	public AuthRequest(String key, String signature, Long timestamp) {
		this.auth = new AuthData(key, signature, timestamp);
	}

	public String getE() {
		return e;
	}

	public void setE(String e) {
		this.e = e;
	}

	public AuthData getAuth() {
		return auth;
	}

	public void setAuth(AuthData auth) {
		this.auth = auth;
	}

}

class AuthData {
	private String key;
	private String signature;
	private Long timestamp;

	public AuthData(String key, String signature, Long timestamp) {
		this.key = key;
		this.signature = signature;
		this.timestamp = timestamp;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
}