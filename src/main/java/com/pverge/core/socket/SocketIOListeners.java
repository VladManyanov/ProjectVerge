package com.pverge.core.socket;

import java.util.UUID;

import javax.ejb.Stateless;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.corundumstudio.socketio.listener.PingListener;
import com.pverge.core.be.EdgeTokensSocketBE;
import com.pverge.core.socket.dataobjects.SIODataObjects.IOAuthInput;
import com.pverge.core.socket.dataobjects.SIODataObjects.IOAuthOutput;

/**
 * Socket-IO - Request listeners
 * @author Hypernucle
 */
@Stateless
public class SocketIOListeners {
	
	EdgeTokensSocketBE tokensSocketBE = new EdgeTokensSocketBE();
	
	public void defineSocketIOListeners(SocketIOServer ioServer) {
		// Auth: game sends token during Open World loading, we send "result: true" to approve the player login
		ioServer.addEventListener("auth", IOAuthInput.class, new DataListener<IOAuthInput>() {
            @Override
            public void onData(SocketIOClient client, IOAuthInput data, AckRequest ackRequest) {
            	IOAuthOutput dataOutput = new IOAuthOutput();
            	dataOutput.setResult(true); // TODO Any checks?
            	ioServer.getClient(client.getSessionId()).sendEvent("auth", dataOutput);
            	tokensSocketBE.keepPlayerSIOToken(data.getToken(), client.getSessionId());
            	System.out.println("### [Socket] Player UUID " + client.getSessionId() + " has been authorized.");
            }
        });
		// Disconnect: when game is closed or Socket connection is aborted
		ioServer.addDisconnectListener(new DisconnectListener() {
		      @Override
		      public void onDisconnect(SocketIOClient client) {
		    	  tokensSocketBE.removePlayerSIOToken(client.getSessionId());
		    	  System.out.println("### [Socket] Player UUID " + client.getSessionId() + " has disconnected.");
		      }
		});
		ioServer.addPingListener(new PingListener() {
		      @Override
		      public void onPing(SocketIOClient client) {
		    	  System.out.println("### [Socket] Player UUID " + client.getSessionId() + " has sent a ping.");
		      }
		});
	}
}

