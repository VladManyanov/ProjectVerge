package com.pverge.core.be;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.pverge.core.db.CarCustomizationDBLoader;
import com.pverge.core.db.dbobjects.CarCustomizationEntity;
import com.pverge.core.db.dbobjects.PlayerVehicleEntity;
import com.pverge.core.socket.NettySocketIO;
import com.pverge.core.socket.dataobjects.SIODataObjects.MessageDataObject;
import com.pverge.core.socket.dataobjects.SIODataObjects.ResourceListDataObject;
import com.pverge.core.socket.dataobjects.SIOMatchObjects.NumberPlate;
import com.pverge.core.socket.dataobjects.SIOMatchObjects.RoomSettings;
import com.pverge.core.socket.dataobjects.SIORaceCommonObjects.*;

/**
 * Edge - Various methods to fill & prepare Match events data
 * @author Hypernucle
 */
@Stateless
public class EdgeMatchCreationBE {
	
	@EJB
	private EdgeVehicleAttributesBE vehicleAttributesBE;
	@EJB
	private CarCustomizationDBLoader carCustomizationDB;
	@EJB
	private EdgePresenceBE presenceBE;
	@EJB
	private EdgeTokensBE tokensBE;
	
	NettySocketIO socketIO = new NettySocketIO();
	
	/**
	 * Create player client & vehicle entry
	 * @return Player data
	 */
	public Clients createPlayerClient(String playerId, PlayerVehicleEntity currentVehicle, boolean isAI) {
		int colorCode = currentVehicle.getColorCode();
		int wheelId = currentVehicle.getWheelColor();
		int wrapId = currentVehicle.getWrapCode();
		if (isAI) {
			colorCode = 1;
			wheelId = 20000;
			wrapId = 0;
		}
		else { // Don't fetch default settings
			if (wheelId != 20000 && wheelId != -1) {
				wheelId = carCustomizationDB.getItemProperties(wheelId).getCID();
			}
			if (wrapId != 0 && wrapId != -1) {
				wrapId = carCustomizationDB.getItemProperties(wrapId).getCID();
			}
		}
		CarCustomizationEntity carColor = carCustomizationDB.getItemProperties(colorCode);
		
		Clients client = new Clients();
		client.setPlayerId(playerId);
		client.setAccountId(playerId);
		client.setVCode(currentVehicle.getVcode());
		client.setAI(isAI);
		client.setTeam(0);
		client.setPositionOnStartingGrid(0);
		client.setVid(currentVehicle.getId());
		client.setAttrs(vehicleAttributesBE.getCarAttrs(currentVehicle));
		client.setSteeringAttrs(vehicleAttributesBE.getSteeringAttrs(currentVehicle));
		
		AppearanceInfo appearanceInfo = new AppearanceInfo();
		ColorData colorData = new ColorData();
		Rgb rgb = new Rgb();
		rgb.setSolid(carColor.getRGBSolid());
		rgb.setSecondary(carColor.getRGBSecondary());
		colorData.setRgb(rgb);
		appearanceInfo.setColorData(colorData);
		appearanceInfo.setWheelId(wheelId);
		appearanceInfo.setWrapId(wrapId);
		
		appearanceInfo.setPlate(getDefaultPlate(playerId));
		client.setAppearanceInfo(appearanceInfo);
		return client;
	}
	
	/**
	 * Create generic number plate
	 * @return Player data
	 */
	public NumberPlate getDefaultPlate(String playerId) {
		NumberPlate plate = new NumberPlate();
		plate.setPid(playerId);
		plate.setPrefix("PJ");
		plate.setPlateNumber("VERGE");
		plate.setTemplateCode(0);
		plate.setBackground(1);
		plate.setFontColor("#ffffff");
		
		return plate;
	}
	
	/**
	 * Send current Room state data request (SIO)
	 */
	public void updateRoomStateSIO(boolean isRandomTrack, String playerId) {
		String[] roomInfo = presenceBE.getRoomInfo();
		ResourceListDataObject rootObj = new ResourceListDataObject();
		rootObj.setCmd("resources");
		
		MessageDataObject optsObj = new MessageDataObject();
		optsObj.setUri("/v2/room2s/1"); // 1 is Match ID
		RoomSettings roomSettings = new RoomSettings();
		optsObj.setBody(roomSettings);
		
		roomSettings.setGameMode(roomInfo[0]);
		roomSettings.setMaxVehicleClazz("ALL");
		roomSettings.setRandomTrack(isRandomTrack);
		roomSettings.setTrackCode(Integer.parseInt(roomInfo[1]));
		List<Integer> lockedSlots = new ArrayList<>();
		lockedSlots.add(3); lockedSlots.add(4);
		lockedSlots.add(5); lockedSlots.add(6); lockedSlots.add(7);
		lockedSlots.add(8);
		roomSettings.setLocked(lockedSlots);
		
		List<Object> optsList = new ArrayList<>();
		optsList.add(optsObj);
		rootObj.setOpts(optsList);
		socketIO.sendEvent("msg", rootObj, rootObj.getCmd(), tokensBE.getSessionUUID(playerId));
	}
	
}
