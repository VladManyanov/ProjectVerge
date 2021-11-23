package com.pverge.core;

import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.pverge.core.socket.NettySocketIO;

/**
 * Startup actions. Notify about core start and start Socket-IO & H2 database modules
 * @author Hypernucle
 */
@Startup
@Singleton
public class StartupCore {
	
	NettySocketIO socketIO = new NettySocketIO();
	Database h2db = new Database();
	
	@PostConstruct
	public void startInit() throws SQLException {
	    // TODO Detect actual IP:Port of the server modules
		//h2db.startH2Database();
	    socketIO.startSocketIO();
	    System.out.println("### [Init] Project Verge Core (0.0.6) has been started, default address is "
	    		+ "192.168.0.10:8888" + ".");
	}
	
	@PreDestroy
	public void shutdownInit() throws SQLException {
		//h2db.stopH2Database();
	    socketIO.stopSocketIO();
	}
}