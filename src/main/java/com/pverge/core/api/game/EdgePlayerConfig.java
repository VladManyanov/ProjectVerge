package com.pverge.core.api.game;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pverge.core.be.EdgePlayersBE;
import com.pverge.core.be.EdgePresenceBE;
import com.pverge.core.db.PlayerSettingsDBLoader;
import com.pverge.core.socket.dataobjects.SIOPlayerObjects.GameSetting;
import com.pverge.core.socket.dataobjects.SIOPlayerObjects.InputKey;
import com.pverge.core.socket.dataobjects.SIOPlayerObjects.PlayerConfig;

/**
 * Edge - Player configuration requests
 * Majority of requests contains some unknown path value, example: _=1632169536992
 * @author Hypernucle
 */
@Path("/v2")
public class EdgePlayerConfig {
	
	@EJB
	private EdgePresenceBE presence;
	@EJB
	private EdgePlayersBE playersBE;
	@EJB
	private PlayerSettingsDBLoader playerSettingsDB;
	
	private static String forcePlayerId = "33";
	// TODO Store Client Feature config somewhere
    
    /**
	 * Client Features request, controls some of the game sections availability
	 * @return Array of Client Features
	 */
	@GET
	@Path("staticdata/clientfeature")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiClientFeature(@HeaderParam("_") String someValue) {
		JsonArray rootArrayJson = new JsonArray();
		rootArrayJson.add("CLAN");rootArrayJson.add("IGR");rootArrayJson.add("PARTNERSHIPMARKETING");rootArrayJson.add("MILEAGE");
		rootArrayJson.add("FESTIVAL");rootArrayJson.add("AUCTION");rootArrayJson.add("NUMBERPLATE");rootArrayJson.add("TRADE");
		rootArrayJson.add("PARTSMATERIALCOMBINE");rootArrayJson.add("QUICKTEAMMATCH");rootArrayJson.add("RANKED1ON1");
		rootArrayJson.add("QUESTSYSTEM");rootArrayJson.add("TIMETRIAL");rootArrayJson.add("MANAGER");
		rootArrayJson.add("MILEAGE");rootArrayJson.add("NUMBERPLATE");rootArrayJson.add("PARTSMATERIALCOMBINE");
		rootArrayJson.add("QUESTSYSTEM");rootArrayJson.add("TIMETRIAL");
		//rootArrayJson.add("WRAPPING"); // Korean version vinyl editor
		//arrayJson.add("PURSUIT");arrayJson.add("ESPORTS"); // Not available on official server
		
		System.out.println("### [PlayerConfig] Client Features request from player ID " + forcePlayerId + ".");
	    return rootArrayJson.toString();
	}
	
	/**
	 * Player config request, contains input controls with some of the game settings
	 * @return Player config
	 */
	@GET
	@Path("playerconfig/{playerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiPlayerConfig(@PathParam(value = "playerId") String playerId, @HeaderParam("_") String someValue) {
		System.out.println("### [PlayerConfig] Player Config request from player ID " + playerId + ".");
	    return playersBE.preparePlayerSettings(playerId).toString();
	}
	
	/**
	 * Player config (Input keys) request, saves the player input config on the server.
	 * @return Full config
	 */
	@PUT
	@Path("playerconfig/{playerId}/inputkey")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiPlayerConfigInputs(String requestBody, @PathParam(value = "playerId") String playerId) {
		PlayerConfig newInputs = new Gson().fromJson(requestBody, PlayerConfig.class);
		PlayerConfig playerConfig = new Gson().fromJson(playersBE.preparePlayerSettings(playerId), PlayerConfig.class);
		playerConfig.setInputKey(newInputs.getInputKey());
		playerSettingsDB.updateSettings(playerId, playerConfig);
		
		System.out.println("### [PlayerConfig] Player config (Input keys) request from player ID " + playerId + ".");
	    return playersBE.preparePlayerSettings(playerId).toString();
	}
	
	/**
	 * Player config (Game Settings) request, saves the game settings config on the server.
	 * @return Full config
	 */
	@PUT
	@Path("playerconfig/{playerId}/gamesetting")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiPlayerConfigGameSettings(String requestBody, @PathParam(value = "playerId") String playerId, 
			@HeaderParam("_") String someValue) {
		PlayerConfig newSettings = new Gson().fromJson(requestBody, PlayerConfig.class);
		PlayerConfig playerConfig = new Gson().fromJson(playersBE.preparePlayerSettings(playerId), PlayerConfig.class);
		playerConfig.setGameSetting(newSettings.getGameSetting());
		playerSettingsDB.updateSettings(playerId, playerConfig);
		
		System.out.println("### [PlayerConfig] Player config (Game Settings) save request from player ID " + playerId + ".");
	    return playersBE.preparePlayerSettings(playerId).toString();
	}
	
	/**
	 * Player Presence state request, can be "idle" or "busy"
	 * @return Current player Presence state
	 */
	@PUT
	@Path("presences/{playerId}/state")
	@Produces(MediaType.APPLICATION_JSON)
	//@Consumes(MediaType.APPLICATION_JSON)
	public String apiPresenceState(String requestBody, @PathParam(value = "playerId") String playerId) {
		JsonObject requestJson = new Gson().fromJson(requestBody, JsonObject.class);
		String state = requestJson.get("state").getAsString();
		presence.setPlayerState(state, playerId);
		if (state.contentEquals("match.reward")) {
			presence.startWithDelay("matchFinish");
		}
		
		JsonObject rootJson = new JsonObject();
		JsonArray arrayJson = new JsonArray();
	    
		JsonObject infoJson = new JsonObject();
		rootJson.add("info", arrayJson);
		infoJson.addProperty("pid", playerId);
		infoJson.addProperty("state", state);
		arrayJson.add(infoJson);
		
		rootJson.addProperty("time", 123456789);
		
		System.out.println("### [PlayerConfig] Player Presence state request from player ID " + playerId + ", state: " + state + ".");
	    return rootJson.toString();
	}
	
	/**
	 * IGR publisher (Chinese) request. Probably related to the Rental or Premium cars
	 */
	@GET
	@Path("{playerId}/igr")
	@Produces(MediaType.APPLICATION_JSON)
	public Response apiIGRPublisher(@PathParam(value = "playerId") String playerId) {
		System.out.println("### [PlayerConfig] Player IGR request from player ID " + playerId + ".");
	    return Response.ok().build();
	}
	
	/**
	 * Banned features check. Required to use Wrap editor on Korean version
	 */
	@GET
	@Path("bannedfeature/{playerId}/features/{featureName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response apiCheckBannedFeature(@PathParam(value = "playerId") String playerId, 
			@PathParam(value = "featureName") String featureName, @HeaderParam("_") String someValue) {
		System.out.println("### [PlayerConfig] Banned feature " + featureName + " request from player ID " + playerId + ".");
	    return Response.ok().build();
	}
    
}