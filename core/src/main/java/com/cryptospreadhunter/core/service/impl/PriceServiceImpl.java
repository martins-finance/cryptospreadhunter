package com.cryptospreadhunter.core.service.impl;

import java.time.OffsetDateTime;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cryptospreadhunter.core.db.RethinkDBConnectionFactory;
import com.cryptospreadhunter.core.model.Price;
import com.cryptospreadhunter.core.service.PriceService;
import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;

@Service
public class PriceServiceImpl implements PriceService {

	protected final Logger log = LoggerFactory.getLogger(PriceServiceImpl.class);
	private static final RethinkDB r = RethinkDB.r;

	@Autowired
	private RethinkDBConnectionFactory connectionFactory;

	@Override
	public Price insert(Price price) {
		Connection connection = connectionFactory.createConnection();
		price.setId(r.uuid().run(connection));
		price.setTime(OffsetDateTime.now());
		HashMap<?, ?> run = r.db("test").table("prices").insert(price).run(connection);

		log.info("Insert {}", price);
		return price;
	}

}
