package com.pverge.core.api.game;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pverge.core.be.EdgeTokensBE;
import com.pverge.core.db.dbobjects.PlayerEntity;

/**
 * Edge - Player social activity requests
 * @author Hypernucle
 */
@Path("/v2")
public class EdgeSocial {
	
	@EJB
	private EdgeTokensBE tokensBE;
	@Context
	private HttpServletRequest sr;
	// TODO 
	
	/**
	 * Get current player Clan request
	 * @return Current Clan
	 */
	@GET
	@Path("claninfo/{playerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiPlayerClan(@PathParam(value = "playerId") String playerId) {
		JsonObject rootJson = new JsonObject();
		rootJson.addProperty("pid", playerId); 
		rootJson.addProperty("clanId", ""); 
		rootJson.addProperty("requestedClanId", ""); 
		rootJson.addProperty("expelNotification", ""); 
		rootJson.addProperty("acceptNotification", ""); 
		rootJson.addProperty("rejectNotification", ""); 
		rootJson.addProperty("dismissNotification", ""); 
		JsonArray kickedClanListArray = new JsonArray();
		rootJson.add("kickedClanList", kickedClanListArray);
		
		System.out.println("### [Social] Player Clan request from player ID " + playerId + ".");
	    return rootJson.toString();
	}
	
	/**
	 * Get current player Friend-list request
	 * @return Friend-list
	 */
	@GET
	@Path("friends")
	@Produces(MediaType.APPLICATION_JSON)
	public Response apiFriends() {
		PlayerEntity player = tokensBE.resolveToken(sr.getHeader("Authorization"));
		System.out.println("### [Social] Player friends request from player ID " + player.getPid() + ".");
	    return Response.ok().build();
	}
	
	/**
	 * Get current player friend invites request
	 * @return Invites list
	 */
	@GET
	@Path("invites")
	@Produces(MediaType.APPLICATION_JSON)
	public Response apiInvites() {
		PlayerEntity player = tokensBE.resolveToken(sr.getHeader("Authorization"));
		System.out.println("### [Social] Player friend invites request from player ID " + player.getPid() + ".");
	    return Response.ok().build();
	}
    
}