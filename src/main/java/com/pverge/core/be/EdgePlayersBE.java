package com.pverge.core.be;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pverge.core.db.PlayerDBLoader;
import com.pverge.core.db.PlayerSettingsDBLoader;
import com.pverge.core.db.dbobjects.PlayerEntity;
import com.pverge.core.db.dbobjects.PlayerSettingsEntity;
import com.pverge.core.db.dbobjects.PlayerVehicleEntity;
import com.pverge.core.db.dbobjects.VehicleSteeringEntity;
import com.pverge.core.socket.NettySocketIO;
import com.pverge.core.socket.dataobjects.SIODataObjects.MessageDataIntObject;
import com.pverge.core.socket.dataobjects.SIODataObjects.MessageDataObject;
import com.pverge.core.socket.dataobjects.SIODataObjects.ResourceListDataObject;
import com.pverge.core.socket.dataobjects.SIOInboxObjects.Bunch;
import com.pverge.core.socket.dataobjects.SIOInboxObjects.InboxItemBody;
import com.pverge.core.socket.dataobjects.SIOInboxObjects.Opts;
import com.pverge.core.socket.dataobjects.SIOInboxObjects.Rewards;

/**
 * Edge - Player data requests
 * @author Hypernucle
 */
@Stateless
public class EdgePlayersBE {

	@EJB
	private PlayerDBLoader playerDB;
	@EJB
	private EdgeChatEventsBE chatEventsBE;
	@EJB
	private EdgePresenceBE presenceBE;
	@EJB
	private PlayerSettingsDBLoader playerSettingsDB;
	
	NettySocketIO socketIO = new NettySocketIO();
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
		recentJson.addProperty("vid", playerEntity.getVid()); // player recent vehicle ID
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
		
