package com.pverge.core.api.game;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pverge.core.be.EdgeInventoryBE;
import com.pverge.core.be.EdgePlayersBE;
import com.pverge.core.be.EdgeSocketVehiclesBE;
import com.pverge.core.be.EdgeVehiclesBE;
import com.pverge.core.db.AttrsPartsInfoDBLoader;
import com.pverge.core.db.CarCustomizationDBLoader;
import com.pverge.core.db.PlayerVehicleDBLoader;
import com.pverge.core.db.dbobjects.AttrsPartsInfoEntity;
import com.pverge.core.db.dbobjects.CarCustomizationEntity;
import com.pverge.core.db.dbobjects.PlayerVehicleEntity;

/**
 * Edge - Vehicles requests & management
 * @author Hypernucle
 */
@Path("/v2")
public class EdgeInventory {
	
	@EJB
	private EdgeSocketVehiclesBE edgeSocketVehiclesBE;
	@EJB
	private EdgePlayersBE edgePlayersBE;
	@EJB
	private EdgeInventoryBE edgeInventoryBE;
	@EJB
	private CarCustomizationDBLoader carCustomizationDB;
	@EJB
	private PlayerVehicleDBLoader playerVehicleDB;
	@EJB
	private EdgeVehiclesBE edgeVehiclesBE;
	@EJB
	private AttrsPartsInfoDBLoader attrsPartsInfoDBLoader;
	
	private static String forcePlayerId = "33";
	// TODO 
	
	/**
	 * Player parts request
	 * @return Parts array
	 */
	@GET
	@Path("parts")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiInventoryParts(@HeaderParam("_") String someValue) {
		System.out.println("### [Inventory] Player Parts request from player ID " + forcePlayerId + ".");
	    return edgeInventoryBE.fetchFakePerformanceInventory();
	}
	
	/**
	 * Player inventory request
	 * @return Inventory
	 */
	@GET
	@Path("inventories/{playerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiInventories(@HeaderParam("_") String someValue, @PathParam(value = "playerId") String playerId) {
		JsonObject rootJson = new JsonObject();
		rootJson.addProperty("__v", 8);
		
		JsonArray itemsArrayJson = new JsonArray();
		rootJson.add("items", itemsArrayJson);
		rootJson.addProperty("id", playerId);
		
		System.out.println("### [Inventory] Main inventory request from player ID " + playerId + ".");
	    return rootJson.toString();
	}
	
	/**
	 * Player inbox items request
	 * @return Inbox
	 */
	@GET
	@Path("inboxes/{playerId}/packages")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiInbox(@HeaderParam("_") String someValue, @PathParam(value = "playerId") String playerId) {
		JsonArray rootArray = new JsonArray();;
		
		System.out.println("### [Inventory] Main inbox request from player ID " + playerId + ".");
	    return rootArray.toString();
	}
	
	/**
	 * Player customization inventory request
	 * @return Inventory
	 */
	@GET
	@Path("tuning/inventories/{playerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiTuningInventories(@HeaderParam("_") String someValue, 
			@PathParam(value = "playerId") String playerId) {
		JsonObject rootJson = new JsonObject();
		rootJson.addProperty("__v", 8);
		rootJson.addProperty("checkedat", "2021-10-05T02:36:43.446Z");
		
		JsonArray itemsArrayJson = new JsonArray();
		rootJson.add("items", itemsArrayJson);
		rootJson.addProperty("id", playerId);
		
		System.out.println("### [Inventory] Visual customization inventory request from player ID " + playerId + ".");
	    return rootJson.toString();
	}
	
