package com.cryptospreadhunter.core.db;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;

public class DbInitializer implements InitializingBean {

	@Autowired
	private RethinkDBConnectionFactory connectionFactory;

	private static final RethinkDB r = RethinkDB.r;

	@Override
	public void afterPropertiesSet() throws Exception {
		createDb();
	}

	private void createDb() {
		Connection connection = connectionFactory.createConnection();
		List<String> dbList = r.dbList().run(connection);
		if (!dbList.contains("test")) {
			r.dbCreate("test").run(connection);
		}
		List<String> tables = r.db("test").tableList().run(connection);
		if (!tables.contains("prices")) {
			r.db("test").tableCreate("prices").run(connection);
			r.db("test").table("prices").indexCreate("id_index").run(connection);
		}
	}
}
