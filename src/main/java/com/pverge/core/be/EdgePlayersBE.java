package com.pverge.core.be;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pverge.core.db.PlayerDBLoader;
import com.pverge.core.db.dbobjects.PlayerEntity;

/**
 * Edge - Player data requests
 * @author Hypernucle
 */
@Stateless
public class EdgePlayersBE {

	@EJB
	private PlayerDBLoader playerDB;
	
	private static String forcePlayerId = "33";
	
	/**
	 * Common player data, same on several requests
	 * @return Player data
	 */
	public String getPlayerInfoCommon(boolean isArray) {
		PlayerEntity playerEntity = playerDB.getPlayer(forcePlayerId);
		
		JsonArray rootArrayJson = new JsonArray();
		JsonObject playerJson = new JsonObject();
		rootArrayJson.add(playerJson);
		
		playerJson.addProperty("name", "ProjectVerge");
		playerJson.addProperty("lowerCaseName", "projectverge");
		playerJson.addProperty("__v", 0);
		playerJson.addProperty("isManager", false);
		
		JsonObject unlockJson = new JsonObject();
		unlockJson.addProperty("maxMarketRegisterCount", 50);
		unlockJson.addProperty("maxGarageCount", 250);
		playerJson.add("unlock", unlockJson);
		
		playerJson.addProperty("latestmatchid", 51527777);
		playerJson.addProperty("createdat", "2017-11-05T22:33:56.802Z");
		
		JsonObject newlyGainedJson = new JsonObject();
		newlyGainedJson.addProperty("inbox", "2021-09-18T16:38:18.168Z");
		newlyGainedJson.addProperty("inventory", "2021-09-18T16:50:11.277Z");
		playerJson.add("newlyGainedAt", newlyGainedJson);
		
		JsonObject checkedAtJson = new JsonObject();
		checkedAtJson.addProperty("inbox", "2021-09-18T16:50:13.662Z");
		checkedAtJson.addProperty("inventory", "2021-09-18T16:52:31.661Z");
		checkedAtJson.addProperty("part", "2021-09-14T17:50:03.415Z");
		checkedAtJson.addProperty("vehicle", "2021-09-18T17:00:35.921Z");
		playerJson.add("checkedat", checkedAtJson);
		
		JsonObject recentJson = new JsonObject();
		recentJson.addProperty("vid", String.valueOf(playerEntity.getVid())); // player recent vehicle ID
		playerJson.add("recent", recentJson);
		
		playerJson.addProperty("challengepoint", 1400);
		playerJson.addProperty("exp", 2917691);
		playerJson.addProperty("level", 63);
		playerJson.addProperty("sp", 999999);
		playerJson.addProperty("id", forcePlayerId);
		
		JsonObject mileageJson = new JsonObject();
		mileageJson.addProperty("balance", 0); 
		mileageJson.addProperty("nextResetAt", "2021-09-30T16:00:00.000Z"); 
		playerJson.add("mileage", mileageJson);
    	
		if (isArray) { // "players" and "checkedat" have different JSON output
			return rootArrayJson.toString();}
		else {
			return playerJson.toString();}
	}
}
