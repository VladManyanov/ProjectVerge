package com.pverge.core.api.game;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonArray;

/**
 * Edge - Shop requests
 * @author Hypernucle
 */
@Path("/v2")
public class EdgeShop {
	
	private static String forcePlayerId = "33";
	// TODO 
	
	/**
	 * Shop content by category request
	 * @return Items array
	 */
	@GET
	@Path("shop/products")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiShopProducts(@QueryParam("category") String category) {
		JsonArray rootArrayJson = new JsonArray();
		
		System.out.println("### [Shop] Shop (category: " + category + ") request from player ID " + forcePlayerId + ".");
	    return rootArrayJson.toString();
	}

}