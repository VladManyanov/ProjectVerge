package com.pverge.core.be;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pverge.core.db.PlayerVehicleDBLoader;
import com.pverge.core.db.dbobjects.PlayerVehicleEntity;

/**
 * Edge - Player data requests
 * @author Hypernucle
 */
@Stateless
public class EdgeVehiclesBE {

	@EJB
	private PlayerVehicleDBLoader playerVehicleDB;
	
	/**
	 * Common player data, same on several requests
	 * @return Player data
	 */
	public String loadPlayerVehicles(String pid) {
		JsonArray rootArrayJson = new JsonArray();
		
		List<PlayerVehicleEntity> dbVehicles = playerVehicleDB.getPlayerVehicles(pid);
		for (PlayerVehicleEntity vehicle : dbVehicles) {
			JsonObject carJson = new JsonObject();
			rootArrayJson.add(carJson);
			
			carJson.addProperty("embededId", String.valueOf(vehicle.getId())); // on 1 more than Id value for some reason
			carJson.addProperty("pid", pid);
			carJson.addProperty("code", vehicle.getVcode()); 
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
			carJson.addProperty("id", String.valueOf(vehicle.getId())); // vehicle id
			
			JsonObject carStatus = new JsonObject();
			carStatus.addProperty("topSpeed", 562);
			carStatus.addProperty("acceleration", 569);
			carStatus.addProperty("nitroCapacity", 559);
			carStatus.addProperty("strength", 409);
			carStatus.addProperty("durability", 397);
			carJson.add("status", carStatus);
			
			carJson.addProperty("ovr", 573);
			carJson.addProperty("clazz", "A");
		}
		
		return rootArrayJson.toString();
	}
}