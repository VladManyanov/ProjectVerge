package com.pverge.core.api.game;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.JsonArray;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.pverge.core.be.EdgeInventoryBE;
import com.pverge.core.be.EdgePlayersBE;
import com.pverge.core.be.EdgeVehiclesBE;
import com.pverge.core.db.CarCustomizationDBLoader;
import com.pverge.core.db.PlayerDBLoader;
import com.pverge.core.db.PlayerVehicleDBLoader;
import com.pverge.core.db.dbobjects.CarCustomizationEntity;
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
	private EdgeVehiclesBE edgeVehiclesBE;
	@EJB
	private PlayerDBLoader playerDB;
	@EJB
	private CarCustomizationDBLoader carCustomizationDB;
	@EJB
	private PlayerVehicleDBLoader playerVehicleDB;
	
	private static String forcePlayerId = "33";
	// TODO 
	
	/**
	 * Shop content by Selling Shop request
	 * @return Items array
	 */
	@GET
	@Path("shop/products")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiShopProductsTuning(@QueryParam("sellingShop") String sellingShop,
			@QueryParam("category") String category) {
		JsonArray rootArrayJson = new JsonArray();
		if (category != null) { // Another shop request
			System.out.println("### [Shop] Shop (category: " + category + ") request from player ID " + forcePlayerId + ".");
		    return rootArrayJson.toString();
		}
		List<CarCustomizationEntity> loadAllItems = carCustomizationDB.loadAllItems();
		
		int order = 0;
		for (CarCustomizationEntity item : loadAllItems) {
			JsonObject itemJson = new JsonObject();
			rootArrayJson.add(itemJson);
			
			itemJson.addProperty("code", item.getCode());
			itemJson.addProperty("category", "shop_category1"); // car body paint
			itemJson.addProperty("name", "Customization Item");
			itemJson.addProperty("desc", "Somebody once told me, that world is gonna roll me...");
			itemJson.addProperty("order", order);
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
			rewardJson.addProperty("code", item.getCode());
			rewardJson.addProperty("type", "TUNING");
			rewardJson.addProperty("_id", "112234");
			
			itemJson.addProperty("id", "098765");
			order++;
		}
		
		System.out.println("### [Shop] Shop (Selling Shop: " + sellingShop + ") request from player ID " + forcePlayerId + ".");
	    return rootArrayJson.toString();
	}
	
	/**
	 * Shop content ("best" category) request
	 * @return Items array
	 */
	@GET
	@Path("shop/bestproducts")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiShopBestProducts() {
		JsonArray rootArrayJson = new JsonArray();
		
		System.out.println("### [Shop] Shop best products request from player ID " + forcePlayerId + ".");
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
		CarCustomizationEntity customizationItem = carCustomizationDB.getItemProperties(itemCode);
		if (customizationItem == null) {
			return Response.serverError().build();
		}
		edgeInventoryBE.setPendingItemId(itemCode);
		edgePlayersBE.prepareCashValueUpdateSIO(forcePlayerId);
		edgeVehiclesBE.applyCustomizationItem(customizationItem, currentVehicle);
		edgeInventoryBE.prepareInboxCreatePackageSIO(forcePlayerId, itemCodeStr);
	
		System.out.println("### [Shop] Shop item (Code " + itemCodeStr + ") purchase request from player ID " + forcePlayerId + ".");
	    return Response.ok().build();
	}

}