	/**
	 * Vehicle Blueprints request (Array
	 * @return Array of player blueprints
	 */
	@GET
	@Path("blueprints")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiInventoryGetBlueprints() {
		// TODO Lazy
		String jsonStr = "[{\"pid\":\"33\",\"code\":181,\"updatedat\":\"2017-11-12T19:06:16.803Z\",\"__v\":0,\"count\":3,\"id\":\"5a089ba896894b00066a024b\"},{\"pid\":\"33\",\"code\":92,\"updatedat\":\"2017-11-18T19:42:52.979Z\",\"__v\":0,\"count\":5,\"id\":\"5a108d3ca483d90007645cfd\"},{\"pid\":\"33\",\"code\":46,\"updatedat\":\"2017-11-19T16:15:15.592Z\",\"__v\":0,\"count\":19,\"id\":\"5a11ae138595d00007a48364\"},{\"pid\":\"33\",\"code\":109,\"updatedat\":\"2017-11-19T18:05:17.250Z\",\"__v\":0,\"count\":3,\"id\":\"5a11c7ddf00e91000788286c\"},{\"pid\":\"33\",\"code\":47,\"updatedat\":\"2017-11-19T18:28:03.711Z\",\"__v\":0,\"count\":2,\"id\":\"5a11cd3322dbdf0006ec0c90\"},{\"pid\":\"33\",\"code\":157,\"updatedat\":\"2017-11-25T18:34:35.444Z\",\"__v\":0,\"count\":1,\"id\":\"5a19b7bb2d8a67000601032f\"},{\"pid\":\"33\",\"code\":13,\"updatedat\":\"2018-02-15T08:26:13.533Z\",\"__v\":0,\"count\":20,\"id\":\"5a854425b8a3280006548524\"},{\"pid\":\"33\",\"code\":194,\"updatedat\":\"2018-04-04T20:28:30.716Z\",\"__v\":0,\"count\":99,\"id\":\"5ac5356edfc3cb000154e387\"},{\"pid\":\"33\",\"code\":44,\"updatedat\":\"2018-11-24T18:28:18.133Z\",\"__v\":0,\"count\":480,\"id\":\"5bf9984251856d975cce2270\"},{\"pid\":\"33\",\"code\":128,\"updatedat\":\"2018-11-24T18:52:59.372Z\",\"__v\":0,\"count\":21,\"id\":\"5bf99e0befc61197b913ee47\"},{\"pid\":\"33\",\"code\":158,\"updatedat\":\"2018-11-24T19:04:51.877Z\",\"__v\":0,\"count\":118,\"id\":\"5bf9a0d3317b3957182a2ae1\"},{\"pid\":\"33\",\"code\":266,\"updatedat\":\"2018-11-24T19:16:34.556Z\",\"__v\":0,\"count\":30,\"id\":\"5bf9a39251816030be7e3a40\"},{\"pid\":\"33\",\"code\":104,\"updatedat\":\"2018-11-24T19:35:51.541Z\",\"__v\":0,\"count\":115,\"id\":\"5bf9a817f27b064eaabdbd5f\"},{\"pid\":\"33\",\"code\":148,\"updatedat\":\"2018-11-24T19:46:51.635Z\",\"__v\":0,\"count\":21,\"id\":\"5bf9aaab50953c8769eb75b6\"},{\"pid\":\"33\",\"code\":179,\"updatedat\":\"2018-11-24T20:41:27.757Z\",\"__v\":0,\"count\":310,\"id\":\"5bf9b7775d4fe0a2458c01ab\"},{\"pid\":\"33\",\"code\":146,\"updatedat\":\"2018-11-24T20:51:07.460Z\",\"__v\":0,\"count\":390,\"id\":\"5bf9b9bbcd603068aaa7dec4\"},{\"pid\":\"33\",\"code\":1,\"updatedat\":\"2018-12-08T20:07:08.212Z\",\"__v\":0,\"count\":400,\"id\":\"5c0c246ce4735879af84e14e\"},{\"pid\":\"33\",\"code\":122,\"updatedat\":\"2018-12-08T20:07:08.213Z\",\"__v\":0,\"count\":650,\"id\":\"5c0c246ce4735864b484e14f\"},{\"pid\":\"33\",\"code\":63,\"updatedat\":\"2018-12-15T19:40:22.029Z\",\"__v\":0,\"count\":80,\"id\":\"5c1558a6613adf6a107948b9\"},{\"pid\":\"33\",\"code\":31,\"updatedat\":\"2018-12-15T19:40:40.770Z\",\"__v\":0,\"count\":81,\"id\":\"5c1558b8f53a1e497ce5824d\"},{\"pid\":\"33\",\"code\":69,\"updatedat\":\"2018-12-15T20:41:52.274Z\",\"__v\":0,\"count\":490,\"id\":\"5c15671033aa344e5ad64035\"},{\"pid\":\"33\",\"code\":2,\"updatedat\":\"2018-12-15T20:42:33.773Z\",\"__v\":0,\"count\":69,\"id\":\"5c156739613adf0d95799f30\"},{\"pid\":\"33\",\"code\":183,\"updatedat\":\"2018-12-25T18:02:01.277Z\",\"__v\":0,\"count\":3,\"id\":\"5c227099c90a711d22a56356\"},{\"pid\":\"33\",\"code\":21,\"updatedat\":\"2018-12-30T19:00:10.416Z\",\"__v\":0,\"count\":32,\"id\":\"5c2915bafb19b9df6574c943\"},{\"pid\":\"33\",\"code\":263,\"updatedat\":\"2019-01-01T19:34:59.934Z\",\"__v\":0,\"count\":48,\"id\":\"5c2bc0e36992533e905b1f63\"},{\"pid\":\"33\",\"code\":204,\"updatedat\":\"2019-01-01T19:35:40.375Z\",\"__v\":0,\"count\":80,\"id\":\"5c2bc10c9ab435e9a1c10031\"},{\"pid\":\"33\",\"code\":4,\"updatedat\":\"2019-01-02T18:46:18.217Z\",\"__v\":0,\"count\":30,\"id\":\"5c2d06faadfffc58e7ca1f60\"},{\"pid\":\"33\",\"code\":102,\"updatedat\":\"2019-01-02T18:50:27.025Z\",\"__v\":0,\"count\":6,\"id\":\"5c2d07f39570e528c42faad4\"},{\"pid\":\"33\",\"code\":114,\"updatedat\":\"2019-01-02T18:54:47.250Z\",\"__v\":0,\"count\":13,\"id\":\"5c2d08f7771a4fd04ffa3df0\"},{\"pid\":\"33\",\"code\":142,\"updatedat\":\"2019-01-02T18:59:14.314Z\",\"__v\":0,\"count\":23,\"id\":\"5c2d0a02fb19b94892a7bde3\"},{\"pid\":\"33\",\"code\":248,\"updatedat\":\"2020-07-19T19:52:38.137Z\",\"__v\":0,\"count\":80,\"id\":\"5f14a4863392c13b48cb6ca6\"},{\"pid\":\"33\",\"code\":14,\"updatedat\":\"2020-07-20T11:48:36.805Z\",\"__v\":0,\"count\":60,\"id\":\"5f158494288bb3761f67c9c5\"},{\"pid\":\"33\",\"code\":207,\"updatedat\":\"2020-07-20T12:31:32.826Z\",\"__v\":0,\"count\":5,\"id\":\"5f158ea406728b43572e7d7f\"},{\"pid\":\"33\",\"code\":268,\"updatedat\":\"2020-07-20T17:07:45.752Z\",\"__v\":0,\"count\":5,\"id\":\"5f15cf61c5744e457e33d738\"},{\"pid\":\"33\",\"code\":134,\"updatedat\":\"2020-08-17T18:33:57.232Z\",\"__v\":0,\"count\":10,\"id\":\"5f3acd9579897d2dcb54201a\"},{\"pid\":\"33\",\"code\":153,\"updatedat\":\"2021-09-15T11:30:21.613Z\",\"__v\":0,\"count\":100,\"id\":\"6141d94de180e1290f4c8da3\"}]";
		
		//JsonArray rootArray = new JsonArray();
		//JsonObject rootObject = new JsonObject();
		//rootArray.add(rootObject);
		
		//rootObject.addProperty("pid", "33"); // player id
		//rootObject.addProperty("code", 181);
		//rootObject.addProperty("updatedat", "2017-11-12T19:06:16.803Z");
		//rootObject.addProperty("__v", 0);
		//rootObject.addProperty("count", 3);
		//rootObject.addProperty("id", "5a089ba896894b00066a024b");
		
		System.out.println("### [Inventory] Blueprints request from player ID " + forcePlayerId + ".");
	    return jsonStr;
	}
	
