package com.pverge.core.api.game;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pverge.core.db.PlayerDBLoader;

/**
 * Edge - Open World activity
 * @author Hypernucle
 */
@Path("/v2")
public class EdgeOpenWorld {
	
	@EJB
	private PlayerDBLoader playerDB;

	private static int maxCampaignCode = 70;
	// TODO Get the event ID from Campaign event start request
	
	/**
	 * Get player OW mileage request. Gives rewards for Open World driving
	 * @return Player mileage info
	 */
	@GET
	@Path("openworld/drive/mileage/{playerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiOWMileage(@PathParam(value = "playerId") String playerId) {
		JsonObject rootJson = new JsonObject();
		JsonObject mileageJson = new JsonObject();
		rootJson.add("mileageInfo", mileageJson);
		mileageJson.addProperty("pid", playerId); 
		mileageJson.addProperty("mileage", 2015494);
		mileageJson.addProperty("rewardCount", 134);
		mileageJson.addProperty("rewardedAt", "2021-09-18T16:42:35.205Z");
		
		rootJson.addProperty("rewardMileage", 15000); 
		rootJson.addProperty("updateMileageThreshold", 1000); 
		
		System.out.println("### [Open World] Get Mileage request from player ID " + playerId + ".");
	    return rootJson.toString();
	}
	
	/**
	 * Get available Open World events (Missions) request
	 */
	@GET
	@Path("openworld/missions/{playerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiOWMissions(@PathParam(value = "playerId") String playerId) {
		// TODO Lazy
		String json = "{\"pid\":\"33\",\"missions\":{\"1\":{\"record\":1},\"2\":{\"record\":1},\"3\":{\"record\":1},\"4\":{\"record\":1},\"5\":{\"record\":1},\"6\":{\"record\":1},\"7\":{\"record\":1},\"8\":{\"record\":1},\"9\":{\"record\":1},\"10\":{\"record\":1},\"11\":{\"record\":1},\"12\":{\"record\":1},\"13\":{\"record\":4},\"14\":{\"record\":1},\"15\":{\"record\":1},\"16\":{\"record\":1},\"17\":{\"record\":1},\"18\":{\"record\":1},\"19\":{\"record\":1},\"21\":{\"record\":127266},\"22\":{\"record\":74000},\"23\":{\"record\":107033},\"24\":{\"record\":119832},\"25\":{\"record\":150132},\"26\":{\"record\":138232},\"27\":{\"record\":78665},\"28\":{\"record\":220833},\"29\":{\"record\":118433},\"30\":{\"record\":156933},\"31\":{\"record\":213832},\"32\":{\"record\":183399},\"33\":{\"record\":181233},\"34\":{\"record\":96300},\"35\":{\"record\":129833},\"41\":{\"record\":24499},\"42\":{\"record\":1},\"43\":{\"record\":1},\"44\":{\"record\":1},\"45\":{\"record\":2},\"101\":{\"record\":1},\"102\":{\"record\":1},\"103\":{\"record\":1},\"104\":{\"record\":1},\"105\":{\"record\":1},\"106\":{\"record\":1},\"107\":{\"record\":1},\"108\":{\"record\":1},\"109\":{\"record\":1},\"110\":{\"record\":1},\"111\":{\"record\":1},\"112\":{\"record\":1},\"113\":{\"record\":1},\"114\":{\"record\":1},\"115\":{\"record\":1},\"116\":{\"record\":1},\"117\":{\"record\":-1},\"118\":{\"record\":1},\"119\":{\"record\":1},\"120\":{\"record\":3},\"121\":{\"record\":1},\"122\":{\"record\":1},\"123\":{\"record\":1},\"124\":{\"record\":1},\"125\":{\"record\":1},\"126\":{\"record\":1},\"127\":{\"record\":3},\"128\":{\"record\":1},\"129\":{\"record\":1},\"130\":{\"record\":1},\"131\":{\"record\":1},\"201\":{\"record\":67766},\"202\":{\"record\":75766},\"203\":{\"record\":50966},\"204\":{\"record\":140566},\"205\":{\"record\":117565},\"206\":{\"record\":194666},\"207\":{\"record\":360866},\"208\":{\"record\":233866},\"209\":{\"record\":338500},\"210\":{\"record\":150766},\"211\":{\"record\":127233},\"212\":{\"record\":120700},\"213\":{\"record\":93265},\"214\":{\"record\":97799},\"215\":{\"record\":139531},\"216\":{\"record\":-1}}}";
		
		System.out.println("### [Open World] Get Mission-list request from player ID " + playerId + ".");
	    return json;
	}
	
	/**
	 * Stop current mission request
	 */
	@POST
	@Path("openworld/missions/{playerId}/@end")
	@Produces(MediaType.APPLICATION_JSON)
	public Response apiOWEndMission(@PathParam(value = "playerId") String playerId) {
		// TODO Request sends the code and isAbandoned status
		System.out.println("### [Open World] Stop current mission request from player ID " + playerId + ".");
	    return Response.ok().build();
	}
	
