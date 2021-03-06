package com.pverge.core.api.game;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.pverge.core.be.EdgeTokensBE;
import com.pverge.core.db.dbobjects.PlayerEntity;

/**
 * Edge - Ranked game mode requests
 * @author Hypernucle
 */
@Path("/v2")
public class EdgeRanked {
	
	@EJB
	private EdgeTokensBE tokensBE;
	@Context
	private HttpServletRequest sr;
	// TODO 
	
	/**
	 * Get current season info request
	 */
	@GET
	@Path("ranked2/seasons")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiRankedSeasons() {
		PlayerEntity player = tokensBE.resolveToken(sr.getHeader("Authorization"));
		
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
		
		System.out.println("### [Ranked] Current Season information request from player ID " + player.getPid() + ".");
	    return rootJson.toString();
	}
	
	/**
	 * Get current season tier ranks request
	 */
	@GET
	@Path("ranked2/seasons/current/{gameModeMeta}/tierrankinfo")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiRankedTierInfo(@PathParam(value = "gameModeMeta") String gameModeMeta) {
		PlayerEntity player = tokensBE.resolveToken(sr.getHeader("Authorization"));
		
		JsonObject rootJson = new JsonObject();
		JsonObject tierPlayerJson = new JsonObject();
		rootJson.add("tierPlayerCount", tierPlayerJson);
		rootJson.addProperty("totalPlayerCount", 0);
		
		System.out.println("### [Ranked] Current Season tier ranks request from player ID " + player.getPid() + ".");
	    return rootJson.toString();
	}
	
	/**
	 * Get current season top players request
	 */
	@GET
	@Path("ranked2/seasons/current/{gameModeMeta}/topplayerinfo")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiRankedTopPlayers(@PathParam(value = "gameModeMeta") String gameModeMeta) {
		PlayerEntity player = tokensBE.resolveToken(sr.getHeader("Authorization"));
		JsonObject rootJson = new JsonObject();
		JsonObject topPlayersJson = new JsonObject();
		rootJson.add("top5", topPlayersJson);
		
		System.out.println("### [Ranked] Current Season top players request from player ID " + player.getPid() + ".");
	    return rootJson.toString();
	}
    
}