	/**
	 * Install performance part from inventory request
	 * @return Vehicle data
	 */
	@POST
	@Path("vehicles/{vehicleId}/parts/{partType}/@install")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiPartsInstall(String requestBody, @PathParam(value = "vehicleId") String vehicleId,
			@PathParam(value = "partType") String partType) {
		JsonObject requestJson = new Gson().fromJson(requestBody, JsonObject.class);
		String itemId = requestJson.get("partCode").getAsString();
		
		PlayerVehicleEntity vehicle = playerVehicleDB.getVehicleByVid(vehicleId);
		edgeInventoryBE.changePerformancePart(vehicle, partType, Integer.parseInt(itemId));
		playerVehicleDB.update(vehicle);
		
		String updatedCar = edgeVehiclesBE.prepareVehicleData(vehicle).toString();
		edgeSocketVehiclesBE.prepareAssetVehicleUpdate(forcePlayerId);
		// TODO Parts inventory status socket request

		System.out.println("### [Inventory] Install performance part ID " + itemId + " on Vehicle ID " 
				+ vehicleId + " request from player ID " + forcePlayerId + ".");
	    return updatedCar;
	}
	
	/**
	 * Upgrade installed performance part on car request
	 * @return Vehicle data
	 */
	@POST
	@Path("vehicles/{vehicleId}/parts/{partType}/@upgrade")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiPartsUpgrade(String requestBody, @PathParam(value = "vehicleId") String vehicleId,
			@PathParam(value = "partType") String partType) {
		// TODO Work with exceptions - wrong part level, un-existing perf part, detect car class
		//JsonObject requestJson = new Gson().fromJson(requestBody, JsonObject.class);
		//String itemId = requestJson.get("partCode").getAsString();
		
		PlayerVehicleEntity vehicle = playerVehicleDB.getVehicleByVid(vehicleId);
		AttrsPartsInfoEntity currentPart = null;
		switch(partType) {
		case "engine":
			currentPart = attrsPartsInfoDBLoader.getPartInfo(vehicle.getPartEngine()); break;
		case "transmission":
			currentPart = attrsPartsInfoDBLoader.getPartInfo(vehicle.getPartTransmission()); break;
		case "nitroTank":
			currentPart = attrsPartsInfoDBLoader.getPartInfo(vehicle.getPartNitroTank()); break;
		case "bumper":
			currentPart = attrsPartsInfoDBLoader.getPartInfo(vehicle.getPartBumper()); break;
		case "frame":
			currentPart = attrsPartsInfoDBLoader.getPartInfo(vehicle.getPartFrame()); break;
		}
		int newPart = attrsPartsInfoDBLoader.findPart(partType, "A", currentPart.getPartLevel() + 1).getPartId();
		edgeInventoryBE.changePerformancePart(vehicle, partType, newPart);
		playerVehicleDB.update(vehicle);
		
		JsonObject updatedCar = edgeVehiclesBE.prepareVehicleData(vehicle);
		JsonObject objJson = new JsonObject();
		objJson.add("vehicle", updatedCar);
		objJson.addProperty("isSuccess", true);
		edgeSocketVehiclesBE.prepareAssetVehicleUpdate(forcePlayerId);
		// TODO Parts inventory status socket request

		System.out.println("### [Inventory] Upgrade performance part type " + partType + " on Vehicle ID " 
				+ vehicleId + " request from player ID " + forcePlayerId + ".");
	    return objJson.toString();
	}
	