		if (presenceBE.getPlayerState().contentEquals("idle")) {
			chatEventsBE.owPlayerSnippetUpdateSIO(forcePlayerId);
		}
		if (isArray) { // "players" and "checkedat" have different JSON output
			return rootArrayJson.toString();}
		else {
			return playerJson.toString();}
	}
	
	/**
	 * Player config data (Inputs and game settings)
	 * @return Settings
	 */
	public String preparePlayerSettings(String playerId) {
		PlayerSettingsEntity settings = playerSettingsDB.findByPid(playerId);
		if (settings == null) { // Create default settings entry
			settings = createDefaultSettings(playerDB.getPlayer(playerId));
		}
		
		JsonObject rootJson = new JsonObject();
		rootJson.addProperty("version", 2);
		JsonObject inputJson = new JsonObject();
		rootJson.add("inputKey", inputJson);
		
		JsonArray toggleWorldmap = new JsonArray();
		inputJson.add("toggleWorldmap", toggleWorldmap);
		toggleWorldmap.add(settings.getToggleWorldmap()[0]);
		toggleWorldmap.add(settings.getToggleWorldmap()[1]);
		//
		JsonArray useItem2 = new JsonArray();
		inputJson.add("useItem2", useItem2);
		useItem2.add(settings.getUseItem2()[0]);
		useItem2.add(settings.getUseItem2()[1]);
		//
		JsonArray secondBrake = new JsonArray();
		inputJson.add("secondBrake", secondBrake);
		secondBrake.add(settings.getSecondBrake()[0]);
		secondBrake.add(settings.getSecondBrake()[1]);
		//
		JsonArray throttle = new JsonArray();
		inputJson.add("throttle", throttle);
		throttle.add(settings.getThrottle()[0]);
		throttle.add(settings.getThrottle()[1]);
		//
		JsonArray brake = new JsonArray();
		inputJson.add("brake", brake);
		brake.add(settings.getBrake()[0]);
		brake.add(settings.getBrake()[1]);
		//
		JsonArray steeringLeft = new JsonArray();
		inputJson.add("steeringLeft", steeringLeft);
		steeringLeft.add(settings.getSteeringLeft()[0]);
		steeringLeft.add(settings.getSteeringLeft()[1]);
		//
		JsonArray steeringRight = new JsonArray();
		inputJson.add("steeringRight", steeringRight);
		steeringRight.add(settings.getSteeringRight()[0]);
		steeringRight.add(settings.getSteeringRight()[1]);
		//
		JsonArray nitro = new JsonArray();
		inputJson.add("nitro", nitro);
		nitro.add(settings.getNitro()[0]);
		nitro.add(settings.getNitro()[1]);
		//
		JsonArray handBrake = new JsonArray();
		inputJson.add("handBrake", handBrake);
		handBrake.add(settings.getHandBrake()[0]);
		handBrake.add(settings.getHandBrake()[1]);
		//
		JsonArray useItem = new JsonArray();
		inputJson.add("useItem", useItem);
		useItem.add(settings.getUseItem()[0]);
		useItem.add(settings.getUseItem()[1]);
		//
		JsonArray reset = new JsonArray();
		inputJson.add("reset", reset);
		reset.add(settings.getReset()[0]);
		reset.add(settings.getReset()[1]);
		//
		JsonArray rearView = new JsonArray();
		inputJson.add("rearView", rearView);
		rearView.add(settings.getRearView()[0]);
		rearView.add(settings.getRearView()[1]);
		//
		JsonArray leftView = new JsonArray();
		inputJson.add("leftView", leftView);
		leftView.add(settings.getLeftView()[0]);
		leftView.add(settings.getLeftView()[1]);
		//
		JsonArray rightView = new JsonArray();
		inputJson.add("rightView", rightView);
		rightView.add(settings.getRightView()[0]);
		rightView.add(settings.getRightView()[1]);
		//
		JsonArray horn = new JsonArray();
		inputJson.add("horn", horn);
		horn.add(settings.getHorn()[0]);
		horn.add(settings.getHorn()[1]);
		//
		JsonArray toggleCamera = new JsonArray();
		inputJson.add("toggleCamera", toggleCamera);
		toggleCamera.add(settings.getToggleCamera()[0]);
		toggleCamera.add(settings.getToggleCamera()[1]);
		//
		JsonArray toggleMinimap = new JsonArray();
		inputJson.add("toggleMinimap", toggleMinimap);
		toggleMinimap.add(settings.getToggleMinimap()[0]);
		toggleMinimap.add(settings.getToggleMinimap()[1]);
		
		JsonObject gameSettingJson = new JsonObject();
		rootJson.add("gameSetting", gameSettingJson);
		
		gameSettingJson.addProperty("minimapPosition", settings.getMinimapPosition());
		gameSettingJson.addProperty("roomMirrorOff", settings.isRoomMirrorOff());
		gameSettingJson.addProperty("useHcs", settings.isUseHcs());
		gameSettingJson.addProperty("useEsc", settings.isUseEsc());
		gameSettingJson.addProperty("useAbs", settings.isUseAbs());
		gameSettingJson.addProperty("actionFeedbackOn", settings.isActionFeedbackOn());
		gameSettingJson.addProperty("keyGuideOn", settings.isKeyGuideOn());
		gameSettingJson.addProperty("vehicleCameraMode", settings.getVehicleCameraMode());
		gameSettingJson.addProperty("chatOn", settings.isChatOn());
		gameSettingJson.addProperty("flevron", settings.isFlevron());
		
		rootJson.addProperty("__v", 0);
		rootJson.addProperty("id", playerId);
		return rootJson.toString();
	}
	
	/**
	 * Send player cash value update
	 */
	public void prepareCashValueUpdateSIO(String playerId) {
		ResourceListDataObject rootData = new ResourceListDataObject();
		List<Object> optsList = new ArrayList<>();
		rootData.setCmd("resources");
		rootData.setOpts(optsList);
		
		MessageDataIntObject cashOpts = new MessageDataIntObject();
		cashOpts.setUri("/players/" + playerId + "/sp");
		cashOpts.setBody(999998);
		optsList.add(cashOpts);
		
		socketIO.sendEvent("msg", rootData, rootData.getCmd());
		System.out.println("### [Socket] Cash value update request from player ID " + playerId + ".");
	}
	
	/**
	 * Save default player settings entry in DB
	 */
	private PlayerSettingsEntity createDefaultSettings(PlayerEntity player) {
		PlayerSettingsEntity defaultSettings = new PlayerSettingsEntity();
		defaultSettings.setPid(player.getPid());
		
		defaultSettings.setToggleWorldmap("77,23");
		defaultSettings.setUseItem2("18,15");
		defaultSettings.setSecondBrake("0,20");
		defaultSettings.setThrottle("38,21");
		defaultSettings.setBrake("40,20");
		defaultSettings.setSteeringLeft("37,0");
		defaultSettings.setSteeringRight("39,0");
		defaultSettings.setNitro("78,12");
		defaultSettings.setHandBrake("32,14");
		defaultSettings.setUseItem("17,13");
		defaultSettings.setReset("82,19");
		defaultSettings.setRearView("87,0");
		defaultSettings.setLeftView("68,0");
		defaultSettings.setRightView("65,0");
		defaultSettings.setHorn("72,18");
		defaultSettings.setToggleCamera("67,9");
		defaultSettings.setToggleMinimap("88,23");
		//
		defaultSettings.setMinimapPosition(0);
		defaultSettings.setRoomMirrorOff(false);
		defaultSettings.setUseHcs(false);
		defaultSettings.setUseEsc(false);
		defaultSettings.setUseAbs(false);
		defaultSettings.setActionFeedbackOn(true);
		defaultSettings.setKeyGuideOn(true);
		defaultSettings.setVehicleCameraMode(0);
		defaultSettings.setChatOn(true);
		defaultSettings.setFlevron(true);
		
		playerSettingsDB.insert(defaultSettings);
		player.setPlayerSettings(defaultSettings);
		playerDB.update(player);
		
		System.out.println("### [Players] Default settings request from player ID " + player.getPid() + ".");
		return defaultSettings;
	}
}
