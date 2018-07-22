package com.cryptospreadhunter.web.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Cursor;
import com.cryptospreadhunter.core.db.RethinkDBConnectionFactory;
import com.cryptospreadhunter.core.model.Price;

@Service
public class PriceChangesListener {
	protected final Logger log = LoggerFactory.getLogger(PriceChangesListener.class);

	private static final RethinkDB r = RethinkDB.r;

	@Autowired
	private RethinkDBConnectionFactory connectionFactory;

	@Autowired
	private SimpMessagingTemplate webSocket;

	@Async
	public void pushChangesToWebSocket() {
		Cursor<Price> cursor = r.db("test").table("prices").changes().getField("new_val").run(connectionFactory.createConnection(), Price.class);

		while (cursor.hasNext()) {
			Price price = cursor.next();
			log.info("New price: {}", price);
			webSocket.convertAndSend("/topic/prices", price);
		}
	}

}