	/**
	 * Player OW campaign status request. Code 70 means Level of campaign progress (which is finished)
	 */
	@GET
	@Path("openworld/campaign/{playerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiOWCampaign(@PathParam(value = "playerId") String playerId) {
		JsonObject rootJson = new JsonObject();
		rootJson.addProperty("pid", playerId);
		rootJson.addProperty("code", playerDB.getPlayer(playerId).getCampaignCode());
		
		JsonObject tasksJson = new JsonObject();
		tasksJson.addProperty("86", 1);
		tasksJson.addProperty("530", 1);
		tasksJson.addProperty("1011", 1);
		tasksJson.addProperty("7008", 40000);
		rootJson.add("tasks", tasksJson);
		
		rootJson.addProperty("status", 1); // 0 - NotStarted, 1 - InProgress, 2 - Completed
		
		System.out.println("### [Open World] Get Campaign status request from player ID " + playerId + ".");
	    return rootJson.toString();
	}
	
	/**
	 * Start event from Campaign request
	 */
	@POST
	@Path("openworld/campaign/{playerId}/@start")
	@Produces(MediaType.APPLICATION_JSON)
	public Response apiOWCampaignStart(@PathParam(value = "playerId") String playerId) {
		System.out.println("### [Open World] Start Campaign event request from player ID " + playerId + ".");
	    return Response.ok().build();
	}
	
	/**
	 * Update the tasks status of Campaign request
	 */
	@POST
	@Path("openworld/campaign/task/{playerId}/@update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response apiOWCampaignUpdate(@PathParam(value = "playerId") String playerId) {
		System.out.println("### [Open World] Update Campaign tasks request from player ID " + playerId + ".");
	    return Response.ok().build();
	}
	
	/**
	 * Finish task list of Campaign request
	 */
	@POST
	@Path("openworld/campaign/{playerId}/@complete")
	@Produces(MediaType.APPLICATION_JSON)
	public Response apiOWCampaignComplete(@PathParam(value = "playerId") String playerId) {
		int playerCampaignCode = playerDB.getPlayer(playerId).getCampaignCode();
		if (playerCampaignCode < maxCampaignCode) {
			playerDB.setCampaignCode(playerId, playerCampaignCode + 1);
		}

		System.out.println("### [Open World] Campaign tasks complete request from player ID " + playerId + ".");
	    return Response.ok().build();
	}
	
	/**
	 * Get Open World spots records request
	 * @return Player records on various Jumps and Speed-zones
	 */
	@GET
	@Path("openworld/spots/{playerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiOWSpots(@PathParam(value = "playerId") String playerId) {
		JsonObject rootJson = new JsonObject();
		rootJson.addProperty("pid", playerId);
		
		JsonObject spotsJson = new JsonObject();
		rootJson.add("spots", spotsJson);
		JsonObject spots1Json = new JsonObject();
		spots1Json.addProperty("type", 1);
		spots1Json.addProperty("record", 276.9065246582031); 
		rootJson.add("1", spots1Json);
		
		System.out.println("### [Open World] Get Spots records request from player ID " + playerId + ".");
	    return rootJson.toString();
	}
	
	/**
	 * Get available Portals (Hideout locations) request. Used as a quick teleports across the map
	 * @return Portals which is available for player
	 */
	@GET
	@Path("openworld/portals/{playerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiOWPortals(@PathParam(value = "playerId") String playerId) {
		JsonObject rootJson = new JsonObject();
		rootJson.addProperty("pid", playerId);
		
		JsonArray portalsArray = new JsonArray();
		rootJson.add("portals", portalsArray);
		for (int i = 0; i < 21; i++) {
			portalsArray.add(i); // 21 Portal locations
		}
		
		System.out.println("### [Open World] Get Portals list request from player ID " + playerId + ".");
	    return rootJson.toString();
	}
	
	/**
	 * Get current OW Limited Missions request. Gives special rewards for driving on specified Mission races
	 * Note: resetDate parameter must be actual and on 1 day after server time, otherwise this request will stuck on infinite loop
	 * @return Limited missions information
	 */
	@GET
	@Path("openworld/limitedmission/{playerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiOWLimitedMissions(@PathParam(value = "playerId") String playerId) {
		JsonObject rootJson = new JsonObject();
		rootJson.addProperty("currentSeasonNo", 1096);
		
		JsonObject progressJson = new JsonObject();
		rootJson.add("progress", progressJson);
		
		JsonObject progress7Json = new JsonObject();
		progress7Json.addProperty("currentCount", 0);
		progress7Json.addProperty("limitCount", 3);
		progressJson.add("7", progress7Json);
		//
		JsonObject progress9Json = new JsonObject();
		progress9Json.addProperty("currentCount", 0);
		progress9Json.addProperty("limitCount", 3);
		progressJson.add("9", progress9Json);
		//
		JsonObject progress16Json = new JsonObject();
		progress16Json.addProperty("currentCount", 0);
		progress16Json.addProperty("limitCount", 3);
		progressJson.add("16", progress16Json);
		//
		JsonObject progress27Json = new JsonObject();
		progress27Json.addProperty("currentCount", 0);
		progress27Json.addProperty("limitCount", 3);
		progressJson.add("27", progress27Json);
		//
		JsonObject progress31Json = new JsonObject();
		progress31Json.addProperty("currentCount", 0);
		progress31Json.addProperty("limitCount", 3);
		progressJson.add("31", progress31Json);
		
		rootJson.addProperty("__v", 0);
		rootJson.addProperty("id", playerId);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		rootJson.addProperty("resetDate", LocalDateTime.now().plusDays(1).format(formatter));
		
		System.out.println("### [Open World] Current Limited Missions request from player ID " + playerId + ".");
	    return rootJson.toString();
	}
    
}