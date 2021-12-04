package com.pverge.core.api.game;

import java.math.BigInteger;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pverge.core.be.EdgeEventLauncherBE;
import com.pverge.core.be.EdgeMatchCreationBE;
import com.pverge.core.be.EdgePresenceBE;
import com.pverge.core.be.EdgeTokensBE;
import com.pverge.core.be.EdgeVehiclesBE;
import com.pverge.core.db.PlayerDBLoader;
import com.pverge.core.db.PlayerVehicleDBLoader;
import com.pverge.core.db.dbobjects.PlayerEntity;

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
	@EJB
	private EdgeVehiclesBE edgeVehiclesBE;
	@EJB
	private EdgeMatchCreationBE edgeMatchCreationBE;
	@EJB
	private EdgeEventLauncherBE edgeEventLauncherBE;
	@EJB
	private EdgeTokensBE tokensBE;
	@Context
	private HttpServletRequest sr;
	// TODO Learn more about room2summaries
	
	/**
	 * Create new racing room request
	 */
	@POST
	@Path("room2")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiRoom2Create(String requestBody) {
		PlayerEntity player = tokensBE.resolveToken(sr.getHeader("Authorization"));
		JsonObject requestJson = new Gson().fromJson(requestBody, JsonObject.class);
		String gameModeMeta = requestJson.get("gameMode").getAsString();
		
		PlayerEntity playerEntity = playerDB.getPlayer(player.getPid());
		edgePresenceBE.changeRoomProperties(requestJson.get("maxPlayer").getAsInt(), gameModeMeta);
		edgePresenceBE.setPlayerActivity("room2superpeer", 30, player.getPid());
		
		JsonObject rootJson = new JsonObject();
		rootJson.addProperty("title", "ProjectVergeRoom");
		rootJson.addProperty("maxVehicleClazz", "ALL");
		rootJson.addProperty("gameMode", gameModeMeta);
		rootJson.addProperty("password", "-1");
		rootJson.addProperty("channel", "ALL");
		
		rootJson.addProperty("hostSlot", 1);
		rootJson.addProperty("id", "123");
		rootJson.addProperty("trackCode", 30);
		rootJson.addProperty("lastTrackCode", 0);
		rootJson.addProperty("isRandomTrack", false);
		rootJson.addProperty("clientsversion", 243235);
		rootJson.addProperty("status", "READY");
		rootJson.addProperty("isRefereeMode", false);
		rootJson.addProperty("refereeSlot", -1);
		
		JsonArray lockedArray = new JsonArray();
		for (int i = 3; i < 9; i++) {
			lockedArray.add(i); // display player slots 3-8 as Locked
		}
		rootJson.add("locked", lockedArray);
		
		JsonArray observersArray = new JsonArray();
		rootJson.add("observers", observersArray);
		
		JsonArray playersArray = new JsonArray();
		JsonObject p1Json = new JsonObject();
		p1Json.addProperty("sid", "1337"); // session ID
		p1Json.addProperty("slot", 1);
		p1Json.addProperty("pid", player.getPid());
		
		playersArray.add(p1Json);
		rootJson.add("players", playersArray);
		
		p1Json.add("vehicle", edgeVehiclesBE.prepareVehicleData(
				playerVehicleDB.getVehicleByVid(playerEntity.getVid())));
		p1Json.addProperty("status", "READY");
		p1Json.addProperty("team", 0);
		p1Json.addProperty("clientVersion", 243235);
		
		JsonObject plateJson = new JsonObject();
		plateJson.addProperty("pid", player.getPid());
		plateJson.addProperty("prefix", "PJ");
		plateJson.addProperty("plateNumber", "VERGE");
		plateJson.addProperty("templateCode", 0);
		plateJson.addProperty("background", 1);
		plateJson.addProperty("fontColor", "#ffffff");
		p1Json.add("plate", plateJson);
		//
		JsonObject p2Json = new JsonObject();
		PlayerEntity player2Entity = playerDB.getPlayer("34");
		p2Json.addProperty("sid", "1337"); // session ID
		p2Json.addProperty("slot", 2);
		p2Json.addProperty("pid", "34");
		
		playersArray.add(p2Json);
		
		p2Json.add("vehicle", edgeVehiclesBE.prepareVehicleData(
				playerVehicleDB.getVehicleByVid(player2Entity.getVid())));
		p2Json.addProperty("status", "READY");
		p2Json.addProperty("team", 0);
		p2Json.addProperty("clientVersion", 243235);
		
		JsonObject plate2Json = new JsonObject();
		plate2Json.addProperty("pid", player.getPid());
		plate2Json.addProperty("prefix", "PJ");
		plate2Json.addProperty("plateNumber", "VERGE");
		plate2Json.addProperty("templateCode", 0);
		plate2Json.addProperty("background", 1);
		plate2Json.addProperty("fontColor", "#ffffff");
		p2Json.add("plate", plate2Json);
		
		System.out.println("### [Room] New room creation request from player ID " + player.getPid() + ".");
	    return rootJson.toString();
	}
	
	/**
	 * Start room race request
	 */
	@POST
	@Path("room2/{roomId}/@start")
	@Produces(MediaType.APPLICATION_JSON)
	public Response apiRoom2Start(@PathParam(value = "roomId") String roomId) {
		PlayerEntity player = tokensBE.resolveToken(sr.getHeader("Authorization"));
		edgePresenceBE.startWithDelay("initRaceEvent");
		
		System.out.println("### [Room] Room ID " + roomId + " start request from player ID " + player.getPid() + ".");
	    return Response.ok().build();
	}
	
	/**
	 * Start room race (Super Peer) request
	 */
	@POST
	@Path("match2/superpeer/@start")
	@Produces(MediaType.APPLICATION_JSON)
	public Response apiRoom2SuperPeerStart(String requestBody) {
		PlayerEntity player = tokensBE.resolveToken(sr.getHeader("Authorization"));
		
		System.out.println("### [Room] Room ID start (Super Peer) request (Data: " + requestBody + 
				") from player ID " + player.getPid() + ".");
	    return Response.ok().build();
	}
	
	/**
	 * Change track on room request
	 */
	@PUT
	@Path("room2/{roomId}/trackcode")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiRoom2ChangeTrack(String requestBody, @PathParam(value = "roomId") String roomId) {
		PlayerEntity player = tokensBE.resolveToken(sr.getHeader("Authorization"));
		
		JsonObject requestJson = new Gson().fromJson(requestBody, JsonObject.class);
		int trackCode = requestJson.get("trackCode").getAsInt();
		boolean isRandomTrack = requestJson.get("isRandomTrack").getAsBoolean();
		if (isRandomTrack) {trackCode = edgeEventLauncherBE.pickRandomTrack();}
		
		edgePresenceBE.setPlayerActivity("room2superpeer", trackCode, player.getPid());
		edgeMatchCreationBE.updateRoomStateSIO(isRandomTrack, player.getPid());
		
		System.out.println("### [Room] Track Code " + trackCode + " change request from player ID " + player.getPid() + ".");
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
		PlayerEntity player = tokensBE.resolveToken(sr.getHeader("Authorization"));
		
		JsonObject requestJson = new Gson().fromJson(requestBody, JsonObject.class);
		String vehicleId = requestJson.get("vid").getAsString();
		playerDB.changeRecentVehicle(player.getPid(), vehicleId);
		edgePresenceBE.changeRecentVehicleSIORequest(player.getPid(), vehicleId);
		
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
		PlayerEntity player = tokensBE.resolveToken(sr.getHeader("Authorization"));
		JsonArray rootArray = new JsonArray();
		JsonObject lobbyJson = new JsonObject();
		rootArray.add(lobbyJson);
		
		// Fake room for tests
		lobbyJson.addProperty("roomId", 1);
		lobbyJson.addProperty("title", "TEST!");
		lobbyJson.addProperty("isRandomTrack", true);
		lobbyJson.addProperty("trackCode", 100);
		lobbyJson.addProperty("maxSlotCount", 6);
		lobbyJson.addProperty("currentSlotCount", 1);
		lobbyJson.addProperty("clientsVersion", 243235);
		lobbyJson.addProperty("matchEndAt", new BigInteger("2639709906719"));
		
		System.out.println("### [Room] Get available rooms request from player ID " + player.getPid() + ".");
	    return rootArray.toString();
	}
	
	/**
	 * Join to room request. Client sends password to check (-1 if no password)
	 */
	@POST
	@Path("room2/{roomId}/players/")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiRoom2Join(@PathParam(value = "roomId") String roomId) {
		PlayerEntity player = tokensBE.resolveToken(sr.getHeader("Authorization"));
		String[] roomInfo = edgePresenceBE.getRoomInfo();
		
		JsonObject rootJson = new JsonObject();
		rootJson.addProperty("uuid", "987654321");
		rootJson.addProperty("title", "ProjectVergeRoom");
		rootJson.addProperty("maxVehicleClazz", "ALL");
		rootJson.addProperty("gameMode", roomInfo[0]);
		rootJson.addProperty("password", "-1");
		rootJson.addProperty("channel", "ALL");
		
		rootJson.addProperty("hostSlot", 1);
		rootJson.addProperty("id", "123");
		rootJson.addProperty("trackCode", Integer.parseInt(roomInfo[1]));
		rootJson.addProperty("lastTrackCode", 0);
		rootJson.addProperty("isRandomTrack", false);
		rootJson.addProperty("clientsversion", 243235);
		rootJson.addProperty("status", "READY");
		rootJson.addProperty("isRefereeMode", false);
		rootJson.addProperty("refereeSlot", -1);
		
		JsonArray lockedArray = new JsonArray();
		for (int i = 3; i < 9; i++) {
			lockedArray.add(i); // display player slots 3-8 as Locked
		}
		rootJson.add("locked", lockedArray);
		
		JsonArray observersArray = new JsonArray();
		rootJson.add("observers", observersArray);
		
		JsonArray playersArray = new JsonArray();
		rootJson.add("players", playersArray);
		
		String fakePlayer1Id = "33";
		JsonObject p1Json = new JsonObject();
		p1Json.addProperty("sid", "1337"); // session ID
		p1Json.addProperty("slot", 1);
		p1Json.addProperty("pid", fakePlayer1Id);
		playersArray.add(p1Json);
		
		p1Json.add("vehicle", edgeVehiclesBE.prepareVehicleData(
				playerVehicleDB.getVehicleByVid(playerDB.getPlayer(fakePlayer1Id).getVid())));
		p1Json.addProperty("status", "READY");
		p1Json.addProperty("team", 0);
		p1Json.addProperty("clientVersion", 243235);
		
		JsonObject plate1Json = new JsonObject();
		plate1Json.addProperty("pid", fakePlayer1Id);
		plate1Json.addProperty("prefix", "PJ");
		plate1Json.addProperty("plateNumber", "VERGE");
		plate1Json.addProperty("templateCode", 0);
		plate1Json.addProperty("background", 1);
		plate1Json.addProperty("fontColor", "#ffffff");
		p1Json.add("plate", plate1Json);
		//
		JsonObject p2Json = new JsonObject();
		p2Json.addProperty("sid", "1337"); // session ID
		p2Json.addProperty("slot", 2);
		p2Json.addProperty("pid", "34");
		playersArray.add(p2Json);
		
		p2Json.add("vehicle", edgeVehiclesBE.prepareVehicleData(
				playerVehicleDB.getVehicleByVid(player.getVid())));
		p2Json.addProperty("status", "READY");
		p2Json.addProperty("team", 0);
		p2Json.addProperty("clientVersion", 243235);
		
		JsonObject plate2Json = new JsonObject();
		plate2Json.addProperty("pid", player.getPid());
		plate2Json.addProperty("prefix", "PJ");
		plate2Json.addProperty("plateNumber", "VERGE");
		plate2Json.addProperty("templateCode", 0);
		plate2Json.addProperty("background", 1);
		plate2Json.addProperty("fontColor", "#ffffff");
		p2Json.add("plate", plate2Json);
		
		System.out.println("### [Room] Player join to Room ID " + roomId + " request from player ID " + player.getPid() + ".");
	    return rootJson.toString();
	}
	
	/**
	 * Remove player from room request. Can be called when race ends, or player is kicked
	 */
	@PUT
	@Path("room2/{roomId}/players/{playerId}/status")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean apiRoom2PlayerStatus(String requestBody, @PathParam(value = "roomId") String roomId, 
			@PathParam(value = "playerId") String playerId) {
		JsonObject requestJson = new Gson().fromJson(requestBody, JsonObject.class);
		String status = requestJson.get("status").getAsString();
		
		System.out.println("### [Room] Player ID " + playerId + " status has been changed to " + status + ".");
	    return true;
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
	
	/**
	 * Remove player from match request. Can be called when player quits the race before finish
	 */
	@DELETE
	@Path("match2/players/{playerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response apiMatch2RemovePlayer(@PathParam(value = "roomId") String roomId, @PathParam(value = "playerId") String playerId) {
		System.out.println("### [Room] Player remove from Match request from player ID " + playerId + ".");
	    return Response.ok().build();
	}
	
	/**
	 * Remove player from match request. Can be called when player quits the race before finish
	 * Note: for some reason it's different from "match2/players/{playerId}"
	 */
	@DELETE
	@Path("room2/player")
	@Produces(MediaType.APPLICATION_JSON)
	public Response apiRoom2WeirdPlayerRemove() {
		PlayerEntity player = tokensBE.resolveToken(sr.getHeader("Authorization"));
		
		System.out.println("### [Room] Another Player remove from Match request from player ID " + player.getPid() + ".");
	    return Response.ok().build();
	}
	
	/**
	 * Finish match request
	 */
	@POST
	@Path("match2/{matchId}/@end")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiMatch2End(@PathParam(value = "matchId") int matchId) {
		// TODO Contains rewards information
		PlayerEntity player = tokensBE.resolveToken(sr.getHeader("Authorization"));
		JsonObject rootJson = new JsonObject();
		
		System.out.println("### [Room] Match ID " + matchId + " finish request from player ID " + player.getPid() + ".");
	    return rootJson.toString();
	}
    
}