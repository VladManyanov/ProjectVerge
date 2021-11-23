package com.pverge.core.be;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pverge.core.db.AttrsPartsInfoDBLoader;
import com.pverge.core.db.PlayerVehicleDBLoader;
import com.pverge.core.db.RatingGradesDBLoader;
import com.pverge.core.db.RatingVehiclesDBLoader;
import com.pverge.core.db.VehicleSteeringDBLoader;
import com.pverge.core.db.dbobjects.CarCustomizationEntity;
import com.pverge.core.db.dbobjects.PlayerVehicleEntity;
import com.pverge.core.db.dbobjects.RatingGradesEntity;
import com.pverge.core.db.dbobjects.RatingVehiclesEntity;
import com.pverge.core.db.dbobjects.VehicleSteeringEntity;

/**
 * Edge - Player data requests
 * @author Hypernucle
 */
@Stateless
public class EdgeVehiclesBE {

	@EJB
	private PlayerVehicleDBLoader playerVehicleDB;
	@EJB
	private VehicleSteeringDBLoader vehicleSteeringDB;
	@EJB
	private RatingVehiclesDBLoader ratingVehiclesDB;
	@EJB
	private RatingGradesDBLoader ratingGradesDB;
	@EJB
	private AttrsPartsInfoDBLoader attrsPartsInfoDBLoader;
	
	/**
	 * Common player data, same on several requests
	 * @return Player data
	 */
	public String loadPlayerVehicles(String pid) {
		JsonArray rootArrayJson = new JsonArray();
		
		List<PlayerVehicleEntity> dbVehicles = playerVehicleDB.getPlayerVehicles(pid);
		for (PlayerVehicleEntity vehicle : dbVehicles) {
			rootArrayJson.add(prepareVehicleData(vehicle));
		}
		return rootArrayJson.toString();
	}
	
