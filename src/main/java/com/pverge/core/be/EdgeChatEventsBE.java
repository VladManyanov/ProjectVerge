package com.pverge.core.be;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.pverge.core.socket.NettySocketIO;
import com.pverge.core.socket.dataobjects.SIOChannelJoinObjects.OWJoinChatOtherChannelObj;
import com.pverge.core.socket.dataobjects.SIOChannelJoinObjects.OWJoinChatChannelObj;
import com.pverge.core.socket.dataobjects.SIOChannelJoinObjects.OWJoinChannelObj;
import com.pverge.core.socket.dataobjects.SIOChannelJoinObjects.OWJoinOpts;
import com.pverge.core.socket.dataobjects.SIODataObjects.*;

/**
 * Prepare and send chat & channel-related events with Socket
 * @author Hypernucle
 */
@Stateless
public class EdgeChatEventsBE {

	NettySocketIO socketIO = new NettySocketIO();
	
	/**
	 * Open World chat connection message
	 */
	public void chatOWChatJoinSIO(int channelId) {
		ResourceListDataObject owJoinRootData = new ResourceListDataObject();
		List<Object> optsList = new ArrayList<>();
		OWJoinOpts owJoinOpts = new OWJoinOpts();
		owJoinOpts.setUri("chat.channel.joined");
		OWJoinChatChannelObj owJoinChatChannelObj = new OWJoinChatChannelObj();
		owJoinChatChannelObj.setChannelCode(channelId);
		
		owJoinOpts.setBody(owJoinChatChannelObj);
		optsList.add(owJoinOpts);
		owJoinRootData.setCmd("resources");
		owJoinRootData.setOpts(optsList);
		
		socketIO.sendEvent("msg", owJoinRootData, owJoinRootData.getCmd());
	}
	
	/**
	 * Open World channel connection message
	 */
	public void chatOWJoinSIO(int channelId) {
		ResourceListDataObject owJoinRootData = new ResourceListDataObject();
		List<Object> optsList = new ArrayList<>();
		OWJoinOpts owJoinOpts = new OWJoinOpts();
		owJoinOpts.setUri("chat.ow.joined");
		OWJoinChannelObj owJoinChannelObj = new OWJoinChannelObj();
		owJoinChannelObj.setOpenworldId(channelId);
		
		owJoinOpts.setBody(owJoinChannelObj);
		optsList.add(owJoinOpts);
		owJoinRootData.setCmd("resources");
		owJoinRootData.setOpts(optsList);
		
		socketIO.sendEvent("msg", owJoinRootData, owJoinRootData.getCmd());
	}
	
	/**
	 * Open World "Other" channel connection message, contains player ID
	 */
	public void chatOWOtherJoinSIO(String pid) {
		ResourceListDataObject owJoinRootData = new ResourceListDataObject();
		List<Object> optsList = new ArrayList<>();
		OWJoinOpts owJoinOpts = new OWJoinOpts();
		owJoinOpts.setUri("chat.ow.other.joined");
		OWJoinChatOtherChannelObj owJoinChatOtherChannelObj = new OWJoinChatOtherChannelObj();
		owJoinChatOtherChannelObj.setPID(pid);
		
		owJoinOpts.setBody(owJoinChatOtherChannelObj);
		optsList.add(owJoinOpts);
		owJoinRootData.setCmd("resources");
		owJoinRootData.setOpts(optsList);
		
		socketIO.sendEvent("msg", owJoinRootData, owJoinRootData.getCmd());
	}
	
	/**
	 * Open World player snippet update message, contains player ID
	 */
	public void owPlayerSnippetUpdateSIO(String pid) {
		ResourceListDataObject owSnippetRootData = new ResourceListDataObject();
		List<Object> optsList = new ArrayList<>();
		OWJoinOpts owJoinOpts = new OWJoinOpts();
		owJoinOpts.setUri("openworld.update.playerSnippet");
		OWJoinChatOtherChannelObj owSnippetObj = new OWJoinChatOtherChannelObj();
		owSnippetObj.setPID(pid);
		
		owJoinOpts.setBody(owSnippetObj);
		optsList.add(owJoinOpts);
		owSnippetRootData.setCmd("resources");
		owSnippetRootData.setOpts(optsList);
		
		socketIO.sendEvent("msg", owSnippetRootData, owSnippetRootData.getCmd());
	}
	
}