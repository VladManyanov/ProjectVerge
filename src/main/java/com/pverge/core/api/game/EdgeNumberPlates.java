package com.pverge.core.api.game;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * Edge - Number plate requests & management
 * Majority of requests contains some unknown path value, example: _=1632169536992
 * @author Hypernucle
 */
@Path("/v2")
public class EdgeNumberPlates {
	
	private static String forcePlayerId = "33";
	// TODO 
	
	/**
	 * Default number plate request, used by AI drivers and players on some cases
	 * @return Default Number Plate entity
	 */
	@GET
	@Path("numberplate/defaultnumberplate")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiDefaultNumberPlate(@HeaderParam("_") String someValue) {
		JsonObject rootJson = new JsonObject();
		rootJson.addProperty("pid", forcePlayerId);
		rootJson.addProperty("prefix", "PJ");
		rootJson.addProperty("plateNumber", "VERGE");
		rootJson.addProperty("templateCode", 0);
		rootJson.addProperty("background", 0);
		rootJson.addProperty("fontColor", "#cccccc");
		
		System.out.println("### [Number Plate] Default plate request from player ID " + forcePlayerId + ".");
	    return rootJson.toString();
	}
	
	/**
	 * Number plate select-lists request. Plates to be randomly choose by server in lottery-style
	 * @return Select-list
	 */
	@GET
	@Path("numberplate/{playerId}/selectlists")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiNumberPlateSelectLists(@PathParam(value = "playerId") String playerId) {
		JsonObject rootJson = new JsonObject();
		rootJson.addProperty("pid", playerId);
		rootJson.addProperty("exchangeProb", 0);
		rootJson.addProperty("lotteryCode", 0);
		rootJson.addProperty("lotteryProb", 0);
		
		JsonArray advanceJson = new JsonArray();
		rootJson.add("advance", advanceJson);
		JsonArray exchangeJson = new JsonArray();
		rootJson.add("exchange", exchangeJson);
		JsonArray expirationJson = new JsonArray();
		rootJson.add("expiration", expirationJson);
		JsonArray lotteryJson = new JsonArray();
		rootJson.add("lottery", lotteryJson);
		JsonArray firstRandomJson = new JsonArray();
		rootJson.add("firstRandom", firstRandomJson);
		
		rootJson.addProperty("first", false);
		
		System.out.println("### [Number Plate] Plates Select-list request from player ID " + playerId + ".");
	    return rootJson.toString();
	}
	
	/**
	 * Number Plate slots of player request, means inventory of available plates
	 */
	@GET
	@Path("numberplate/players/{playerId}/slots")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiNumberPlatePlayersSlots(@PathParam(value = "playerId") String playerId) {
		JsonArray rootJsonArray = new JsonArray();
		JsonObject plateJson = new JsonObject();
		rootJsonArray.add(plateJson);
		plateJson.addProperty("pid", playerId);
		plateJson.addProperty("expiration", 0);
		plateJson.addProperty("templateCode", 0);
		plateJson.addProperty("plateNumber", "VERGE");
		plateJson.addProperty("id", "12345");
		
		System.out.println("### [Number Plate] Plates Select-list request from player ID " + playerId + ".");
	    return rootJsonArray.toString();
	}
	
	/**
	 * Current player Number Plate request
	 * @return Plate info
	 */
	@GET
	@Path("numberplate/playerinfo/{playerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiPlatePlayerInfo(@PathParam(value = "playerId") String playerId) {
		JsonObject rootJson = new JsonObject();
		rootJson.addProperty("pid", playerId);
		
		JsonArray selectedArray = new JsonArray();
		JsonObject selectedJson = new JsonObject();
		selectedJson.addProperty("pid", playerId); 
		selectedJson.addProperty("_id", "12345"); 
		selectedJson.addProperty("expiration", 0); 
		selectedJson.addProperty("templateCode", 0); 
		selectedJson.addProperty("plateNumber", "VERGE"); 
		rootJson.add("selected", selectedArray);
		selectedArray.add(selectedJson);
		
		rootJson.addProperty("prefix", "PJ");
		rootJson.addProperty("prefixResetNum", 0);
		rootJson.addProperty("slotExpandSize", 0);
		
		System.out.println("### [Number Plate] Current Plate request from player ID " + playerId + ".");
	    return rootJson.toString();
	}
	
	/**
	 * Template plates info request (Array)
	 * @return Array of template Plates
	 */
	@GET
	@Path("numberplate/templatecodes")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiPlateTemplates() {
		JsonArray rootJsonArray = new JsonArray();
		JsonObject templateJson = new JsonObject();
		rootJsonArray.add(templateJson);
		
		templateJson.addProperty("appearancecode", 1);
		templateJson.addProperty("destorysp", 0);
		templateJson.addProperty("price", 0);
		templateJson.addProperty("auction", false);
		templateJson.addProperty("purchase", false);
		templateJson.addProperty("exchange", false);
		templateJson.addProperty("lottery", false);
		templateJson.addProperty("codeclass", 1);
		templateJson.addProperty("templatecode", 0);
		
		System.out.println("### [Number Plate] Template Number Plates request from player ID " + forcePlayerId + ".");
	    return rootJsonArray.toString();
	}
    
}