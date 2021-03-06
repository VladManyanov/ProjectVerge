package com.pverge.core.socket;

import java.util.UUID;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;

/**
 * Socket-IO - Configuration and start process
 * @author Hypernucle
 */
public class NettySocketIO {
	
	static SocketIOServer ioServer = new SocketIOServer(getSocketIOConfig());
	SocketIOListeners ioListeners = new SocketIOListeners();
	
	public static Configuration getSocketIOConfig() {
		Configuration config = new Configuration();
	    //config.setHostname("localhost");
	    config.setPort(3000);
	    config.setPingTimeout(60000);
	    config.setPingInterval(30000);
	    config.setContext("/socket.io");
	    
	    SocketConfig socketConfig = new SocketConfig();
	    socketConfig.setReuseAddress(true);
	    
	    config.setSocketConfig(socketConfig);
	    return config;
	}
	
	public void startSocketIO() {
		ioListeners.defineSocketIOListeners(ioServer);
		ioServer.start();
		System.out.println("### [Init] Socket-IO module has been started on " + 
				ioServer.getConfiguration().getHostname() + ":" + ioServer.getConfiguration().getPort() + ".");
	}
	
	public void stopSocketIO() {
		ioServer.stop();
		System.out.println("### [Init] Socket-IO module has been stopped.");
	}
	
	/**
	 * Process and emit server-to-client Socket events
	 */
	public void sendEvent(String type, Object data, String logType, UUID playerSessionId) {
		ioServer.getClient(playerSessionId).sendEvent(type, data);
		System.out.println("### [Socket] Event emit of player UUID " + playerSessionId 
				+ ", type: " + type + ", " + logType + ".");
	}
}
