package com.pverge.core.be;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pverge.core.db.PlayerDBLoader;
import com.pverge.core.db.PlayerVehicleDBLoader;
import com.pverge.core.socket.NettySocketIO;
import com.pverge.core.socket.dataobjects.SIODataObjects.*;
import com.pverge.core.socket.dataobjects.SIOInboxObjects.*;

/**
 * Inventory actions
 * @author Hypernucle
 */
@Stateless
public class EdgeInventoryBE {

	@EJB
	private PlayerDBLoader playerDB;
	@EJB
	private PlayerVehicleDBLoader playerVehicleDB;
	
	NettySocketIO socketIO = new NettySocketIO();
	private static String forcePlayerId = "33";
	private static int pendingItemId = 0;
	// TODO 
	
	/**
	 * Send vehicle asset update response
	 */
	public void prepareInboxCreatePackageSIO(String playerId, String itemId) {
		ResourceListDataObject rootData = new ResourceListDataObject();
		List<Object> optsList = new ArrayList<>();
		rootData.setCmd("resources");
		rootData.setOpts(optsList);
		
		MessageDataObject inboxOpts = new MessageDataObject();
		inboxOpts.setUri("/inboxes/" + playerId + "/packages/" + itemId + "/create");
		
		InboxItemBody inboxBody = new InboxItemBody();
		inboxBody.setPid(playerId);
		inboxBody.setType("PURCHASED");
		inboxBody.setTitle("test item");
		inboxBody.setDesc("good item for tests");
		inboxBody.setImgpath("shop/product/" + itemId + ".png");
		
		Opts opts = new Opts();
		opts.setOrderNo("a1f6bf3acd694d95bca84a39193262bc");
		opts.setSellingShop("TUNING_SHOP");
		
		Bunch bunch = new Bunch();
		bunch.setCount(1);
		bunch.setCurrency("SP");
		bunch.setId("112233");
		bunch.setPrice(1000);
		opts.setBunch(bunch);
		inboxBody.setOpts(opts);
		
		inboxBody.setCreatedat("2021-10-05T03:00:54.533Z");
		inboxBody.setCount(1);
		
		List<Rewards> rewards = new ArrayList<>();
		Rewards reward = new Rewards();
		reward.setCode(Integer.parseInt(itemId));
		reward.setCount(1);
		reward.setId("112234");
		reward.setType("TUNING");
		rewards.add(reward);
		inboxBody.setRewards(rewards);
		
		inboxBody.setId("112234");
		
		inboxOpts.setBody(inboxBody);
		optsList.add(inboxOpts);
		
		socketIO.sendEvent("msg", rootData, rootData.getCmd());
		System.out.println("### [Socket] Inbox package information request from player ID " + forcePlayerId + ".");
	}
	
	/**
	 * Asset item change response
	 */
	public void prepareInboxItemChangeSIO(String playerId, int itemId) {
		ResourceListDataObject rootData = new ResourceListDataObject();
		List<Object> optsList = new ArrayList<>();
		rootData.setCmd("resources");
		rootData.setOpts(optsList);
		
		MessageDataObject itemOpts = new MessageDataObject();
		itemOpts.setUri("/asset/tuning/inventory/item/change/id/" + playerId);
		
		ItemChangeBody itemBody = new ItemChangeBody();
		itemBody.setCode(itemId);
		itemBody.setUpdatedat("2021-10-05T03:00:54.732Z");
		itemBody.setCreatedat("2021-10-05T03:00:54.732Z");
		itemBody.setAddedat("2021-10-05T03:00:54.732Z");
		itemBody.setCount(1);
		itemBody.setId("112233");
		
		itemOpts.setBody(itemBody);
		optsList.add(itemOpts);
		
		socketIO.sendEvent("msg", rootData, rootData.getCmd());
		System.out.println("### [Socket] Asset item change request from player ID " + forcePlayerId + ".");
	}
	
