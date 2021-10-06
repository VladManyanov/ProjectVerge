package com.pverge.core.api.game;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.JsonArray;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.pverge.core.be.EdgeInventoryBE;
import com.pverge.core.be.EdgePlayersBE;
import com.pverge.core.db.CarColorsDBLoader;
import com.pverge.core.db.PlayerDBLoader;
import com.pverge.core.db.PlayerVehicleDBLoader;
import com.pverge.core.db.dbobjects.CarColorsEntity;
import com.pverge.core.db.dbobjects.PlayerVehicleEntity;

/**
 * Edge - Shop requests
 * @author Hypernucle
 */
@Path("/v2")
public class EdgeShop {
	
	@EJB
	private EdgeInventoryBE edgeInventoryBE;
	@EJB
	private EdgePlayersBE edgePlayersBE;
	@EJB
	private PlayerDBLoader playerDB;
	@EJB
	private CarColorsDBLoader carColorsDB;
	@EJB
	private PlayerVehicleDBLoader playerVehicleDB;
	
	private static String forcePlayerId = "33";
	// TODO 
	
	/**
	 * Shop content by category request
	 * @return Items array
	 */
	@GET
	@Path("shop/products")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiShopProducts(@QueryParam("sellingShop") String sellingShop) {
		// On this example, we will add green car paint item
		JsonArray rootArrayJson = new JsonArray();
		JsonObject itemJson = new JsonObject();
		rootArrayJson.add(itemJson);
		
		itemJson.addProperty("code", 5075); // item id
		itemJson.addProperty("category", "shop_category1"); // car body paint
		itemJson.addProperty("name", "Green!!!");
		itemJson.addProperty("desc", "Somebody once told me, that world is gonna roll me...");
		itemJson.addProperty("order", 21);
		itemJson.addProperty("isPromotion", false);
		itemJson.addProperty("isNewProduct", false);
		itemJson.addProperty("saleFrom", "2017-07-11T17:50:00.005Z");
		itemJson.addProperty("saleTo", "2022-07-30T16:15:00.008Z");
		itemJson.addProperty("viewFrom", "2017-07-11T17:50:00.005Z");
		itemJson.addProperty("viewTo", "2022-07-30T16:15:00.006Z");
		itemJson.addProperty("isShowDates", false);
		itemJson.addProperty("sellingShop", "TUNING_SHOP");
		itemJson.addProperty("__v", 0);
		itemJson.add("markType", JsonNull.INSTANCE);
		itemJson.addProperty("saleLimit", 0);
		
		JsonArray bunchesArrayJson = new JsonArray();
		itemJson.add("bunches", bunchesArrayJson);
		JsonObject bunchJson = new JsonObject();
		bunchesArrayJson.add(bunchJson);
		bunchJson.addProperty("count", 1);
		bunchJson.addProperty("currency", "SP");
		bunchJson.addProperty("price", 1000);
		bunchJson.addProperty("_id", "112233");
		
		JsonArray rewardsArrayJson = new JsonArray();
		itemJson.add("rewards", rewardsArrayJson);
		JsonObject rewardJson = new JsonObject();
		rewardsArrayJson.add(rewardJson);
		rewardJson.addProperty("count", 1);
		rewardJson.addProperty("code", 5075);
		rewardJson.addProperty("type", "TUNING");
		rewardJson.addProperty("_id", "112234");
		
		itemJson.addProperty("id", "098765");
		
		//
		
		JsonObject item2Json = new JsonObject();
		rootArrayJson.add(item2Json);
		
		item2Json.addProperty("code", 5085); // item id
		item2Json.addProperty("category", "shop_category1"); // car body paint
		item2Json.addProperty("name", "Green!!!");
		item2Json.addProperty("desc", "Somebody once told me, that world is gonna roll me...");
		item2Json.addProperty("order", 22);
		item2Json.addProperty("isPromotion", false);
		item2Json.addProperty("isNewProduct", false);
		item2Json.addProperty("saleFrom", "2017-07-11T17:50:00.005Z");
		item2Json.addProperty("saleTo", "2022-07-30T16:15:00.008Z");
		item2Json.addProperty("viewFrom", "2017-07-11T17:50:00.005Z");
		item2Json.addProperty("viewTo", "2022-07-30T16:15:00.006Z");
		item2Json.addProperty("isShowDates", false);
		item2Json.addProperty("sellingShop", "TUNING_SHOP");
		item2Json.addProperty("__v", 0);
		item2Json.add("markType", JsonNull.INSTANCE);
		item2Json.addProperty("saleLimit", 0);
		
		JsonArray bunches2ArrayJson = new JsonArray();
		item2Json.add("bunches", bunches2ArrayJson);
		JsonObject bunch2Json = new JsonObject();
		bunches2ArrayJson.add(bunch2Json);
		bunch2Json.addProperty("count", 1);
		bunch2Json.addProperty("currency", "SP");
		bunch2Json.addProperty("price", 1000);
		bunch2Json.addProperty("_id", "212233");
		
		JsonArray rewards2ArrayJson = new JsonArray();
		item2Json.add("rewards", rewards2ArrayJson);
		JsonObject reward2Json = new JsonObject();
		rewards2ArrayJson.add(reward2Json);
		reward2Json.addProperty("count", 1);
		reward2Json.addProperty("code", 5085);
		reward2Json.addProperty("type", "TUNING");
		reward2Json.addProperty("_id", "212234");
		
		item2Json.addProperty("id", "198765");
		
		System.out.println("### [Shop] Shop (category: " + sellingShop + ") request from player ID " + forcePlayerId + ".");
	    return rootArrayJson.toString();
	}
	
	/**
	 * Purchase item from shop request
	 * @return OK response
	 */
	@POST
	@Path("shop/products/{itemCode}/@purchase")
	@Produces(MediaType.APPLICATION_JSON)
	public Response apiShopPurchase(@PathParam("itemCode") String itemCodeStr) {
		PlayerVehicleEntity currentVehicle = playerVehicleDB.getVehicleByVid(playerDB.getPlayer(forcePlayerId).getVid());
		int itemCode = Integer.parseInt(itemCodeStr);
		String rgbSolid = "#ffffff";
		String rgbSecondary = "#ffffff";
		CarColorsEntity carColor = carColorsDB.getColor(itemCode);
		if (carColor != null) {
			rgbSolid = carColor.getRGBSolid();
			rgbSecondary = carColor.getRGBSecondary();
		}
		edgeInventoryBE.setPendingItemId(itemCode);
		edgePlayersBE.prepareCashValueUpdateSIO(forcePlayerId);
		playerVehicleDB.setVehicleColor(currentVehicle.getId(), itemCode, rgbSolid, rgbSecondary);
		edgeInventoryBE.prepareInboxCreatePackageSIO(forcePlayerId, itemCodeStr);
	
		System.out.println("### [Shop] Shop item (Code " + itemCodeStr + ") purchase request from player ID " + forcePlayerId + ".");
	    return Response.ok().build();
	}

}