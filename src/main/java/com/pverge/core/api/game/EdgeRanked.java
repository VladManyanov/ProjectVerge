package com.pverge.core.api.game;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;

/**
 * Edge - Ranked game mode requests
 * @author Hypernucle
 */
@Path("/v2")
public class EdgeRanked {
	
	private static String forcePlayerId = "33";
	// TODO 
	
	/**
	 * Get current season info request
	 */
	@GET
	@Path("ranked2/seasons")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiRankedSeasons() {
		JsonObject rootJson = new JsonObject();
		JsonObject prevSeasonJson = new JsonObject();
		prevSeasonJson.addProperty("seasonnum", 36);
		prevSeasonJson.addProperty("startat", "2021-08-02T15:59:59.000Z");
		prevSeasonJson.addProperty("id", 1);
		rootJson.add("prevSeason", prevSeasonJson);
		
		JsonObject currSeasonJson = new JsonObject();
		currSeasonJson.addProperty("seasonnum", 37);
		currSeasonJson.addProperty("startat", "2021-09-01T15:59:59.000Z");
		currSeasonJson.addProperty("id", 2);
		rootJson.add("currSeason", currSeasonJson);
		
		JsonObject nextSeasonJson = new JsonObject();
		nextSeasonJson.addProperty("seasonnum", 38);
		nextSeasonJson.addProperty("startat", "2021-09-29T15:59:59.000Z");
		nextSeasonJson.addProperty("id", 3);
		rootJson.add("nextSeason", nextSeasonJson);
		
		System.out.println("### [Ranked] Current Season information request from player ID " + forcePlayerId + ".");
	    return rootJson.toString();
	}
    
}