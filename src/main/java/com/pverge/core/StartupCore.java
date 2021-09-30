package com.pverge.core;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.pverge.core.socket.NettySocketIO;

/**
 * Startup actions. Notify about core start and start Socket-IO module
 * @author Hypernucle
 */
@Startup
@Singleton
public class StartupCore {
	
	NettySocketIO socketIO = new NettySocketIO();
	
	@PostConstruct
	public void startInit() {
	    // TODO Detect actual IP:Port of the server modules
	    System.out.println("### [Init] Project Verge Core has been started on " + "192.168.0.10:8888" + ".");
	    socketIO.startSocketIO();
	}
	
	@PreDestroy
	public void shutdownInit() {
	    socketIO.stopSocketIO();
	}
}