	/**
	 * Prepare common vehicle data format
	 * @return Vehicle data
	 */
	public JsonObject prepareVehicleData(PlayerVehicleEntity vehicle) {
		JsonObject carJson = new JsonObject();
		RatingVehiclesEntity ratingEntity = calcCarRating(vehicle);
			
		carJson.addProperty("embededId", vehicle.getId()); // on 1 more than Id value for some reason
		carJson.addProperty("pid", vehicle.getPid());
		carJson.addProperty("code", vehicle.getVcode()); 
		carJson.addProperty("createdat", "2017-11-12T18:42:19.874Z");
		carJson.addProperty("updatedat", "2020-08-12T20:42:53.861Z");
		carJson.addProperty("__v", 1); // Unknown parameter
		carJson.add("steering", prepareSteering(vehicle.getSteering()));
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
		carParts.addProperty("transmission", vehicle.getPartTransmission());
		carParts.addProperty("engine", vehicle.getPartEngine());
		carJson.add("parts", carParts);
			
		carJson.addProperty("favorite", false);
		carJson.addProperty("grade", vehicle.getGrade());
		carJson.addProperty("__v", 1);
		carJson.addProperty("id", vehicle.getId()); // vehicle id
			
		JsonObject carStatus = new JsonObject();
		carStatus.addProperty("topSpeed", ratingEntity.getTopSpeed());
		carStatus.addProperty("acceleration", ratingEntity.getAcceleration());
		carStatus.addProperty("nitroCapacity", ratingEntity.getNitroCapacity());
		carStatus.addProperty("strength", ratingEntity.getStrength());
		carStatus.addProperty("durability", ratingEntity.getDurability());
		carJson.add("status", carStatus);
			
		carJson.addProperty("ovr", ratingEntity.getOvrDefault());
		carJson.addProperty("igr", false);
		carJson.addProperty("clazz", ratingEntity.getClazz());
		
		return carJson;
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
	
	/**
	 * Prepare vehicle steering settings
	 */
	public JsonArray prepareSteering(VehicleSteeringEntity steering) {
		JsonArray arraySteering = new JsonArray();
		if (steering == null) { // Default steering settings entry
			for (int i = 0; i < 14; i++) { // Must be 14 values
				JsonObject valJson = new JsonObject();
				valJson.addProperty("code", i);
				valJson.addProperty("val", 50);
				arraySteering.add(valJson);
			}
		} else {
			JsonObject val0Json = new JsonObject();
			val0Json.addProperty("code", 0);
			val0Json.addProperty("val", steering.getV0());
			arraySteering.add(val0Json);
			//
			JsonObject val1Json = new JsonObject();
			val1Json.addProperty("code", 1);
			val1Json.addProperty("val", steering.getV1());
			arraySteering.add(val1Json);
			//
			JsonObject val2Json = new JsonObject();
			val2Json.addProperty("code", 2);
			val2Json.addProperty("val", steering.getV2());
			arraySteering.add(val2Json);
			//
			JsonObject val3Json = new JsonObject();
			val3Json.addProperty("code", 3);
			val3Json.addProperty("val", steering.getV3());
			arraySteering.add(val3Json);
			//
			JsonObject val4Json = new JsonObject();
			val4Json.addProperty("code", 4);
			val4Json.addProperty("val", steering.getV4());
			arraySteering.add(val4Json);
			//
			JsonObject val5Json = new JsonObject();
			val5Json.addProperty("code", 5);
			val5Json.addProperty("val", steering.getV5());
			arraySteering.add(val5Json);
			//
			JsonObject val6Json = new JsonObject();
			val6Json.addProperty("code", 6);
			val6Json.addProperty("val", steering.getV6());
			arraySteering.add(val6Json);
			//
			JsonObject val7Json = new JsonObject();
			val7Json.addProperty("code", 7);
			val7Json.addProperty("val", steering.getV7());
			arraySteering.add(val7Json);
			//
			JsonObject val8Json = new JsonObject();
			val8Json.addProperty("code", 8);
			val8Json.addProperty("val", steering.getV8());
			arraySteering.add(val8Json);
			//
			JsonObject val9Json = new JsonObject();
			val9Json.addProperty("code", 9);
			val9Json.addProperty("val", steering.getV9());
			arraySteering.add(val9Json);
			//
			JsonObject val10Json = new JsonObject();
			val10Json.addProperty("code", 10);
			val10Json.addProperty("val", steering.getV10());
			arraySteering.add(val10Json);
			//
			JsonObject val11Json = new JsonObject();
			val11Json.addProperty("code", 11);
			val11Json.addProperty("val", steering.getV11());
			arraySteering.add(val11Json);
			//
			JsonObject val12Json = new JsonObject();
			val12Json.addProperty("code", 12);
			val12Json.addProperty("val", steering.getV12());
			arraySteering.add(val12Json);
			//
			JsonObject val13Json = new JsonObject();
			val13Json.addProperty("code", 13);
			val13Json.addProperty("val", steering.getV13());
			arraySteering.add(val13Json);
		}
		return arraySteering;
	}
	
	/**
	 * Save default vehicle steering entry in DB
	 */
	public void createDefaultSteering(String vid, PlayerVehicleEntity vehicle) {
		VehicleSteeringEntity defaultSteering = new VehicleSteeringEntity();
		defaultSteering.setVid(vid);
		defaultSteering.setV0(50); defaultSteering.setV1(50); defaultSteering.setV2(50);
		defaultSteering.setV3(50); defaultSteering.setV4(50); defaultSteering.setV5(50);
		defaultSteering.setV6(50); defaultSteering.setV7(50); defaultSteering.setV8(50);
		defaultSteering.setV9(50); defaultSteering.setV10(50); defaultSteering.setV11(50);
		defaultSteering.setV12(50); defaultSteering.setV13(50);
		vehicleSteeringDB.insert(defaultSteering);
		
		vehicle.setSteering(defaultSteering);
		playerVehicleDB.update(vehicle);
		System.out.println("### [Vehicles] Vehicle ID " + vehicle.getId() + 
				" create steering settings request from player ID " + vehicle.getPid() + ".");
	}
	
	/**
	 * Calculate car rating with parts & current grade
	 */
	public RatingVehiclesEntity calcCarRating(PlayerVehicleEntity vehicle) {
		RatingVehiclesEntity output = new RatingVehiclesEntity();
		RatingVehiclesEntity ratingEntity = ratingVehiclesDB.findByCode(vehicle.getVcode());
		RatingGradesEntity ratingGradesEntity = ratingGradesDB.findGrade(vehicle.getVcode(), vehicle.getGrade());
		if (ratingEntity == null) { // Note: some of vehicles without the rating, still could have a Grades data on DB
			ratingEntity = dummyRating();
			ratingGradesEntity = dummyGradeRating(); 
			System.out.println("!!! [Vehicle] Rating entity of vehicle Code " + vehicle.getVcode() + " cannot be found.");
		}
		
		output.setClazz(ratingEntity.getClazz());
		output.setTopSpeed(ratingEntity.getTopSpeed() + ratingGradesEntity.getTopSpeed());
		output.setAcceleration(ratingEntity.getAcceleration() + ratingGradesEntity.getAcceleration());
		output.setNitroCapacity(ratingEntity.getNitroCapacity() + ratingGradesEntity.getNitroCapacity());
		output.setStrength(ratingEntity.getStrength() + ratingGradesEntity.getStrength());
		output.setDurability(ratingEntity.getDurability() + ratingGradesEntity.getDurability());
		
		if (vehicle.getPartEngine() != 0) {
			output.setTopSpeed(output.getTopSpeed() + 
					attrsPartsInfoDBLoader.getPartInfo(vehicle.getPartEngine()).getOvrTopSpeed());
		}
		if (vehicle.getPartTransmission() != 0) {
			output.setAcceleration(output.getAcceleration() + 
					attrsPartsInfoDBLoader.getPartInfo(vehicle.getPartTransmission()).getOvrAcceleration());
		}
		if (vehicle.getPartNitroTank() != 0) {
			output.setNitroCapacity(output.getNitroCapacity() + 
					attrsPartsInfoDBLoader.getPartInfo(vehicle.getPartNitroTank()).getOvrNitroCapacity());
		}
		if (vehicle.getPartBumper() != 0) {
			output.setStrength(output.getStrength() + 
					attrsPartsInfoDBLoader.getPartInfo(vehicle.getPartBumper()).getOvrStrength());
		}
		if (vehicle.getPartFrame() != 0) {
			output.setDurability(output.getDurability() + 
					attrsPartsInfoDBLoader.getPartInfo(vehicle.getPartFrame()).getOvrDurability());
		}
		output.setOvrDefault( // Calculate OVR without Strength or Durability, like original values
				((output.getTopSpeed() + output.getAcceleration() + output.getNitroCapacity()) / 3) );
		
		return output;
	}
	
	private RatingVehiclesEntity dummyRating() {
		RatingVehiclesEntity dummy = new RatingVehiclesEntity();
		dummy.setTopSpeed(0);
		dummy.setStrength(0);
		dummy.setNitroCapacity(0);
		dummy.setDurability(0);
		dummy.setAcceleration(0);
		dummy.setClazz("C");
		return dummy;
	}
	
	private RatingGradesEntity dummyGradeRating() {
		RatingGradesEntity dummy = new RatingGradesEntity();
		dummy.setTopSpeed(0);
		dummy.setStrength(0);
		dummy.setNitroCapacity(0);
		dummy.setDurability(0);
		dummy.setAcceleration(0);
		return dummy;
	}
}
