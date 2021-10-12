package com.pverge.core.be;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pverge.core.db.PlayerVehicleDBLoader;
import com.pverge.core.db.dbobjects.CarCustomizationEntity;
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
			carPaint.addProperty("wheelCode", vehicle.getWheelColor());
			carPaint.addProperty("wrapCode", vehicle.getWrapCode());
			carPaint.addProperty("colorCode", vehicle.getColorCode());
			carJson.add("paint", carPaint);
			
			JsonObject carParts = new JsonObject();
			carParts.addProperty("frame", vehicle.getPartFrame());
			carParts.addProperty("bumper", vehicle.getPartBumper());
			carParts.addProperty("nitroTank", vehicle.getPartNitroTank());
			carParts.addProperty("transmission", vehicle.getPartNitroTank());
			carParts.addProperty("engine", vehicle.getPartEngine());
			carJson.add("parts", carParts);
			
			carJson.addProperty("favorite", false);
			carJson.addProperty("grade", vehicle.getGrade());
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
	
	/**
	 * Apply customization item to the player car
	 */
	public void applyCustomizationItem(CarCustomizationEntity carCustomizationEntity, PlayerVehicleEntity playerVehicleEntity) {
		int colorCode = playerVehicleEntity.getColorCode();
		int wheelCode = playerVehicleEntity.getWheelColor();
		int wrapCode = playerVehicleEntity.getWrapCode();
		switch(carCustomizationEntity.getSubType()) {
		case "VEHICLECOLOR":
			colorCode = carCustomizationEntity.getCode(); break;
		case "WHEELCOLOR":
			wheelCode = carCustomizationEntity.getCode(); break;
		case "WRAP":
			wrapCode = carCustomizationEntity.getCode(); break;
		}
		System.out.println("### [Inventory] Customization item ID" + carCustomizationEntity.getCode() + " (Category: " + carCustomizationEntity.getSubType() +
				") has been applied on vehicle ID " + playerVehicleEntity.getId() + " of player ID " + playerVehicleEntity.getPid() + ".");
		playerVehicleDB.setCustomization(playerVehicleEntity.getId(), colorCode, wheelCode, wrapCode);
	}
}
