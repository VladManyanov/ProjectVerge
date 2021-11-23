package com.pverge.core.api.game;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pverge.core.be.EdgeTokensBE;
import com.pverge.core.db.dbobjects.PlayerEntity;

/**
 * Edge - In-game market activity
 * @author Hypernucle
 */
@Path("/v2")
public class EdgeMarket {
	
	@EJB
	private EdgeTokensBE tokensBE;
	@Context
	private HttpServletRequest sr;
	// TODO 
	
	/**
	 * Clear Auctions notification request. Appears somewhere during Open World loading
	 */
	@POST
	@Path("auctionhouse/players/{playerId}/notification/@clear")
	@Produces(MediaType.APPLICATION_JSON)
	public Response apiAuctionsClearNotice(@PathParam(value = "playerId") String playerId) {
		System.out.println("### [Market] Clear Auctions notification request from player ID " + playerId + ".");
	    return Response.ok().build();
	}
	
	/**
	 * Get Auctions notification request
	 */
	@GET
	@Path("auctionhouse/players/{playerId}/notification")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiAuctionsNotification(@PathParam(value = "playerId") String playerId) {
		JsonObject rootJson = new JsonObject();
		JsonArray finishedItemsArray = new JsonArray();
		JsonArray rejectedItemsArray = new JsonArray();
		rootJson.add("finishedItems", finishedItemsArray);
		rootJson.add("rejectedItems", rejectedItemsArray);
		
		System.out.println("### [Market] Get Auctions notification request from player ID " + playerId + ".");
	    return rootJson.toString();
	}
	
	/**
	 * Market vehicle values request, always returns null for some reason
	 */
	@GET
	@Path("market2/vehiclevalues")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiMarketVehicleValues(@QueryParam(value = "codes") String codes) {
		PlayerEntity player = tokensBE.resolveToken(sr.getHeader("Authorization"));
		JsonArray rootArrayJson = new JsonArray();
		rootArrayJson.add("1");
		
		System.out.println("### [Market] Get vehicle values request from player ID " + player.getPid() + ".");
	    return rootArrayJson.toString();
	}
	
	/**
	 * Market offers request, empty on real server
	 */
	@GET
	@Path("market2/offers")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiMarketOffers() {
		PlayerEntity player = tokensBE.resolveToken(sr.getHeader("Authorization"));
		JsonArray rootArrayJson = new JsonArray();
		
		System.out.println("### [Market] Get offers request from player ID " + player.getPid() + ".");
	    return rootArrayJson.toString();
	}
    
}