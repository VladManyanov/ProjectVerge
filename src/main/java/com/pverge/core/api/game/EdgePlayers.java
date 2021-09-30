package com.pverge.core.api.game;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pverge.core.be.EdgePlayersBE;

/**
 * Edge - Player & Account information requests
 * Majority of requests contains some unknown path value, example: _=1632169536992
 * @author Hypernucle
 */
@Path("/v2")
public class EdgePlayers {
	
	EdgePlayersBE edgePlayersBE = new EdgePlayersBE();
	private static String forcePlayerId = "33";
	private static String forceAccountId = "11";
	private static String forceVehicleId = "16666";
	// TODO 
	
	/**
	 * Account information request.
	 * Note: it is unknown if the game had a multiple drivers functionality
	 * @return Account data
	 */
	@GET
	@Path("accounts")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiAccounts(@HeaderParam("_") String someValue) {
		JsonArray rootArrayJson = new JsonArray();
		JsonObject accountJson = new JsonObject();
		rootArrayJson.add(accountJson);
		
		accountJson.addProperty("pubId", forceAccountId); // Account ID, related to the Chinese or Korean server owner
		accountJson.addProperty("createdat", "2017-11-12T18:22:47.587Z");
		
		JsonArray arrayJsonTutorHistory = new JsonArray();
		accountJson.add("quickTutorialHistory", arrayJsonTutorHistory);
		
		accountJson.addProperty("tutorialState", 0);
		accountJson.addProperty("__v", 0);
		accountJson.addProperty("lastloginat", 0);
		accountJson.addProperty("lastlogoutat", 0);
		accountJson.addProperty("activate", true);
		
		JsonObject lastPlayedJson = new JsonObject();
		lastPlayedJson.addProperty("time", "1970-01-01T00:00:00.000Z");
		accountJson.add("lastplayed", lastPlayedJson);
		
		accountJson.addProperty("id", forcePlayerId);
		accountJson.addProperty("serverTime", "2021-09-18T19:12:15.467Z");
		
		System.out.println("### [Players] Account player request from player ID " + forcePlayerId + ".");
	    return rootArrayJson.toString();
	}
	
	/**
	 * Player information request
	 * @return Player data
	 */
	@GET
	@Path("players")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiPlayers(@HeaderParam("_") String someValue) {
		System.out.println("### [Players] Player data request from player ID " + forcePlayerId + ".");
	    return edgePlayersBE.getPlayerInfoCommon(true);
	}
	
	/**
	 * Player information request, with Checked At (recent vehicle) update
	 * @return Player data
	 */
	@PUT
	@Path("players/{playerId}/checkedat")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiPlayersCheckedAt(@PathParam(value = "playerId") String playerId) {
		System.out.println("### [Players] Player data (Checked at) request from player ID " + playerId + ".");
	    return edgePlayersBE.getPlayerInfoCommon(false);
	}
	
	/**
	 * Player log-in request. Client sends clientVersion parameter
	 */
	@POST
	@Path("players/{playerId}/@login")
	@Produces(MediaType.APPLICATION_JSON)
	public Response apiPlayersLogin(@PathParam(value = "playerId") String playerId) {
		System.out.println("### [Players] Player Log-in request from player ID " + playerId + ".");
	    return Response.ok().build();
	}
	
	/**
	 * Player Recent request. Client sends the recent vehicle ID
	 */
	@PUT
	@Path("players/{playerId}/recent")
	@Produces(MediaType.APPLICATION_JSON)
	public Response apiPlayersRecent(@PathParam(value = "playerId") String playerId) {
		System.out.println("### [Players] Player Recent request from player ID " + playerId + ".");
	    return Response.ok().build();
	}
	
	/**
	 * Player Snippet request, used for Open World or Rooms player announces
	 * @return actual player data
	 */
	@POST
	@Path("snippets/players")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiPlayerSnippet() {
		JsonArray rootArrayJson = new JsonArray();
		JsonObject playerJson = new JsonObject();
		rootArrayJson.add(playerJson);
		
		playerJson.addProperty("pid", forcePlayerId);
		playerJson.addProperty("name", "ProjectVerge");
		playerJson.addProperty("level", 63);
		playerJson.addProperty("avatar", "");
		playerJson.addProperty("igr", false);
		playerJson.addProperty("vip", false);
		playerJson.addProperty("vid", forceVehicleId);
		playerJson.addProperty("vCode", 145); // vehicle model code
		playerJson.addProperty("ovr", 707);
		playerJson.addProperty("loginDate", "2021-09-19T12:15:03.597Z");
		playerJson.addProperty("online", true);
		playerJson.addProperty("clanId", "");
		
		JsonObject rankedJson = new JsonObject();
		playerJson.add("ranked", rankedJson);
		
		JsonObject gm1Json = new JsonObject();
		gm1Json.addProperty("fp", 1219);
		gm1Json.addProperty("currSeasonId", "60fe8dc1d512c69eea50797c");
		rankedJson.add("SPEED1ON1", gm1Json);
		
		JsonObject gm2Json = new JsonObject();
		gm2Json.addProperty("medalTier", 11);
		gm2Json.addProperty("medalSeasonId", "600fcc2fe50540f3c5c8d151");
		gm2Json.addProperty("fp", 2142);
		gm2Json.addProperty("currSeasonId", "60fe8dc1d512c69eea50797c");
		rankedJson.add("SPEEDINDIVIDUAL", gm2Json);
		
		rankedJson.addProperty("currSeasonId", "59c8b6325d72a200075de6a1");
		rankedJson.addProperty("fp", 600);
		rankedJson.addProperty("medalTier", 0);
		
		playerJson.addProperty("updatedAt", "2021-09-19T12:15:03.599Z");
		
		System.out.println("### [Players] Player Snippet request from player ID " + forcePlayerId + ".");
	    return rootArrayJson.toString();
	}
    
}