	/**
	 * Uninstall performance part from car request
	 * @return Vehicle data
	 */
	@POST
	@Path("vehicles/{vehicleId}/parts/{partType}/@uninstall")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiPartsUninstall(@PathParam(value = "vehicleId") String vehicleId,
			@PathParam(value = "partType") String partType) {
		PlayerVehicleEntity vehicle = playerVehicleDB.getVehicleByVid(vehicleId);
		edgeInventoryBE.changePerformancePart(vehicle, partType, 0);
		playerVehicleDB.update(vehicle);
		
		String updatedCar = edgeVehiclesBE.prepareVehicleData(vehicle).toString();
		edgeSocketVehiclesBE.prepareAssetVehicleUpdate(forcePlayerId);
		// TODO Parts inventory status socket request

		System.out.println("### [Inventory] Uninstall performance part " + partType + " from Vehicle ID " 
				+ vehicleId + " request from player ID " + forcePlayerId + ".");
	    return updatedCar;
	}
	
	/**
	 * Request to create new car from Blueprints. Identical to "/vehicles" request data
	 * @return Vehicle data
	 */
	@POST
	@Path("blueprints/{objectId}/@produce")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiBlueprintsProduce(@PathParam(value = "objectId") String objectId) {
		JsonObject carJson = new JsonObject();
		
		carJson.addProperty("embededId", "16660"); // on 1 more than Id value for some reason
		carJson.addProperty("pid", forcePlayerId);
		carJson.addProperty("code", 210);
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
		carJson.addProperty("id", "16660"); // vehicle id
		
		JsonObject carStatus = new JsonObject();
		carStatus.addProperty("topSpeed", 562);
		carStatus.addProperty("acceleration", 569);
		carStatus.addProperty("nitroCapacity", 559);
		carStatus.addProperty("strength", 409);
		carStatus.addProperty("durability", 397);
		carJson.add("status", carStatus);
		
		carJson.addProperty("ovr", 573);
		carJson.addProperty("clazz", "A");
		
		System.out.println("### [Inventory] Blueprints Produce (Vehicle ID " + "16660" + ") request from player ID " + forcePlayerId + ".");
	    return carJson.toString();
	}
	
