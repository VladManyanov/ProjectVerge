package com.pverge.core.be;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

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
	
	public int getPendingItemId() {
		return pendingItemId;
	}
	
	public void setPendingItemId(int itemId) {
		pendingItemId = itemId;
	}
	
}