	/**
	 * Prepare the fake set of performance parts. They will be not removed when used
	 */
	public String fetchFakePerformanceInventory() {
		JsonArray rootArrayJson = new JsonArray();
		
		JsonObject partJson1 = new JsonObject();
		partJson1.addProperty("pid", forcePlayerId);
		partJson1.addProperty("code", "100210");
		partJson1.addProperty("updatedAt", "2021-08-28T19:16:28.676Z");
		partJson1.addProperty("increasedAt", "2021-08-28T19:16:28.676Z");
		partJson1.addProperty("__v", 0);
		partJson1.addProperty("count", 21);
		partJson1.addProperty("id", "110");
		rootArrayJson.add(partJson1);
		
		JsonObject partJson2 = new JsonObject();
		partJson2.addProperty("pid", forcePlayerId);
		partJson2.addProperty("code", "100220");
		partJson2.addProperty("updatedAt", "2021-08-28T19:16:28.676Z");
		partJson2.addProperty("increasedAt", "2021-08-28T19:16:28.676Z");
		partJson2.addProperty("__v", 0);
		partJson2.addProperty("count", 21);
		partJson2.addProperty("id", "120");
		rootArrayJson.add(partJson2);
		
		JsonObject partJson3 = new JsonObject();
		partJson3.addProperty("pid", forcePlayerId);
		partJson3.addProperty("code", "100230");
		partJson3.addProperty("updatedAt", "2021-08-28T19:16:28.676Z");
		partJson3.addProperty("increasedAt", "2021-08-28T19:16:28.676Z");
		partJson3.addProperty("__v", 0);
		partJson3.addProperty("count", 21);
		partJson3.addProperty("id", "130");
		rootArrayJson.add(partJson3);
		
		JsonObject partJson4 = new JsonObject();
		partJson4.addProperty("pid", forcePlayerId);
		partJson4.addProperty("code", "100240");
		partJson4.addProperty("updatedAt", "2021-08-28T19:16:28.676Z");
		partJson4.addProperty("increasedAt", "2021-08-28T19:16:28.676Z");
		partJson4.addProperty("__v", 0);
		partJson4.addProperty("count", 21);
		partJson4.addProperty("id", "140");
		rootArrayJson.add(partJson4);
		
		JsonObject partJson5 = new JsonObject();
		partJson5.addProperty("pid", forcePlayerId);
		partJson5.addProperty("code", "100250");
		partJson5.addProperty("updatedAt", "2021-08-28T19:16:28.676Z");
		partJson5.addProperty("increasedAt", "2021-08-28T19:16:28.676Z");
		partJson5.addProperty("__v", 0);
		partJson5.addProperty("count", 21);
		partJson5.addProperty("id", "150");
		rootArrayJson.add(partJson5);
		
		JsonObject partJson6 = new JsonObject();
		partJson6.addProperty("pid", forcePlayerId);
		partJson6.addProperty("code", "100260");
		partJson6.addProperty("updatedAt", "2021-08-28T19:16:28.676Z");
		partJson6.addProperty("increasedAt", "2021-08-28T19:16:28.676Z");
		partJson6.addProperty("__v", 0);
		partJson6.addProperty("count", 21);
		partJson6.addProperty("id", "160");
		rootArrayJson.add(partJson6);
		
		JsonObject partJson7 = new JsonObject();
		partJson7.addProperty("pid", forcePlayerId);
		partJson7.addProperty("code", "100270");
		partJson7.addProperty("updatedAt", "2021-08-28T19:16:28.676Z");
		partJson7.addProperty("increasedAt", "2021-08-28T19:16:28.676Z");
		partJson7.addProperty("__v", 0);
		partJson7.addProperty("count", 21);
		partJson7.addProperty("id", "170");
		rootArrayJson.add(partJson7);
		
		JsonObject partJson8 = new JsonObject();
		partJson8.addProperty("pid", forcePlayerId);
		partJson8.addProperty("code", "100280");
		partJson8.addProperty("updatedAt", "2021-08-28T19:16:28.676Z");
		partJson8.addProperty("increasedAt", "2021-08-28T19:16:28.676Z");
		partJson8.addProperty("__v", 0);
		partJson8.addProperty("count", 21);
		partJson8.addProperty("id", "180");
		rootArrayJson.add(partJson8);
		
		JsonObject partJson9 = new JsonObject();
		partJson9.addProperty("pid", forcePlayerId);
		partJson9.addProperty("code", "100290");
		partJson9.addProperty("updatedAt", "2021-08-28T19:16:28.676Z");
		partJson9.addProperty("increasedAt", "2021-08-28T19:16:28.676Z");
		partJson9.addProperty("__v", 0);
		partJson9.addProperty("count", 21);
		partJson9.addProperty("id", "190");
		rootArrayJson.add(partJson9);
		
		JsonObject partJson10 = new JsonObject();
		partJson10.addProperty("pid", forcePlayerId);
		partJson10.addProperty("code", "100300");
		partJson10.addProperty("updatedAt", "2021-08-28T19:16:28.676Z");
		partJson10.addProperty("increasedAt", "2021-08-28T19:16:28.676Z");
		partJson10.addProperty("__v", 0);
		partJson10.addProperty("count", 21);
		partJson10.addProperty("id", "200");
		rootArrayJson.add(partJson10);
		
		JsonObject dismJson = new JsonObject();
		dismJson.addProperty("pid", forcePlayerId);
		dismJson.addProperty("code", "90001");
		dismJson.addProperty("updatedAt", "2021-08-28T19:16:28.676Z");
		dismJson.addProperty("increasedAt", "2021-08-28T19:16:28.676Z");
		dismJson.addProperty("__v", 0);
		dismJson.addProperty("count", 10000);
		dismJson.addProperty("id", "300");
		rootArrayJson.add(dismJson);
		
		return rootArrayJson.toString();
	}
	
	public int getPendingItemId() {
		return pendingItemId;
	}
	
	public void setPendingItemId(int itemId) {
		pendingItemId = itemId;
	}
	
}