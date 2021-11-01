package com.pverge.core.be;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.pverge.core.db.AttrsGradesDBLoader;
import com.pverge.core.db.AttrsPartsDBLoader;
import com.pverge.core.db.CarCustomizationDBLoader;
import com.pverge.core.db.dbobjects.AttrsGradesEntity;
import com.pverge.core.db.dbobjects.AttrsPartsEntity;
import com.pverge.core.db.dbobjects.AttrsPartsTorqueEntity;
import com.pverge.core.db.dbobjects.AttrsTorqueEntity;
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
	private CarCustomizationDBLoader carCustomizationDB;
	@EJB
	private AttrsGradesDBLoader attrsGradesDB;
	@EJB
	private AttrsPartsDBLoader attrsPartsDB;
	
	/**
	 * Create player client entry
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
		client.setAttrs(getCarAttrs(currentVehicle));
		
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
	 * Create Attributes entry for player vehicle
	 * @return Attributes entry
	 */
	public List<Object> getCarAttrs(PlayerVehicleEntity currentVehicle) {
		AttrsGradesEntity attrsGrades = attrsGradesDB.getAttrsByGrade(currentVehicle.getVcode(), currentVehicle.getGrade());
		if (attrsGrades == null) {
			return getDefaultAttrs(false);
		}
		AttrsTorqueEntity attrsTorque = attrsGrades.getAttrsTorque();
		
		float finalSpeedLimiter = attrsGrades.getSpeedLimiter();
		float finalSpeedLimiterNOS = attrsGrades.getSpeedLimiterNOS();
		
		float torqueX1 = attrsTorque.getX1(); float torqueY1 = attrsTorque.getY1(); float torqueZ1 = attrsTorque.getZ1();
		float torqueX2 = attrsTorque.getX2(); float torqueY2 = attrsTorque.getY2(); float torqueZ2 = attrsTorque.getZ2();
		float torqueX3 = attrsTorque.getX3(); float torqueY3 = attrsTorque.getY3(); float torqueZ3 = attrsTorque.getZ3();
		float torqueX4 = attrsTorque.getX4(); float torqueY4 = attrsTorque.getY4(); float torqueZ4 = attrsTorque.getZ4();
		float torqueX5 = attrsTorque.getX5(); float torqueY5 = attrsTorque.getY5(); float torqueZ5 = attrsTorque.getZ5();
		float torqueX6 = attrsTorque.getX6(); float torqueY6 = attrsTorque.getY6(); float torqueZ6 = attrsTorque.getZ6();
		float torqueX7 = attrsTorque.getX7(); float torqueY7 = attrsTorque.getY7(); float torqueZ7 = attrsTorque.getZ7();
		float torqueX8 = attrsTorque.getX8(); float torqueY8 = attrsTorque.getY8(); float torqueZ8 = attrsTorque.getZ8();
		float torqueX9 = attrsTorque.getX9(); float torqueY9 = attrsTorque.getY9(); float torqueZ9 = attrsTorque.getZ9();
		float torqueX10 = attrsTorque.getX10(); float torqueY10 = attrsTorque.getY10(); float torqueZ10 = attrsTorque.getZ10();
		
		float finalDragCoefficient = attrsGrades.getDragCoefficient();
		float finalGearChangeTime = attrsGrades.getGearChangeTime();
		float finalNosCapacity = attrsGrades.getNosCapacity();
		float finalNosTorqueBoost = attrsGrades.getNosTorqueBoost();
		float finalNosRechargeFactor = attrsGrades.getNosRechargeFactor();
		float finalNosDispenseFactor = attrsGrades.getNosDispenseFactor();
		float finalStrength = attrsGrades.getStrength();
		float finalDurCapacity = attrsGrades.getDurCapacity();
		// Not changed by parts
		float finalChassisMass = attrsGrades.getChassisMass();
		float finalTorqueSplit = attrsGrades.getTorqueSplit();
		float finalTorqueSplitInDrift = attrsGrades.getTorqueSplitInDrift();
		
		if (currentVehicle.getPartEngine() != 0) {
			AttrsPartsEntity enginePart = attrsPartsDB.getPart(currentVehicle.getPartEngine());
			AttrsPartsTorqueEntity enginePartTorque = enginePart.getAttrsPartsTorque();
			finalSpeedLimiter = finalSpeedLimiter + enginePart.getSpeedLimiter();
			finalSpeedLimiterNOS = finalSpeedLimiterNOS + enginePart.getSpeedLimiterNOS();
			//
			torqueY1 = torqueY1 + enginePartTorque.getY1();
			torqueY2 = torqueY2 + enginePartTorque.getY2();
			torqueY3 = torqueY3 + enginePartTorque.getY3();
			torqueY4 = torqueY4 + enginePartTorque.getY4();
			torqueY5 = torqueY5 + enginePartTorque.getY5();
			torqueY6 = torqueY6 + enginePartTorque.getY6();
			torqueY7 = torqueY7 + enginePartTorque.getY7();
			torqueY8 = torqueY8 + enginePartTorque.getY8();
			torqueY9 = torqueY9 + enginePartTorque.getY9();
			torqueY10 = torqueY10 + enginePartTorque.getY10();
		}
		if (currentVehicle.getPartTransmission() != 0) {
			AttrsPartsEntity transmissionPart = attrsPartsDB.getPart(currentVehicle.getPartTransmission());
			AttrsPartsTorqueEntity transmissionPartTorque = transmissionPart.getAttrsPartsTorque();
			finalDragCoefficient = finalDragCoefficient + transmissionPart.getDragCoefficient();
			finalGearChangeTime = finalGearChangeTime + transmissionPart.getGearChangeTime();
			//
			torqueY1 = torqueY1 + transmissionPartTorque.getY1();
			torqueY2 = torqueY2 + transmissionPartTorque.getY2();
			torqueY3 = torqueY3 + transmissionPartTorque.getY3();
			torqueY4 = torqueY4 + transmissionPartTorque.getY4();
			torqueY5 = torqueY5 + transmissionPartTorque.getY5();
			torqueY6 = torqueY6 + transmissionPartTorque.getY6();
			torqueY7 = torqueY7 + transmissionPartTorque.getY7();
			torqueY8 = torqueY8 + transmissionPartTorque.getY8();
			torqueY9 = torqueY9 + transmissionPartTorque.getY9();
			torqueY10 = torqueY10 + transmissionPartTorque.getY10();
		}
		if (currentVehicle.getPartNitroTank() != 0) {
			AttrsPartsEntity nitroTankPart = attrsPartsDB.getPart(currentVehicle.getPartNitroTank());
			finalNosCapacity = finalNosCapacity + nitroTankPart.getNosCapacity();
			finalNosTorqueBoost = finalNosTorqueBoost + nitroTankPart.getNosTorqueBoost();
			finalNosRechargeFactor = finalNosRechargeFactor + nitroTankPart.getNosRechargeFactor();
			finalNosDispenseFactor = finalNosDispenseFactor + nitroTankPart.getNosDispenseFactor();
		}
		if (currentVehicle.getPartBumper() != 0) {
			AttrsPartsEntity bumperPart = attrsPartsDB.getPart(currentVehicle.getPartBumper());
			finalStrength = finalStrength + bumperPart.getStrength();
		}
		if (currentVehicle.getPartFrame() != 0) {
			AttrsPartsEntity framePart = attrsPartsDB.getPart(currentVehicle.getPartFrame());
			finalDurCapacity = finalDurCapacity + framePart.getDurCapacity();
		}
		
		List<Object> attrsList = new ArrayList<>();
		Attrs attrEngineSpeedLimiter = new Attrs();
		attrEngineSpeedLimiter.setAttr("Engine.SpeedLimiter");
		attrEngineSpeedLimiter.setType("float");
		attrEngineSpeedLimiter.setVal(finalSpeedLimiter);
		Attrs attrEngineSpeedLimiterNOS = new Attrs();
		attrEngineSpeedLimiterNOS.setAttr("Engine.SpeedLimiterNOS");
		attrEngineSpeedLimiterNOS.setType("float");
		attrEngineSpeedLimiterNOS.setVal(finalSpeedLimiterNOS);
		
		AttrsGraph attrEngineTorque = new AttrsGraph();
		attrEngineTorque.setAttr("Engine.Torque");
		attrEngineTorque.setType("graph");
		
		GraphValue graphTorque1 = new GraphValue();
		graphTorque1.setX(torqueX1);
		graphTorque1.setY(torqueY1);
		graphTorque1.setZ(torqueZ1);
		GraphValue graphTorque2 = new GraphValue();
		graphTorque2.setX(torqueX2);
		graphTorque2.setY(torqueY2);
		graphTorque2.setZ(torqueZ2);
		GraphValue graphTorque3 = new GraphValue();
		graphTorque3.setX(torqueX3);
		graphTorque3.setY(torqueY3);
		graphTorque3.setZ(torqueZ3);
		GraphValue graphTorque4 = new GraphValue();
		graphTorque4.setX(torqueX4);
		graphTorque4.setY(torqueY4);
		graphTorque4.setZ(torqueZ4);
		GraphValue graphTorque5 = new GraphValue();
		graphTorque5.setX(torqueX5);
		graphTorque5.setY(torqueY5);
		graphTorque5.setZ(torqueZ5);
		GraphValue graphTorque6 = new GraphValue();
		graphTorque6.setX(torqueX6);
		graphTorque6.setY(torqueY6);
		graphTorque6.setZ(torqueZ6);
		GraphValue graphTorque7 = new GraphValue();
		graphTorque7.setX(torqueX7);
		graphTorque7.setY(torqueY7);
		graphTorque7.setZ(torqueZ7);
		GraphValue graphTorque8 = new GraphValue();
		graphTorque8.setX(torqueX8);
		graphTorque8.setY(torqueY8);
		graphTorque8.setZ(torqueZ8);
		GraphValue graphTorque9 = new GraphValue();
		graphTorque9.setX(torqueX9);
		graphTorque9.setY(torqueY9);
		graphTorque9.setZ(torqueZ9);
		GraphValue graphTorque10 = new GraphValue();
		graphTorque10.setX(torqueX10);
		graphTorque10.setY(torqueY10);
		graphTorque10.setZ(torqueZ10);
		
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
		attrAerodynamicsDragCoefficient.setVal(finalDragCoefficient);
		Attrs attrTransmissionGearChangeTime = new Attrs();
		attrTransmissionGearChangeTime.setAttr("Transmission.GearChangeTime");
		attrTransmissionGearChangeTime.setType("float");
		attrTransmissionGearChangeTime.setVal(finalGearChangeTime);
		Attrs attrNosConfigCapacity = new Attrs();
		attrNosConfigCapacity.setAttr("NosConfig.Capacity");
		attrNosConfigCapacity.setType("float");
		attrNosConfigCapacity.setVal(finalNosCapacity);
		Attrs attrNosConfigTorqueBoost = new Attrs();
		attrNosConfigTorqueBoost.setAttr("NosConfig.TorqueBoost");
		attrNosConfigTorqueBoost.setType("float");
		attrNosConfigTorqueBoost.setVal(finalNosTorqueBoost);
		Attrs attrNosConfigNosRechargeFactor = new Attrs();
		attrNosConfigNosRechargeFactor.setAttr("NosConfig.NosRechargeFactor");
		attrNosConfigNosRechargeFactor.setType("float");
		attrNosConfigNosRechargeFactor.setVal(finalNosRechargeFactor);
		Attrs attrNosConfigNosDispenseFactor = new Attrs();
		attrNosConfigNosDispenseFactor.setAttr("NosConfig.NosDispenseFactor");
		attrNosConfigNosDispenseFactor.setType("float");
		attrNosConfigNosDispenseFactor.setVal(finalNosDispenseFactor);
		Attrs attrStrengthStrength = new Attrs();
		attrStrengthStrength.setAttr("Strength.Strength");
		attrStrengthStrength.setType("float");
		attrStrengthStrength.setVal(finalStrength);
		Attrs attrDurabilityCapacity = new Attrs();
		attrDurabilityCapacity.setAttr("Durability.Capacity");
		attrDurabilityCapacity.setType("float");
		attrDurabilityCapacity.setVal(finalDurCapacity);
		Attrs attrChassisMass = new Attrs();
		attrChassisMass.setAttr("Chassis.Mass");
		attrChassisMass.setType("float");
		attrChassisMass.setVal(finalChassisMass);
		Attrs attrTransmissionTorqueSplit = new Attrs();
		attrTransmissionTorqueSplit.setAttr("Transmission.TorqueSplit");
		attrTransmissionTorqueSplit.setType("float");
		attrTransmissionTorqueSplit.setVal(finalTorqueSplit);
		Attrs attrTransmissionTorqueSplitInDrift = new Attrs();
		attrTransmissionTorqueSplitInDrift.setAttr("Transmission.TorqueSplitInDrift");
		attrTransmissionTorqueSplitInDrift.setType("float");
		attrTransmissionTorqueSplitInDrift.setVal(finalTorqueSplitInDrift);
		
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
	
	/**
	 * Create generic Attributes entry
	 * @return Attributes entry
	 */
	public List<Object> getDefaultAttrs(boolean fill) {
		if (fill) {
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
		List<Object> dummyAttrsList = new ArrayList<>();
		return dummyAttrsList;
	}
}
