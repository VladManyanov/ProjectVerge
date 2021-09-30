package com.pverge.core.api.game;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * Edge - Race rooms requests. To start the race, a proper Socket-IO with event details must be sent
 * @author Hypernucle
 */
@Path("/v2")
public class EdgeRoom2 {
	
	private static String forcePlayerId = "33";
	// TODO Learn more about room2summaries
	
	/**
	 * Create new racing room request
	 */
	@POST
	@Path("room2")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiRoom2Create() {
		// TODO A lot of parameters was added during tests, not all of them is actually used
		JsonObject rootJson = new JsonObject();
		rootJson.addProperty("title", "ProjectVergeRoom");
		rootJson.addProperty("maxPlayer", 1);
		rootJson.addProperty("maxVehicleClazz", "ALL");
		rootJson.addProperty("gameMode", "SPEEDINDIVIDUAL");
		rootJson.addProperty("password", "-1");
		rootJson.addProperty("channel", "ALL");
		
		rootJson.addProperty("channel", "ALL");
		rootJson.addProperty("id", "123");
		rootJson.addProperty("trackCode", 30);
		rootJson.addProperty("isRandomTrack", false);
		
		JsonArray channelEnumArray = new JsonArray();
		channelEnumArray.add(1);
		channelEnumArray.add(1);
		channelEnumArray.add(1);
		channelEnumArray.add(1);
		channelEnumArray.add(1);
		rootJson.add("ChannelEnum", channelEnumArray);
		
		JsonObject gmInfoJson = new JsonObject();
		gmInfoJson.addProperty("maxPlayer", 1);
		rootJson.add("gameModeInfo", gmInfoJson);
		
		JsonArray lockedArray = new JsonArray();
		rootJson.add("locked", lockedArray);
		
		JsonArray observersArray = new JsonArray();
		JsonObject p1oJson = new JsonObject();
		p1oJson.addProperty("pid", "33");
		observersArray.add(p1oJson);
		rootJson.add("observers", observersArray);
		
		JsonArray playersArray = new JsonArray();
		JsonObject p1Json = new JsonObject();
		p1Json.addProperty("pid", "33");
		
		playersArray.add(p1Json);
		rootJson.add("players", playersArray);
		
		JsonObject carJson = new JsonObject();
		
		carJson.addProperty("embededId", "16666"); // on 1 more than Id value for some reason
		carJson.addProperty("pid", "33"); // player id
		carJson.addProperty("code", 224);
		carJson.addProperty("createdat", "2017-11-12T18:42:19.874Z");
		carJson.addProperty("updatedat", "2020-08-12T20:42:53.861Z");
		carJson.addProperty("__v", 1);
		JsonArray arraySteering = new JsonArray();
		carJson.add("steering", arraySteering);
		carJson.addProperty("depotStatus", 1);
		
		JsonObject carPaint = new JsonObject();
		carPaint.addProperty("wheelCode", 20000);
		carPaint.addProperty("wrapCode", 0);
		carPaint.addProperty("colorCode", 12);
		carJson.add("paint", carPaint);
		
		JsonObject carParts = new JsonObject();
		carParts.addProperty("frame", 0);
		carParts.addProperty("bumper", 0);
		carParts.addProperty("nitroTank", 0);
		carParts.addProperty("transmission", 0);
		carParts.addProperty("engine", 0);
		carJson.add("parts", carParts);
		
		carJson.addProperty("favorite", false);
		carJson.addProperty("grade", 3);
		carJson.addProperty("__v", 1);
		carJson.addProperty("id", "16666"); // vehicle id
		
		JsonObject carStatus = new JsonObject();
		carStatus.addProperty("topSpeed", 562);
		carStatus.addProperty("acceleration", 569);
		carStatus.addProperty("nitroCapacity", 559);
		carStatus.addProperty("strength", 409);
		carStatus.addProperty("durability", 397);
		carJson.add("status", carStatus);
		
		carJson.addProperty("ovr", 573);
		carJson.addProperty("clazz", "A");
		
		p1Json.add("vehicle", carJson);
		
		System.out.println("### [Room] New room creation request from player ID " + forcePlayerId + ".");
	    return rootJson.toString();
	}
	
	/**
	 * Start room race request
	 */
	@POST
	@Path("room2/{roomId}/@start")
	@Produces(MediaType.APPLICATION_JSON)
	public Response apiRoom2Start(@PathParam(value = "roomId") String roomId) {
		System.out.println("### [Room] Room ID " + roomId + " start request from player ID " + forcePlayerId + ".");
	    return Response.ok().build();
	}
	
	/**
	 * Get list of available rooms request
	 */
	@GET
	@Path("room2summaries")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiRoom2List() {
		JsonObject rootJson = new JsonObject();
		
		System.out.println("### [Room] Get available rooms request from player ID " + forcePlayerId + ".");
	    return rootJson.toString();
	}
	
	/**
	 * Remove player from room request. Can be called when race ends, or player is kicked
	 */
	@DELETE
	@Path("room2/{roomId}/players/{playerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response apiRoom2RemovePlayer(@PathParam(value = "roomId") String roomId, @PathParam(value = "playerId") String playerId) {
		System.out.println("### [Room] Player remove from Room ID " + roomId + " request from player ID " + playerId + ".");
	    return Response.ok().build();
	}
    
}