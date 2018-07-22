package com.cryptospreadhunter.web.controller;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rethinkdb.RethinkDB;
import com.cryptospreadhunter.core.db.RethinkDBConnectionFactory;
import com.cryptospreadhunter.core.model.Price;

@RestController
@RequestMapping("/price")
public class PriceController {

	protected final Logger log = LoggerFactory.getLogger(PriceController.class);
	private static final RethinkDB r = RethinkDB.r;

	@Autowired
	private RethinkDBConnectionFactory connectionFactory;

	@RequestMapping(method = RequestMethod.POST)
	public Price postMessage(@RequestBody Price price) {
		price.setTime(OffsetDateTime.now());
		HashMap<?, ?> run = r.db("test").table("prices").insert(price).run(connectionFactory.createConnection());

		log.info("Insert {}", run);
		return price;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Price> getPrices() {

		List<Price> messages = r.db("test").table("prices").orderBy().optArg("index", r.desc("time")).limit(20).orderBy("time").run(connectionFactory.createConnection(), Price.class);

		return messages;
	}
}
