package com.pverge.core.socket;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.pverge.core.be.EdgeTokensBE;
import com.pverge.core.db.dbobjects.PlayerEntity;
import com.pverge.core.socket.dataobjects.SIODataObjects.IOAuthObject;

/**
 * Socket-IO - Request listeners
 * @author Hypernucle
 */
public class SocketIOListeners {
	
//	@EJB
//	private EdgeTokensBE tokensBE;
//	@Context
//	private HttpServletRequest sr;
	
//	private PlayerEntity player = tokensBE.resolveToken(sr.getHeader("Authorization"));
	
	public void defineSocketIOListeners(SocketIOServer ioServer) {
		// Auth: game sends token during Open World loading, we send "result: true" to approve the player login
		ioServer.addEventListener("auth", IOAuthObject.class, new DataListener<IOAuthObject>() {
            @Override
            public void onData(SocketIOClient client, IOAuthObject data, AckRequest ackRequest) {
            	data.setResult(true);
            	ioServer.getClient(client.getSessionId()).sendEvent("auth", data);
//            	tokensBE.savePlayerSIOToken(player.getPid(), client.getSessionId());
            }
        });
		// Disconnect: when game is closed or Socket connection is aborted
		// For some reason game will mark "ping" request as "disconnected"
		ioServer.addDisconnectListener(new DisconnectListener() {
		      @Override
		      public void onDisconnect(SocketIOClient client) {
		    	  System.out.println("### [Socket] Player UUID " + client.getSessionId() + " has disconnected.");
		      }
		});
	}
}