	/**
	 * Use inventory + shop item request
	 * @return Actual inventory with recently used item
	 */
	@PUT
	@Path("tuning/inventories/{playerId}/items/{itemId}/@use")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiUseInventoryItem(@PathParam(value = "playerId") String playerId, @PathParam(value = "itemId") String itemId) {
		int itemCode = edgeInventoryBE.getPendingItemId();
		String json = "{\"inventory\":{\"__v\":8,\"checkedat\":\"2021-10-05T02:36:43.446Z\",\"__t\":\"615bbf33e180e15e2885d229\",\"items\":[],\"id\":\"33\"},\"item\":{\"code\":"+itemCode+",\"updatedat\":\"2021-10-05T02:57:55.263Z\",\"createdat\":\"2019-02-08T06:28:06.899Z\",\"addedat\":\"2020-07-20T17:03:02.362Z\",\"usedat\":\"2021-10-05T02:57:55.263Z\",\"count\":0,\"id\":\"112233\"}}";
		
		System.out.println("### [Inventory] Use inventory item request from player ID " + forcePlayerId + ".");
	    return json;
	}
	
	/**
	 * Use inbox item (can be direcly from Shop)
	 * @return Response
	 */
	@POST
	@Path("inboxes/{playerId}/packages/@use")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiInboxUse(@PathParam(value = "playerId") String playerId) {
		int itemId = edgeInventoryBE.getPendingItemId();
		
		JsonArray rootArray = new JsonArray();
		JsonObject mainObject = new JsonObject();
		rootArray.add(mainObject);
		mainObject.addProperty("pid", playerId);
		mainObject.addProperty("type", "PURCHASED");
		mainObject.addProperty("title", "test item");
		mainObject.addProperty("desc", "good item for tests");
		mainObject.addProperty("imgpath", "shop/product/5075.png");
		
		JsonObject optsObject = new JsonObject();
		mainObject.add("opts", optsObject);
		optsObject.addProperty("sellingShop", "TUNING_SHOP");
		JsonObject bunchObject = new JsonObject();
		mainObject.add("bunch", bunchObject);
		bunchObject.addProperty("count", 1);
		bunchObject.addProperty("currency", "SP");
		bunchObject.addProperty("price", 1000);
		bunchObject.addProperty("_id", "112233");
		optsObject.addProperty("orderNo", "a1f6bf3acd694d95bca84a39193262bc");
		
		mainObject.addProperty("createdat", "2021-10-05T03:00:54.533Z");
		mainObject.addProperty("__v", 0);
		
		JsonArray objectsArray = new JsonArray();
		mainObject.add("objects", objectsArray);
		JsonObject rewardObject = new JsonObject();
		objectsArray.add(rewardObject);
		rewardObject.addProperty("count", 1);
		rewardObject.addProperty("code", itemId);
		rewardObject.addProperty("type", "TUNING");
		rewardObject.addProperty("_id", "112234");
		
		mainObject.addProperty("id", "615bbfe64bc9a149bf38b8db");
		edgeInventoryBE.prepareInboxItemChangeSIO(playerId, itemId);
		edgeSocketVehiclesBE.prepareAssetVehicleUpdate(forcePlayerId);
		
		System.out.println("### [Inventory] Use inbox item request from player ID " + playerId + ".");
	    return rootArray.toString();
	}
	
	/**
	 * Remove inventory item (or item installed on car)
	 * @return Response
	 */
	@PUT
	@Path("tuning/inventories/{playerId}/item/{itemId}/remove")
	@Produces(MediaType.APPLICATION_JSON)
	public Response apiRemoveItem(String requestBody, @PathParam(value = "playerId") String playerId, 
			@PathParam(value = "itemId") String itemId) {
		JsonObject requestJson = new Gson().fromJson(requestBody, JsonObject.class);
		String vid = requestJson.get("vid").getAsString();
		
		PlayerVehicleEntity playerVehicleEntity = playerVehicleDB.getVehicleByVid(vid);
		CarCustomizationEntity customizationItem = carCustomizationDB.getItemProperties(Integer.parseInt(itemId));
		int colorCode = playerVehicleEntity.getColorCode();
		int wheelColor = playerVehicleEntity.getWheelColor();
		int wrapCode = playerVehicleEntity.getWrapCode();
		switch(customizationItem.getSubType()) { // What type if item we should remove?
		case "WHEELCOLOR":
			wheelColor = 20000; break;
		case "WRAP":
			wrapCode = 0; break;
		}
		playerVehicleDB.setCustomization(vid, colorCode, wheelColor, wrapCode);
		edgeSocketVehiclesBE.prepareAssetVehicleUpdate(playerId);
		
		System.out.println("### [Inventory] Remove customization item ID " + itemId + " request from player ID " + playerId + ".");
	    return Response.ok().build();
	}

}