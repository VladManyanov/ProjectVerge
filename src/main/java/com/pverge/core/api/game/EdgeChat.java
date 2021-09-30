package com.pverge.core.api.game;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;

/**
 * Edge - Chat requests
 * @author Hypernucle
 */
@Path("/v2")
public class EdgeChat {
	
	private static String forcePlayerId = "33";
	// TODO Learn more about in-game chat
	
	/**
	 * Chat broadcasts items request
	 */
	@GET
	@Path("chat/broadcasts/item")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiChatBroadcastsItem() {
		JsonObject rootJson = new JsonObject();
		rootJson.addProperty("itemCount", 0);
		
		System.out.println("### [Chat] Get broadcasts items request from player ID " + forcePlayerId + ".");
	    return rootJson.toString();
	}
    
}