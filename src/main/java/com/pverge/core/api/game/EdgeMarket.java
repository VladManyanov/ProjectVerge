package com.pverge.core.api.game;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * Edge - In-game market activity
 * @author Hypernucle
 */
@Path("/v2")
public class EdgeMarket {
	
	private static String forcePlayerId = "33";
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
		JsonArray rootArrayJson = new JsonArray();
		rootArrayJson.add("1");
		
		System.out.println("### [Market] Get vehicle values request from player ID " + forcePlayerId + ".");
	    return rootArrayJson.toString();
	}
    
}