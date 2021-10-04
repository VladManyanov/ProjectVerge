package com.pverge.core.be;

import java.util.ArrayList;
import java.util.List;

import com.pverge.core.db.dbobjects.PlayerVehicleEntity;
import com.pverge.core.socket.dataobjects.SIOMatchObjects.NumberPlate;
import com.pverge.core.socket.dataobjects.SIORaceCommonObjects.*;

/**
 * Edge - Various methods to fill & prepare Match events data
 * @author Hypernucle
 */
public class EdgeMatchCreationBE {
	
	/**
	 * Create player client entry
	 * @return Player data
	 */
	public Clients createPlayerClient(String playerId, PlayerVehicleEntity currentVehicle, boolean isAI) {
		Clients client = new Clients();
		client.setPlayerId(playerId);
		client.setAccountId(playerId);
		client.setVCode(currentVehicle.getVcode());
		client.setAI(isAI);
		client.setTeam(0);
		client.setPositionOnStartingGrid(0);
		client.setVid(String.valueOf(currentVehicle.getId()));
		client.setAttrs(getDefaultAttrs());
		
		AppearanceInfo appearanceInfo = new AppearanceInfo();
		ColorData colorData = new ColorData();
		Rgb rgb = new Rgb();
		rgb.setSolid(currentVehicle.getRGBSolid());
		rgb.setSecondary(currentVehicle.getRGBSecondary());
		colorData.setRgb(rgb);
		appearanceInfo.setColorData(colorData);
		appearanceInfo.setWheelId(1);
		appearanceInfo.setWrapId(0);
		
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
	 * Create generic Attributes entry
	 * @return Attributes entry
	 */
	public List<Object> getDefaultAttrs() {
		List<Object> attrsList = new ArrayList<>();
		Attrs attrEngineSpeedLimiter = new Attrs();
		attrEngineSpeedLimiter.setAttr("Engine.SpeedLimiter");
		attrEngineSpeedLimiter.setType("float");
		attrEngineSpeedLimiter.setVal(168.9f);
		Attrs attrEngineSpeedLimiterNOS = new Attrs();
		attrEngineSpeedLimiterNOS.setAttr("Engine.SpeedLimiterNOS");
		attrEngineSpeedLimiterNOS.setType("float");
		attrEngineSpeedLimiterNOS.setVal(183.9f);
		
		AttrsGraph attrEngineTorque = new AttrsGraph();
		attrEngineTorque.setAttr("Engine.Torque");
		attrEngineTorque.setType("graph");
		
		GraphValue graphTorque1 = new GraphValue();
		graphTorque1.setX(1000);
		graphTorque1.setY(0);
		graphTorque1.setZ(1);
		GraphValue graphTorque2 = new GraphValue();
		graphTorque2.setX(1020);
		graphTorque2.setY(1865);
		graphTorque2.setZ(8);
		GraphValue graphTorque3 = new GraphValue();
		graphTorque3.setX(1000);
		graphTorque3.setY(427.14000000001215f);
		graphTorque3.setZ(32);
		GraphValue graphTorque4 = new GraphValue();
		graphTorque4.setX(1712.5f);
		graphTorque4.setY(458.34000000001214f);
		graphTorque4.setZ(32);
		GraphValue graphTorque5 = new GraphValue();
		graphTorque5.setX(3012.49975f);
		graphTorque5.setY(478.34000000001214f);
		graphTorque5.setZ(32);
		GraphValue graphTorque6 = new GraphValue();
		graphTorque6.setX(4774.99975f);
		graphTorque6.setY(491.14000000001215f);
		graphTorque6.setZ(32);
		GraphValue graphTorque7 = new GraphValue();
		graphTorque7.setX(6000.000249999999f);
		graphTorque7.setY(500.7400000000122f);
		graphTorque7.setZ(32);
		GraphValue graphTorque8 = new GraphValue();
		graphTorque8.setX(6724.99975f);
		graphTorque8.setY(492.7400000000122f);
		graphTorque8.setZ(32);
		GraphValue graphTorque9 = new GraphValue();
		graphTorque9.setX(7637.5f);
		graphTorque9.setY(480.7400000000122f);
		graphTorque9.setZ(32);
		GraphValue graphTorque10 = new GraphValue();
		graphTorque10.setX(8500);
		graphTorque10.setY(457.5400000000121f);
		graphTorque10.setZ(32);
		
		List<GraphValue> graphValueList = new ArrayList<>();
		graphValueList.add(graphTorque1);
		graphValueList.add(graphTorque2);
		graphValueList.add(graphTorque3);
		graphValueList.add(graphTorque4);
		graphValueList.add(graphTorque5);
		graphValueList.add(graphTorque6);
		graphValueList.add(graphTorque7);
		graphValueList.add(graphTorque8);
		graphValueList.add(graphTorque9);
		graphValueList.add(graphTorque10);
		attrEngineTorque.setVal(graphValueList);
		
		Attrs attrAerodynamicsDragCoefficient = new Attrs();
		attrAerodynamicsDragCoefficient.setAttr("Aerodynamics.DragCoefficient");
		attrAerodynamicsDragCoefficient.setType("float");
		attrAerodynamicsDragCoefficient.setVal(0.419f);
		Attrs attrTransmissionGearChangeTime = new Attrs();
		attrTransmissionGearChangeTime.setAttr("Transmission.GearChangeTime");
		attrTransmissionGearChangeTime.setType("float");
		attrTransmissionGearChangeTime.setVal(0.40376f);
		Attrs attrNosConfigCapacity = new Attrs();
		attrNosConfigCapacity.setAttr("NosConfig.Capacity");
		attrNosConfigCapacity.setType("float");
		attrNosConfigCapacity.setVal(32.12f);
		Attrs attrNosConfigTorqueBoost = new Attrs();
		attrNosConfigTorqueBoost.setAttr("NosConfig.TorqueBoost");
		attrNosConfigTorqueBoost.setType("float");
		attrNosConfigTorqueBoost.setVal(1.089f);
		Attrs attrNosConfigNosRechargeFactor = new Attrs();
		attrNosConfigNosRechargeFactor.setAttr("NosConfig.NosRechargeFactor");
		attrNosConfigNosRechargeFactor.setType("float");
		attrNosConfigNosRechargeFactor.setVal(1.2668f);
		Attrs attrNosConfigNosDispenseFactor = new Attrs();
		attrNosConfigNosDispenseFactor.setAttr("NosConfig.NosDispenseFactor");
		attrNosConfigNosDispenseFactor.setType("float");
		attrNosConfigNosDispenseFactor.setVal(1.4666f);
		Attrs attrStrengthStrength = new Attrs();
		attrStrengthStrength.setAttr("Strength.Strength");
		attrStrengthStrength.setType("float");
		attrStrengthStrength.setVal(1.93f);
		Attrs attrDurabilityCapacity = new Attrs();
		attrDurabilityCapacity.setAttr("Durability.Capacity");
		attrDurabilityCapacity.setType("float");
		attrDurabilityCapacity.setVal(56f);
		Attrs attrChassisMass = new Attrs();
		attrChassisMass.setAttr("Chassis.Mass");
		attrChassisMass.setType("float");
		attrChassisMass.setVal(1800f);
		Attrs attrTransmissionTorqueSplit = new Attrs();
		attrTransmissionTorqueSplit.setAttr("Transmission.TorqueSplit");
		attrTransmissionTorqueSplit.setType("float");
		attrTransmissionTorqueSplit.setVal(0f);
		Attrs attrTransmissionTorqueSplitInDrift = new Attrs();
		attrTransmissionTorqueSplitInDrift.setAttr("Transmission.TorqueSplitInDrift");
		attrTransmissionTorqueSplitInDrift.setType("float");
		attrTransmissionTorqueSplitInDrift.setVal(0f);
		
		attrsList.add(attrEngineSpeedLimiter);
		attrsList.add(attrEngineSpeedLimiterNOS);
		attrsList.add(attrEngineTorque); // Graph
		attrsList.add(attrAerodynamicsDragCoefficient);
		attrsList.add(attrTransmissionGearChangeTime);
		attrsList.add(attrNosConfigCapacity);
		attrsList.add(attrNosConfigTorqueBoost);
		attrsList.add(attrNosConfigNosRechargeFactor);
		attrsList.add(attrNosConfigNosDispenseFactor);
		attrsList.add(attrStrengthStrength);
		attrsList.add(attrDurabilityCapacity);
		attrsList.add(attrChassisMass);
		attrsList.add(attrTransmissionTorqueSplit);
		attrsList.add(attrTransmissionTorqueSplitInDrift);
		
		return attrsList;
	}
}
