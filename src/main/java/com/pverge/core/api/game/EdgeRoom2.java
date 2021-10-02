package com.pverge.core.api.game;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pverge.core.be.EdgePresenceBE;
import com.pverge.core.db.PlayerDBLoader;
import com.pverge.core.db.PlayerVehicleDBLoader;
import com.pverge.core.db.dbobjects.PlayerEntity;
import com.pverge.core.db.dbobjects.PlayerVehicleEntity;

/**
 * Edge - Race rooms requests. To start the race, a proper Socket-IO with event details must be sent
 * @author Hypernucle
 */
@Path("/v2")
public class EdgeRoom2 {
	
	@EJB
	private PlayerDBLoader playerDB;
	@EJB
	private PlayerVehicleDBLoader playerVehicleDB;
	@EJB
	private EdgePresenceBE edgePresenceBE;
	
	private static String forcePlayerId = "33";
	// TODO Learn more about room2summaries
	
	/**
	 * Create new racing room request
	 */
	@POST
	@Path("room2")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiRoom2Create(String requestBody) {
		// TODO A lot of parameters was added during tests, not all of them is actually used
		JsonObject requestJson = new Gson().fromJson(requestBody, JsonObject.class);
		
		PlayerEntity playerEntity = playerDB.getPlayer(forcePlayerId);
		PlayerVehicleEntity playerVehicleEntity = playerVehicleDB.getVehicleByVid(playerEntity.getVid());
		
		JsonObject rootJson = new JsonObject();
		rootJson.addProperty("title", "ProjectVergeRoom");
		rootJson.addProperty("maxPlayer", 1);
		rootJson.addProperty("maxVehicleClazz", "ALL");
		rootJson.addProperty("gameMode", requestJson.get("gameMode").getAsString());
		rootJson.addProperty("password", "-1");
		rootJson.addProperty("channel", "ALL");
		
		rootJson.addProperty("hostSlot", 1);
		rootJson.addProperty("id", "123");
		rootJson.addProperty("trackCode", 30);
		rootJson.addProperty("isRandomTrack", false);
		rootJson.addProperty("status", "READY");
		rootJson.addProperty("isRefereeMode", false);
		rootJson.addProperty("refereeSlot", -1);
		
		JsonObject gmInfoJson = new JsonObject();
		gmInfoJson.addProperty("maxPlayer", requestJson.get("maxPlayer").getAsString());
		rootJson.add("gameModeInfo", gmInfoJson);
		
		JsonArray lockedArray = new JsonArray();
		for (int i = 2; i < 9; i++) {
			lockedArray.add(i); // display player slots 2-8 as Locked
		}
		rootJson.add("locked", lockedArray);
		
		JsonArray observersArray = new JsonArray();
		rootJson.add("observers", observersArray);
		
		JsonArray playersArray = new JsonArray();
		JsonObject p1Json = new JsonObject();
		p1Json.addProperty("sid", "1337"); // session ID
		p1Json.addProperty("slot", 1);
		p1Json.addProperty("pid", forcePlayerId);
		
		playersArray.add(p1Json);
		rootJson.add("players", playersArray);
		
		JsonObject carJson = new JsonObject();
		carJson.addProperty("id", String.valueOf(playerVehicleEntity.getId())); // vehicle id
		carJson.addProperty("code", playerVehicleEntity.getVcode());
		carJson.addProperty("grade", 3);
		carJson.addProperty("ovr", 573);
		
		JsonObject carParts = new JsonObject();
		carParts.addProperty("frame", 0);
		carParts.addProperty("bumper", 0);
		carParts.addProperty("nitroTank", 0);
		carParts.addProperty("transmission", 0);
		carParts.addProperty("engine", 0);
		carJson.add("parts", carParts);
		
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
		
		JsonObject carStatus = new JsonObject();
		carStatus.addProperty("topSpeed", 562);
		carStatus.addProperty("acceleration", 569);
		carStatus.addProperty("nitroCapacity", 559);
		carStatus.addProperty("strength", 409);
		carStatus.addProperty("durability", 397);
		carJson.add("status", carStatus);
		
		carJson.addProperty("igr", false);
		p1Json.add("vehicle", carJson);
		
		p1Json.addProperty("status", "JOIN");
		p1Json.addProperty("team", 0);
		p1Json.addProperty("clientVersion", 0);
		
		JsonObject plateJson = new JsonObject();
		plateJson.addProperty("pid", forcePlayerId);
		plateJson.addProperty("prefix", "PJ");
		plateJson.addProperty("plateNumber", "VERGE");
		plateJson.addProperty("templateCode", 0);
		plateJson.addProperty("background", 1);
		plateJson.addProperty("fontColor", "#ffffff");
		p1Json.add("plate", plateJson);
		
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
	 * Change track on room request
	 */
	@PUT
	@Path("room2/{roomId}/trackcode")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiRoom2ChangeTrack(String requestBody, @PathParam(value = "roomId") String roomId) {
		JsonObject requestJson = new Gson().fromJson(requestBody, JsonObject.class);
		String trackCode = requestJson.get("trackCode").getAsString();
		edgePresenceBE.setPlayerActivity("room2superpeer", trackCode);
		
		System.out.println("### [Room] Track Code " + trackCode + " change request from player ID " + forcePlayerId + ".");
	    return "true";
	}
	
	/**
	 * Change vehicle on room request
	 */
	@PUT
	@Path("room2/{roomId}/players/{slotId}/vehicle/vid")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiRoom2ChangeVehicle(String requestBody, @PathParam(value = "roomId") String roomId, 
			@PathParam(value = "slotId") String slotId) {
		JsonObject requestJson = new Gson().fromJson(requestBody, JsonObject.class);
		String vehicleId = requestJson.get("vid").getAsString();
		playerDB.changeRecentVehicle(forcePlayerId, vehicleId);
		
		System.out.println("### [Room] Vehicle ID " + vehicleId + " change request from player slot " + slotId + ".");
	    return "true";
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