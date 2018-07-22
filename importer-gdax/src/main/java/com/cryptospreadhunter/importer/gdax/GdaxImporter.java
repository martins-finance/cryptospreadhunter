package com.cryptospreadhunter.importer.gdax;

import java.io.IOException;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cryptospreadhunter.core.model.Price;
import com.cryptospreadhunter.core.service.PriceService;

import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import ws.wamp.jawampa.PubSubData;
import ws.wamp.jawampa.WampClient;
import ws.wamp.jawampa.WampClientBuilder;
import ws.wamp.jawampa.connection.IWampConnectorProvider;
import ws.wamp.jawampa.transport.netty.NettyWampClientConnectorProvider;

@Service
public class GdaxImporter {

	@Autowired
	private PriceService priceService;

	Subscription tickerSubscription;
	Vector<String> existing = new Vector<>();

	public void start() {

		IWampConnectorProvider connectorProvider = new NettyWampClientConnectorProvider();
		WampClientBuilder builder = new WampClientBuilder();

		// Build two clients
		final WampClient client1;
		try {
			builder.withConnectorProvider(connectorProvider).withUri("wss://api.poloniex.com").withRealm("realm1").withInfiniteReconnects().withReconnectInterval(3, TimeUnit.SECONDS);
			client1 = builder.build();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		client1.statusChanged().subscribe(new Action1<WampClient.State>() {
			@Override
			public void call(WampClient.State t1) {
				System.out.println("Session1 status changed to " + t1);

				if (t1 instanceof WampClient.ConnectedState) {
					// Register a procedure
					tickerSubscription = client1.makeSubscription("ticker").subscribe(new Action1<PubSubData>() {
						@Override
						public void call(PubSubData pubSubData) {

							//["USDT_BTC","4033.00000012","4035.00000006","4033.00000012","-0.03774116","27866044.87923686","6670.73429967",0,"4277.99999989","4032.07224208"]
							//currencyPair, last, lowestAsk, highestBid, percentChange, baseVolume, quoteVolume, isFrozen, 24hrHigh, 24hrLow

							String[] currencyPairArray = pubSubData.arguments().get(0).asText().split("_");
							String currency = currencyPairArray[0];
							currency = currency.replaceFirst("USDT", "USD");
							String base = currencyPairArray[1];

							priceService.insert(new Price(Price.EXCHANGE_POLONIEX, Price.OPERATION_BUY, pubSubData.arguments().get(3).asDouble(), currency, base));
							priceService.insert(new Price(Price.EXCHANGE_POLONIEX, Price.OPERATION_SELL, pubSubData.arguments().get(2).asDouble(), currency, base));

						}
					});
				}
			}
		}, new Action1<Throwable>() {
			@Override
			public void call(Throwable t) {
				System.out.println("Session1 ended with error " + t);
			}
		}, new Action0() {
			@Override
			public void call() {
				System.out.println("Session1 ended normally");
			}
		});

		System.out.println("Connecting the client 1");
		client1.open();

		// System.out.println("tickerSubscription the client 1");
		// tickerSubscription = client1.registerProcedure("ticker").subscribe(new
		// Action1<Request>() {
		// @Override
		// public void call(Request request) {
		// System.out.println("tickerSubscription" + request);
		// }
		// });
		//
		// System.out.println("makeSubscription the client 1");
		// client1.makeSubscription("ticker").doOnNext(new Action1<Object>() {
		// @Override
		// public void call(Object t1) {
		// System.out.println("makeSubscription: " + t1);
		// }
		//
		// });

		waitUntilKeypressed();

		System.out.println("Closing the client 1");
		client1.close().toBlocking().last();

	}

	private void waitUntilKeypressed() {
		try {
			System.in.read();
			while (System.in.available() > 0) {
				System.in.read();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
