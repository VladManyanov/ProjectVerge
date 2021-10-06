package com.pverge.core.be;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.pverge.core.db.PlayerDBLoader;
import com.pverge.core.db.PlayerVehicleDBLoader;
import com.pverge.core.db.dbobjects.PlayerVehicleEntity;
import com.pverge.core.socket.NettySocketIO;
import com.pverge.core.socket.dataobjects.SIODataObjects.*;
import com.pverge.core.socket.dataobjects.SIOAssetVehicleObjects.*;

/**
 * Prepare and send vehicle events with Socket (Vehicle-related)
 * @author Hypernucle
 */
@Stateless
public class EdgeSocketVehiclesBE {

	@EJB
	private PlayerDBLoader playerDB;
	@EJB
	private PlayerVehicleDBLoader playerVehicleDB;
	
	private static String forcePlayerId = "33";
	NettySocketIO socketIO = new NettySocketIO();
	
	/**
	 * Send vehicle asset update response
	 */
	public void prepareAssetVehicleUpdate(String playerId) {
		PlayerVehicleEntity currentVehicle = playerVehicleDB.getVehicleByVid(playerDB.getPlayer(playerId).getVid());
		
		ResourceListDataObject rootData = new ResourceListDataObject();
		List<Object> optsList = new ArrayList<>();
		rootData.setCmd("resources");
		rootData.setOpts(optsList);
		
		MessageDataObject assetOpts = new MessageDataObject();
		assetOpts.setUri("/asset/vehicles/update");
		
		AssetBody assetBody = new AssetBody();
		assetBody.setEmbededId("5f2876e606728b1823052658");
		assetBody.setPid(playerId);
		assetBody.setCode(currentVehicle.getVcode());
		assetBody.setCreatedat("2020-08-03T20:43:18.241Z");
		assetBody.setCheckedat("2020-08-03T20:43:18.241Z");
		assetBody.setUsage(0);
		assetBody.setV(0);
		
		List<Steering> steeringAraay = new ArrayList<>();
		assetBody.setSteering(steeringAraay);
		
		assetBody.setDepotStatus(1);
		
		Paint assetPaint = new Paint();
		assetPaint.setColorCode(currentVehicle.getColorCode());
		assetPaint.setWheelCode(currentVehicle.getWheelColor());
		assetPaint.setWrapCode(currentVehicle.getWrapCode());
		assetBody.setPaint(assetPaint);
		
		Parts assetParts = new Parts();
		assetParts.setBumper(0);
		assetParts.setEngine(0);
		assetParts.setFrame(0);
		assetParts.setNitroTank(0);
		assetParts.setTransmission(0);
		assetBody.setParts(assetParts);
		
		assetBody.setFavorite(false);
		assetBody.setGrade(3);
		assetBody.setId(String.valueOf(currentVehicle.getId()));
		
		Status assetStatus = new Status();
		assetStatus.setTopSpeed(700);
		assetStatus.setAcceleration(723);
		assetStatus.setNitroCapacity(738);
		assetStatus.setStrength(470);
		assetStatus.setDurability(410);
		assetBody.setStatus(assetStatus);
		
		assetBody.setOvr(720);
		assetBody.setClazz("S");
		assetOpts.setBody(assetBody);
		optsList.add(assetOpts);
		
		socketIO.sendEvent("msg", rootData, rootData.getCmd());
	}
	
}