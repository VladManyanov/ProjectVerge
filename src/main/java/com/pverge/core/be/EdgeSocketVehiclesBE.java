package com.pverge.core.be;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pverge.core.db.PlayerDBLoader;
import com.pverge.core.db.PlayerVehicleDBLoader;
import com.pverge.core.db.dbobjects.PlayerVehicleEntity;
import com.pverge.core.db.dbobjects.VehicleSteeringEntity;
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
	public void prepareAssetVehicleUpdate(String playerId, String uri) {
		PlayerVehicleEntity currentVehicle = playerVehicleDB.getVehicleByVid(playerDB.getPlayer(playerId).getVid());
		
		ResourceListDataObject rootData = new ResourceListDataObject();
		List<Object> optsList = new ArrayList<>();
		rootData.setCmd("resources");
		rootData.setOpts(optsList);
		
		MessageDataObject assetOpts = new MessageDataObject();
		assetOpts.setUri(uri);
		
		AssetBody assetBody = new AssetBody();
		assetBody.setEmbededId("5f2876e606728b1823052658");
		assetBody.setPid(playerId);
		assetBody.setCode(currentVehicle.getVcode());
		assetBody.setCreatedat("2020-08-03T20:43:18.241Z");
		assetBody.setCheckedat("2020-08-03T20:43:18.241Z");
		assetBody.setUsage(0);
		assetBody.setV(0);
		
		assetBody.setSteering(prepareSteeringSIO(currentVehicle.getSteering()));
		assetBody.setDepotStatus(1);
		
		Paint assetPaint = new Paint();
		assetPaint.setColorCode(currentVehicle.getColorCode());
		assetPaint.setWheelCode(currentVehicle.getWheelColor());
		assetPaint.setWrapCode(currentVehicle.getWrapCode());
		assetBody.setPaint(assetPaint);
		
		Parts assetParts = new Parts();
		assetParts.setBumper(currentVehicle.getPartBumper());
		assetParts.setEngine(currentVehicle.getPartEngine());
		assetParts.setFrame(currentVehicle.getPartFrame());
		assetParts.setNitroTank(currentVehicle.getPartNitroTank());
		assetParts.setTransmission(currentVehicle.getPartTransmission());
		assetBody.setParts(assetParts);
		
		assetBody.setFavorite(false);
		assetBody.setGrade(currentVehicle.getGrade());
		assetBody.setId(currentVehicle.getId());
		
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
	
	/**
	 * Prepare vehicle steering settings (for SocketIO)
	 */
	public List<Steering> prepareSteeringSIO(VehicleSteeringEntity steering) {
		List<Steering> steeringOpts = new ArrayList<>();
		if (steering == null) { // Default steering settings entry
			for (int i = 0; i < 14; i++) { // Must be 14 values
				Steering valObj = new Steering();
				valObj.setCode(i);
				valObj.setVal(50);
				steeringOpts.add(valObj);
			}
		} else {
			Steering val0Obj = new Steering();
			val0Obj.setCode(0);
			val0Obj.setVal(steering.getV0());
			steeringOpts.add(val0Obj);
			//
			Steering val1Obj = new Steering();
			val1Obj.setCode(1);
			val1Obj.setVal(steering.getV1());
			steeringOpts.add(val1Obj);
			//
			Steering val2Obj = new Steering();
			val2Obj.setCode(2);
			val2Obj.setVal(steering.getV2());
			steeringOpts.add(val2Obj);
			//
			Steering val3Obj = new Steering();
			val3Obj.setCode(3);
			val3Obj.setVal(steering.getV3());
			steeringOpts.add(val3Obj);
			//
			Steering val4Obj = new Steering();
			val4Obj.setCode(4);
			val4Obj.setVal(steering.getV4());
			steeringOpts.add(val4Obj);
			//
			Steering val5Obj = new Steering();
			val5Obj.setCode(5);
			val5Obj.setVal(steering.getV5());
			steeringOpts.add(val5Obj);
			//
			Steering val6Obj = new Steering();
			val6Obj.setCode(6);
			val6Obj.setVal(steering.getV6());
			steeringOpts.add(val6Obj);
			//
			Steering val7Obj = new Steering();
			val7Obj.setCode(7);
			val7Obj.setVal(steering.getV7());
			steeringOpts.add(val7Obj);
			//
			Steering val8Obj = new Steering();
			val8Obj.setCode(8);
			val8Obj.setVal(steering.getV8());
			steeringOpts.add(val8Obj);
			//
			Steering val9Obj = new Steering();
			val9Obj.setCode(9);
			val9Obj.setVal(steering.getV9());
			steeringOpts.add(val9Obj);
			//
			Steering val10Obj = new Steering();
			val10Obj.setCode(10);
			val10Obj.setVal(steering.getV10());
			steeringOpts.add(val10Obj);
			//
			Steering val11Obj = new Steering();
			val1Obj.setCode(1);
			val11Obj.setVal(steering.getV11());
			steeringOpts.add(val11Obj);
			//
			Steering val12Obj = new Steering();
			val12Obj.setCode(12);
			val12Obj.setVal(steering.getV12());
			steeringOpts.add(val12Obj);
			//
			Steering val13Obj = new Steering();
			val13Obj.setCode(0);
			val13Obj.setVal(steering.getV13());
			steeringOpts.add(val13Obj);
		}
		return steeringOpts;
	}
	
}