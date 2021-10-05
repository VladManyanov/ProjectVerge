package com.pverge.core.api.game;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonArray;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

/**
 * Edge - Shop requests
 * @author Hypernucle
 */
@Path("/v2")
public class EdgeShop {
	
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
		itemJson.addProperty("desc", "Somebody ones told me, that world is gonna roll me...");
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
		bunchJson.addProperty("price", 1000000);
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
		
		System.out.println("### [Shop] Shop (category: " + sellingShop + ") request from player ID " + forcePlayerId + ".");
	    return rootArrayJson.toString();
	}

}