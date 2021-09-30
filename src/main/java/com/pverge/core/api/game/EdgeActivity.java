package com.pverge.core.api.game;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * Edge - Various in-game activities
 * @author Hypernucle
 */
@Path("/v2")
public class EdgeActivity {
	
	private static String forcePlayerId = "33";
	// TODO 
	
	/**
	 * Daily quests request, up to 5 quests. All quests properties is taken by ID from client Static Data list
	 * @return Quests list
	 */
	@GET
	@Path("quests")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiQuests(@HeaderParam("_") String someValue) {
		JsonObject rootJson = new JsonObject();
		rootJson.addProperty("id", forcePlayerId);
		rootJson.addProperty("qp", 290);
		rootJson.addProperty("qpResetTime", "2022-09-30T16:00:00.000Z");
		rootJson.addProperty("qpResetType", "MONTHLY");
		
		JsonArray questProgressArray = new JsonArray();
		rootJson.add("questProgress", questProgressArray);
		JsonObject q1Json = new JsonObject();
		q1Json.addProperty("code", 10103);
		q1Json.addProperty("value", 1);
		q1Json.addProperty("completed", false);
		questProgressArray.add(q1Json);
		
		rootJson.addProperty("questResetTime", "2022-09-19T16:00:00.000Z");
		rootJson.addProperty("questResetType", "DAILY");
		
		System.out.println("### [Activity] Quests list request from player ID " + forcePlayerId + ".");
	    return rootJson.toString();
	}
	
	/**
	 * Get Licenses mode data request
	 */
	@GET
	@Path("licenses/{playerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiLicensesGet(@PathParam(value = "playerId") String playerId) {
		JsonObject rootJson = new JsonObject();
		rootJson.addProperty("code", 4);
		rootJson.addProperty("createdat", "2017-11-12T18:34:50.153Z");
		rootJson.addProperty("__v", 24);
		
		JsonArray completedTestArray = new JsonArray();
		rootJson.add("completedTest", completedTestArray);
		
		rootJson.addProperty("id", playerId);
		
		System.out.println("### [Activity] Licenses data request from player ID " + playerId + ".");
	    return rootJson.toString();
	}
	
	/**
	 * Match vehicle attributes request, used during race start
	 */
	@GET
	@Path("match2/vehicle/attrs")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiMatchVehicleAttrs(@QueryParam("code") String code, @QueryParam("grade") String grade) {
		// TODO Lazy
		String json = "[{\"attr\":\"Engine.SpeedLimiter\",\"type\":\"float\",\"val\":149.7},{\"attr\":\"Engine.SpeedLimiterNOS\",\"type\":\"float\",\"val\":164.7},{\"attr\":\"Engine.Torque\",\"type\":\"graph\",\"val\":[{\"x\":1000,\"y\":0,\"z\":1},{\"x\":10200,\"y\":1865,\"z\":8},{\"x\":1000,\"y\":391.78000000000884,\"z\":32},{\"x\":1712.5,\"y\":422.98000000000883,\"z\":32},{\"x\":3012.49975,\"y\":442.98000000000883,\"z\":32},{\"x\":4774.99975,\"y\":455.78000000000884,\"z\":32},{\"x\":6000.000249999999,\"y\":465.38000000000886,\"z\":32},{\"x\":6724.99975,\"y\":457.38000000000886,\"z\":32},{\"x\":7637.5,\"y\":445.38000000000886,\"z\":32},{\"x\":8500,\"y\":422.1800000000088,\"z\":32}]},{\"attr\":\"Aerodynamics.DragCoefficient\",\"type\":\"float\",\"val\":0.523},{\"attr\":\"Transmission.GearChangeTime\",\"type\":\"float\",\"val\":0.51192},{\"attr\":\"NosConfig.Capacity\",\"type\":\"float\",\"val\":22.76},{\"attr\":\"NosConfig.TorqueBoost\",\"type\":\"float\",\"val\":0.972},{\"attr\":\"NosConfig.NosRechargeFactor\",\"type\":\"float\",\"val\":1.1264},{\"attr\":\"NosConfig.NosDispenseFactor\",\"type\":\"float\",\"val\":1.5368},{\"attr\":\"Strength.Strength\",\"type\":\"float\",\"val\":1.582},{\"attr\":\"Durability.Capacity\",\"type\":\"float\",\"val\":50.56},{\"attr\":\"Chassis.Mass\",\"type\":\"float\",\"val\":1800},{\"attr\":\"Transmission.TorqueSplit\",\"type\":\"float\",\"val\":0.1},{\"attr\":\"Transmission.TorqueSplitInDrift\",\"type\":\"float\",\"val\":0.1}]";
		
		System.out.println("### [Activity] Match vehicle attrib request from player ID " + forcePlayerId + ", vehicle code: " + code + ".");
	    return json;
	}
    
}