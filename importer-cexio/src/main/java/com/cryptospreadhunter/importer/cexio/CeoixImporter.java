package com.cryptospreadhunter.importer.cexio;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.StringTokenizer;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cryptospreadhunter.core.model.Price;
import com.cryptospreadhunter.core.service.PriceService;
import com.cryptospreadhunter.importer.cexio.message.AuthRequest;
import com.cryptospreadhunter.importer.cexio.message.MarketDataUpdateResponse;
import com.cryptospreadhunter.importer.cexio.message.OrderBookSubscriptionRequest;
import com.cryptospreadhunter.importer.cexio.message.Pair;
import com.cryptospreadhunter.importer.cexio.message.PongRequest;
import com.cryptospreadhunter.importer.cexio.message.Response;
import com.cryptospreadhunter.importer.cexio.message.TickerResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class CeoixImporter {

	@Autowired
	private PriceService priceService;

	@Value("${cexio.websocketapi.endpoint}")
	private String websocketapiEndpoint;

	@Value("${cexio.websocketapi.key}")
	private String websocketapiKey;

	@Value("${cexio.websocketapi.secret}")
	private String websocketapiSecret;

	GsonBuilder gsonBuilder = new GsonBuilder();
	Gson gson = gsonBuilder.create();

	public void start() {

		try {

			final CexioWebsocketClientEndpoint clientEndPoint = new CexioWebsocketClientEndpoint(new URI(websocketapiEndpoint));

			// add listener
			clientEndPoint.addMessageHandler(new CexioWebsocketClientEndpoint.MessageHandler() {
				public void handleMessage(String message) {
					Response response = gson.fromJson(message, Response.class);

					if (response.getE().equals("ping")) {
						clientEndPoint.sendMessage(gson.toJson(new PongRequest()));
					} else if (response.getE().equals("ticker")) {
						TickerResponse tickerResponse = gson.fromJson(response.getData(), TickerResponse.class);
						System.out.println(tickerResponse);
					} else if (response.getE().equals("md_update")) {
						MarketDataUpdateResponse marketDataUpdateResponse = gson.fromJson(response.getData(), MarketDataUpdateResponse.class);
						System.out.println(marketDataUpdateResponse);

						marketDataUpdateResponse.getPair();
						StringTokenizer st = new StringTokenizer(marketDataUpdateResponse.getPair(), ":");
						String base = st.nextToken();
						String currency = st.nextToken();

						if (marketDataUpdateResponse.getBids().length > 0) {
							for (int i = 0; i < marketDataUpdateResponse.getBids().length; i++) {
								Double amount = marketDataUpdateResponse.getBids()[i][0];
								priceService.insert(new Price(Price.EXCHANGE_CEXIO, Price.OPERATION_BUY, amount, currency, base));
							}
						}

						if (marketDataUpdateResponse.getAsks().length > 0) {
							for (int i = 0; i < marketDataUpdateResponse.getAsks().length; i++) {
								Double amount = marketDataUpdateResponse.getAsks()[i][0];
								priceService.insert(new Price(Price.EXCHANGE_CEXIO, Price.OPERATION_SELL, amount, currency, base));
							}
						}

					}

				}
			});

			// wait 1 second
			Thread.sleep(1000);

			AuthRequest authMessage = generateAuthenticationRequestMessage(websocketapiKey, websocketapiSecret, System.currentTimeMillis() / 1000);

			clientEndPoint.sendMessage(gson.toJson(authMessage));

			// wait 2 seconds
			Thread.sleep(2000);

			long timestamp = System.currentTimeMillis() / 1000;

			//			TickerRequest tickerMessage = new TickerRequest(Pair.BTC_USD, timestamp + "_1_ticker");
			//			clientEndPoint.sendMessage(gson.toJson(tickerMessage));
			//
			//			timestamp = System.currentTimeMillis() / 1000;
			//			TickerRequest tickerMessage2 = new TickerRequest(Pair.ETH_USD, timestamp + "_2_ticker");
			//			clientEndPoint.sendMessage(gson.toJson(tickerMessage2));
			//
			//			timestamp = System.currentTimeMillis() / 1000;

			OrderBookSubscriptionRequest orderBookSubscriptionRequest = new OrderBookSubscriptionRequest(Pair.BTC_USD, true, -1, timestamp + "1_order-book-subscribe");
			clientEndPoint.sendMessage(gson.toJson(orderBookSubscriptionRequest));

			timestamp = System.currentTimeMillis() / 1000;
			OrderBookSubscriptionRequest orderBookSubscriptionRequest2 = new OrderBookSubscriptionRequest(Pair.ETH_USD, true, -1, timestamp + "1_order-book-subscribe");
			clientEndPoint.sendMessage(gson.toJson(orderBookSubscriptionRequest2));

		} catch (InterruptedException ex) {
			System.err.println("InterruptedException exception: " + ex.getMessage());
		} catch (URISyntaxException ex) {
			System.err.println("URISyntaxException exception: " + ex.getMessage());
		} catch (InvalidKeyException e) {
			System.err.println("InvalidKeyException exception: " + e.getMessage());
		} catch (NoSuchAlgorithmException e) {
			System.err.println("NoSuchAlgorithmException exception: " + e.getMessage());
		}

	}

	private AuthRequest generateAuthenticationRequestMessage(String key, String secret, long timestamp) throws NoSuchAlgorithmException, InvalidKeyException {
		SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
		Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
		sha256_HMAC.init(secret_key);
		String hash = Hex.encodeHexString(sha256_HMAC.doFinal((Long.toString(timestamp) + key).getBytes()));
		return new AuthRequest(key, hash, timestamp);
	}

}
