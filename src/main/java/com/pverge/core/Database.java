package com.pverge.core;

import java.sql.SQLException;

import org.h2.tools.Server;

/**
 * H2 Database actions
 * @author Hypernucle
 */
public class Database {
	
	static Server h2Server = new Server();
	// Currently using a standalone server
	
	public void startH2Database() throws SQLException {
		h2Server = Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers");
		h2Server.start();
	    System.out.println("### [DB] H2 database has been started.");
	}
	
	public void stopH2Database() throws SQLException {
		h2Server.stop();
	    System.out.println("### [DB] H2 database is shutdown.");
	}
}