package com.pverge.core.api.game;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.pverge.core.be.EdgeEventLauncherBE;

/**
 * Edge - Open World channels requests
 * Work of Channel system is relies on properly recreated Socket-IO module
 * Note: without proper Open World Channel configuration, game will not let player to load Open World (HP2) location, and stuck on Lighthouse2
 * @author Hypernucle
 */
@Path("/v2")
public class EdgeOWChannels {
	
	@EJB
	private EdgeEventLauncherBE eventLauncher;
	
	private static String forcePlayerId = "33";
	private static int forceChannelId = 94;
	// TODO Learn more about @leave and @join requests
	
	/**
	 * Channel reserve request. Choose and send Channel connection values.
	 * Note: defined values is fake, however all fields must be filled to prevent "OW Channel migration" error
	 * @return Channel to connect to
	 */
	@POST
	@Path("openworld/channel/@reserve")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiOWChannelReserve() {
		JsonObject rootJson = new JsonObject();
		rootJson.addProperty("channelId", forceChannelId);
		rootJson.addProperty("host", "http://localhost");
		rootJson.addProperty("port", 9999);
		rootJson.addProperty("securityKey", "NMtEu3CAgpk22OIMIC4fBQ==");
		eventLauncher.chatOWJoinSIO(forceChannelId);
		
		System.out.println("### [OW Channels] Open World Channel reserve request from player ID " + forcePlayerId + ", channelId: " + forceChannelId + ".");
	    return rootJson.toString();
	}
	
	/**
	 * Get current selected Channel of player request
	 * @return channel ID
	 */
	@GET
	@Path("openworld/channel/players/{playerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiOWChannelPlayers() {
		JsonObject rootJson = new JsonObject();
		rootJson.addProperty("channelId", forceChannelId);
		
		System.out.println("### [OW Channels] Open World Channel reserve request from player ID " + forcePlayerId + ".");
	    return rootJson.toString();
	}
	
	/**
	 * OW Channel join request, contains the channel ID
	 */
	@POST
	@Path("openworld/channel/@join")
	@Produces(MediaType.APPLICATION_JSON)
	public Response apiOWChannelJoin(String requestBody) {
		JsonObject requestJson = new Gson().fromJson(requestBody, JsonObject.class);
		String channelId = requestJson.get("channelId").getAsString();
		
		System.out.println("### [OW Channels] Open World Channel join request from player ID " + forcePlayerId + ", channelId: " + channelId + ".");
	    return Response.ok().build();
	}
	
	/**
	 * Channel leave request. If client channel is not selected, channel ID will be on -1.
	 */
	@POST
	@Path("openworld/channel/@leave")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiOWChannelLeave() {
		JsonObject rootJson = new JsonObject();
		rootJson.addProperty("channelId", -1);
		
		System.out.println("### [OW Channels] Open World Channel leave request from player ID " + forcePlayerId + ".");
	    return rootJson.toString();
	}
	
	/**
	 * Get OW Channel amount request, 250 on original server
	 */
	@GET
	@Path("openworld/channel/count")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiOWChannelCount() {
		System.out.println("/// EDGE: OW Channel count GET");
		JsonObject rootJson = new JsonObject();
		rootJson.addProperty("count", 250);
		
		System.out.println("### [OW Channels] Open World Channel count request from player ID " + forcePlayerId + ", count: " + 250 + ".");
	    return rootJson.toString();
	}
    
}