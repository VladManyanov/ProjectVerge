package com.pverge.core.be;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.pverge.core.db.CarCustomizationDBLoader;
import com.pverge.core.db.dbobjects.CarCustomizationEntity;
import com.pverge.core.db.dbobjects.PlayerVehicleEntity;
import com.pverge.core.socket.dataobjects.SIOMatchObjects.NumberPlate;
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
	
}
