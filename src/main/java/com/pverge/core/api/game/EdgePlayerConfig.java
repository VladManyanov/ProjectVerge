package com.pverge.core.api.game;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pverge.core.be.EdgePresenceBE;

/**
 * Edge - Player configuration requests
 * Majority of requests contains some unknown path value, example: _=1632169536992
 * @author Hypernucle
 */
@Path("/v2")
public class EdgePlayerConfig {
	
	@EJB
	private EdgePresenceBE presence;
	
	private static String forcePlayerId = "33";
	// TODO Store Client Feature config somewhere
	// TODO Send proper full Player config, according to the original request
    
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
		JsonObject rootJson = new JsonObject();
		JsonObject inputJson = new JsonObject();
		
		rootJson.add("inputKey", inputJson);
		inputJson.addProperty("secondBrake", "S");
		
		System.out.println("### [PlayerConfig] Player Config request from player ID " + playerId + ".");
	    return rootJson.toString();
	}
	
	/**
	 * Player config (Input keys) request, saves the player input config on the server.
	 * Note: not sure if really used by the game, since GameSetting request sends the Inputs data too
	 */
	@PUT
	@Path("playerconfig/{accountId}/inputkey")
	@Produces(MediaType.APPLICATION_JSON)
	public Response apiPlayerConfigInputs(@PathParam(value = "playerId") String playerId) {
		System.out.println("### [PlayerConfig] Player config (Input keys) request from player ID " + playerId + ".");
	    return Response.ok().build();
	}
	
	/**
	 * Player config (Game Settings) request, saves the player input & game settings config on the server.
	 * @return Game Setting config which was sent by player
	 */
	@PUT
	@Path("playerconfig/{playerId}/gamesetting")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiPlayerConfigGameSettings(@PathParam(value = "playerId") String playerId, @HeaderParam("_") String someValue) {
		// TODO Lazy
		String rootStr = "{\"version\":2,\"inputKey\":{\"toggleWorldmap\":[77,23],\"useItem2\":[18,15],\"secondBrake\":[0,20],\"throttle\":[38,21],\"brake\":[40,20],\"steeringLeft\":[37,0],\"steeringRight\":[39,0],\"nitro\":[78,12],\"handBrake\":[32,14],\"useItem\":[17,13],\"reset\":[82,19],\"rearView\":[87,0],\"leftView\":[68,0],\"rightView\":[65,0],\"horn\":[72,18],\"toggleCamera\":[67,9],\"toggleMinimap\":[88,23]},\"gameSetting\":{\"minimapPosition\":0,\"roomMirrorOff\":false,\"useHcs\":false,\"useEsc\":false,\"useAbs\":false,\"actionFeedbackOn\":true,\"keyGuideOn\":true,\"vehicleCameraMode\":0,\"chatOn\":true,\"flevron\":true},\"__v\":0,\"id\":\"5a0891771f40bf0007f512bb\"}";
		
		System.out.println("### [PlayerConfig] Player config (Game Settings) request from player ID " + playerId + ".");
	    return rootStr;
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
    
}