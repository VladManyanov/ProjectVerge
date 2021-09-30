package com.pverge.core.api.game;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pverge.core.be.EdgePlayersBE;

/**
 * Edge - Vehicles requests & management
 * @author Hypernucle
 */
@Path("/v2")
public class EdgeVehicles {
	
	EdgePlayersBE edgePlayersBE = new EdgePlayersBE();
	private static String forcePlayerId = "33";
	private static String forceVehicleId = "16666";
	// TODO 
	
	/**
	 * Fetch player vehicles request (Array)
	 * @return Player vehicles
	 */
	@GET
	@Path("vehicles")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiVehicles(@HeaderParam("_") String someValue) {
		JsonArray rootArrayJson = new JsonArray();
		JsonObject carJson = new JsonObject();
		rootArrayJson.add(carJson);
		
		carJson.addProperty("embededId", forceVehicleId); // on 1 more than Id value for some reason
		carJson.addProperty("pid", forcePlayerId);
		carJson.addProperty("code", 224); // Swedish sedan car
		carJson.addProperty("createdat", "2017-11-12T18:42:19.874Z");
		carJson.addProperty("updatedat", "2020-08-12T20:42:53.861Z");
		carJson.addProperty("__v", 1); // Unknown parameter
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
		carJson.addProperty("id", forceVehicleId); // vehicle id
		
		JsonObject carStatus = new JsonObject();
		carStatus.addProperty("topSpeed", 562);
		carStatus.addProperty("acceleration", 569);
		carStatus.addProperty("nitroCapacity", 559);
		carStatus.addProperty("strength", 409);
		carStatus.addProperty("durability", 397);
		carJson.add("status", carStatus);
		
		carJson.addProperty("ovr", 573);
		carJson.addProperty("clazz", "A");

		System.out.println("### [Vehicles] Player vehicles request from player ID " + forcePlayerId + ".");
	    return rootArrayJson.toString();
	}
	
	/**
	 * IGR Vehicles request. Probably related to the Rental or Premium cars
	 */
	@GET
	@Path("igrvehicles")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiIGRVehicles(@HeaderParam("_") String someValue) {
		JsonArray rootArrayJson = new JsonArray();
		
		System.out.println("### [Vehicles] Player IGR vehicles request from player ID " + forcePlayerId + ".");
	    return rootArrayJson.toString();
	}
	
	
    
}