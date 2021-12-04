package com.pverge.core.api.game;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.pverge.core.be.EdgeEventLauncherBE;
import com.pverge.core.be.EdgePlayersBE;
import com.pverge.core.be.EdgePresenceBE;
import com.pverge.core.be.EdgeTokensBE;
import com.pverge.core.be.util.MiscUtils;
import com.pverge.core.db.PlayerDBLoader;
import com.pverge.core.db.PlayerVehicleDBLoader;
import com.pverge.core.db.dbobjects.PlayerEntity;

/**
 * Edge - Player & Account information requests
 * Majority of requests contains some unknown path value, example: _=1632169536992
 * @author Hypernucle
 */
@Path("/v2")
public class EdgePlayers {
	
	@EJB
	private PlayerDBLoader playerDB;
	@EJB
	private PlayerVehicleDBLoader playerVehicleDB;
	@EJB
	private EdgePlayersBE edgePlayersBE;
	@EJB
	private EdgePresenceBE edgePresenceBE;
	@EJB
	private EdgeEventLauncherBE edgeEventLauncherBE;
	@EJB
	private EdgeTokensBE tokensBE;
	@Context
	private HttpServletRequest sr;
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
		PlayerEntity player = tokensBE.resolveToken(sr.getHeader("Authorization"));
		JsonArray rootArrayJson = new JsonArray();
		JsonObject accountJson = new JsonObject();
		rootArrayJson.add(accountJson);
		
		accountJson.addProperty("pubId", player.getPid()); // Account ID, related to the Chinese or Korean server owner
		accountJson.addProperty("createdat", "2017-11-12T18:22:47.587Z");
		
		JsonArray arrayJsonTutorHistory = new JsonArray();
		arrayJsonTutorHistory.add(11);
		arrayJsonTutorHistory.add(21);
		accountJson.add("quickTutorialHistory", arrayJsonTutorHistory);
		
		accountJson.addProperty("tutorialState", 50);
		accountJson.addProperty("__v", 0);
		accountJson.addProperty("lastloginat", 0);
		accountJson.addProperty("lastlogoutat", 0);
		accountJson.addProperty("activate", false);
		
		JsonObject lastPlayedJson = new JsonObject();
		lastPlayedJson.addProperty("time", "1970-01-01T00:00:00.000Z");
		accountJson.add("lastplayed", lastPlayedJson);
		
		accountJson.addProperty("id", player.getPid());
		accountJson.addProperty("serverTime", MiscUtils.getCurrentTime());
		
		System.out.println("### [Players] Account player request from player ID " + player.getPid() + ".");
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
		PlayerEntity player = tokensBE.resolveToken(sr.getHeader("Authorization"));
		
		System.out.println("### [Players] Player data request from player ID " + player.getPid() + ".");
	    return edgePlayersBE.getPlayerInfoCommon(true, player);
	}
	
	/**
	 * Player information request, with Checked At (recent vehicle) update
	 * @return Player data
	 */
	@PUT
	@Path("players/{playerId}/checkedat")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiPlayersCheckedAt(@PathParam(value = "playerId") String playerId) {
		PlayerEntity player = tokensBE.resolveToken(sr.getHeader("Authorization"));
		
		System.out.println("### [Players] Player data (Checked at) request from player ID " + playerId + ".");
	    return edgePlayersBE.getPlayerInfoCommon(false, player);
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
	public Response apiPlayersRecent(String requestBody, @PathParam(value = "playerId") String playerId) {
		JsonObject requestJson = new Gson().fromJson(requestBody, JsonObject.class);
		String vid = requestJson.get("vid").getAsString();
		playerDB.changeRecentVehicle(playerId, vid);
		edgePresenceBE.changeRecentVehicleSIORequest(playerId, vid);
		
		System.out.println("### [Players] Player Recent request from player ID " + playerId + ", new vehicle ID: " + vid + ".");
	    return Response.ok().build();
	}
	
	/**
	 * Player Snippet request, used to load players data & vehicles
	 * @return Actual player data
	 */
	@POST
	@Path("snippets/players")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiPlayerSnippet(String requestBody) {
		PlayerEntity player = tokensBE.resolveToken(sr.getHeader("Authorization"));
		JsonArray requestJson = new Gson().fromJson(requestBody, JsonArray.class);
		
		JsonArray rootArrayJson = new JsonArray();
		if (requestJson.isEmpty()) {
			System.out.println("!!! [TEST] Player Snippet request is empty!");
		}
		for (JsonElement playerJsonId : requestJson) {
			String playerId = playerJsonId.getAsString();
			System.out.println("### [TEST] Player Snippet: " + playerId);
			rootArrayJson.add(edgePlayersBE.getPlayerSnippet(playerId));
		}
		System.out.println("### [Players] Player Snippet request from player ID " + player.getPid() + ".");
	    return rootArrayJson.toString();
	}
	
	/**
	 * Player paid currency balance
	 * @return Balance
	 */
	@GET
	@Path("tc/balance")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiTCBalance(@HeaderParam("_") String someValue) {
		PlayerEntity player = tokensBE.resolveToken(sr.getHeader("Authorization"));
		JsonObject rootJson = new JsonObject();
		rootJson.addProperty("balance", 0);
		
		System.out.println("### [Players] Player TC balance request from player ID " + player.getPid() + ".");
	    return rootJson.toString();
	}
    
}