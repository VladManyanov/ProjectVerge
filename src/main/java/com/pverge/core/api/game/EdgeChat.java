package com.pverge.core.api.game;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.pverge.core.be.EdgeChatEventsBE;
import com.pverge.core.be.EdgePresenceBE;
import com.pverge.core.be.EdgeTokensBE;
import com.pverge.core.db.dbobjects.PlayerEntity;

/**
 * Edge - Chat requests
 * @author Hypernucle
 */
@Path("/v2")
public class EdgeChat {
	
	@EJB
	private EdgePresenceBE presenceBE;
	@EJB
	private EdgeTokensBE tokensBE;
	@EJB
	private EdgeChatEventsBE chatEventsBE;
	@Context
	private HttpServletRequest sr;
	
	/**
	 * Chat broadcasts items request
	 */
	@GET
	@Path("chat/broadcasts/item")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiChatBroadcastsItem() {
		PlayerEntity player = tokensBE.resolveToken(sr.getHeader("Authorization"));
		
		JsonObject rootJson = new JsonObject();
		rootJson.addProperty("itemCount", 0);
		if (presenceBE.getPlayerState().contentEquals("idle")) {
			chatEventsBE.owPlayerSnippetUpdateSIO(player.getPid());
		}
		
		System.out.println("### [Chat] Get broadcasts items request from player ID " + player.getPid() + ".");
	    return rootJson.toString();
	}
    
}