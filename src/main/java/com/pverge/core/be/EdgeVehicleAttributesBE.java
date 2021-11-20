package com.pverge.core.be;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.pverge.core.db.AttrsGradesDBLoader;
import com.pverge.core.db.AttrsPartsDBLoader;
import com.pverge.core.db.VehicleSteeringDBLoader;
import com.pverge.core.db.dbobjects.AttrsGradesEntity;
import com.pverge.core.db.dbobjects.AttrsPartsEntity;
import com.pverge.core.db.dbobjects.AttrsPartsTorqueEntity;
import com.pverge.core.db.dbobjects.AttrsTorqueEntity;
import com.pverge.core.db.dbobjects.PlayerVehicleEntity;
import com.pverge.core.db.dbobjects.VehicleSteeringEntity;
import com.pverge.core.socket.dataobjects.SIORaceCommonObjects.*;

/**
 * Edge - Prepare vehicle attributes data for events
 * @author Hypernucle
 */
@Stateless
public class EdgeVehicleAttributesBE {
	
	@EJB
	private AttrsGradesDBLoader attrsGradesDB;
	@EJB
	private AttrsPartsDBLoader attrsPartsDB;
	@EJB
	private VehicleSteeringDBLoader vehicleSteeringDB;
	
	/**
	 * Get Attributes entry for stock vehicle
	 * @return Attributes entry
	 */
	public List<Object> getStockCarAttrs(int vCode, int grade) {
		PlayerVehicleEntity stockVehicle = new PlayerVehicleEntity();
		stockVehicle.setVcode(vCode);
		stockVehicle.setGrade(grade);
		stockVehicle.setPartBumper(0);
		stockVehicle.setPartEngine(0);
		stockVehicle.setPartFrame(0);
		stockVehicle.setPartNitroTank(0);
		stockVehicle.setPartTransmission(0);
		return getCarAttrs(stockVehicle);
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
		return new ArrayList<>();
	}
	
	/**
	 * Create Steering attributes entry for player vehicle. BigDecimal calculations is used 
	 * to avoid rounding & calculations errors
	 * @return Steering attributes entry
	 */
	public List<SteeringAttrs> getSteeringAttrs(PlayerVehicleEntity currentVehicle) {
		VehicleSteeringEntity steeringSettings = vehicleSteeringDB.findByVid(currentVehicle.getId());
		List<SteeringAttrs> attrsList = new ArrayList<>();
		int currentValue = 50;
		boolean equalValues = false;
		BigDecimal calc = new BigDecimal("0");
		if (steeringSettings == null) { // Use default steering settings
			steeringSettings = new VehicleSteeringEntity();
			steeringSettings.setV0(currentValue); steeringSettings.setV1(currentValue); steeringSettings.setV2(currentValue);
			steeringSettings.setV3(currentValue); steeringSettings.setV4(currentValue); steeringSettings.setV5(currentValue);
			steeringSettings.setV6(currentValue); steeringSettings.setV7(currentValue); steeringSettings.setV8(currentValue);
			steeringSettings.setV9(currentValue); steeringSettings.setV10(currentValue); steeringSettings.setV11(currentValue);
			steeringSettings.setV12(currentValue); steeringSettings.setV13(currentValue); 
		} else if (vehicleSteeringDB.isSteeringValuesEqual(steeringSettings)) {
			equalValues = true;
		}
		
		// Brake Pressure Distribution
		currentValue = steeringSettings.getV0();
		SteeringAttrs attr0 = new SteeringAttrs();
		attr0.setAttr(59);
		attr0.setModType(0);
		calc = new BigDecimal("0.006").multiply(BigDecimal.valueOf(currentValue));
		attr0.setVal( (new BigDecimal("0.7").add(calc).doubleValue()) );
		attrsList.add(attr0);
		
		SteeringAttrs attr1 = new SteeringAttrs();
		attr1.setAttr(151);
		attr1.setModType(0);
		calc = new BigDecimal("0.002").multiply(BigDecimal.valueOf(currentValue));
		attr1.setVal( (new BigDecimal("0.9").add(calc).doubleValue()) );
		attrsList.add(attr1);
		
		SteeringAttrs attr2 = new SteeringAttrs();
		attr2.setAttr(153);
		attr2.setModType(0);
		calc = new BigDecimal("0.002").multiply(BigDecimal.valueOf(currentValue));
		attr2.setVal( (new BigDecimal("0.9").add(calc).doubleValue()) );
		attrsList.add(attr2);
		
		SteeringAttrs attr3 = new SteeringAttrs();
		attr3.setAttr(77);
		attr3.setModType(1);
		calc = new BigDecimal("0.01").multiply(BigDecimal.valueOf(currentValue));
		attr3.setVal( (new BigDecimal("-0.5").add(calc).doubleValue()) );
		attrsList.add(attr3);
		
		// Brake Pressure
		currentValue = steeringSettings.getV1();
		SteeringAttrs attr4 = new SteeringAttrs();
		attr4.setAttr(58);
		attr4.setModType(0);
		calc = new BigDecimal("0.002").multiply(BigDecimal.valueOf(currentValue));
		attr4.setVal( (new BigDecimal("1.1").subtract(calc).doubleValue()) );
		attrsList.add(attr4);
		
		// Braking Gear
		currentValue = steeringSettings.getV2();
		SteeringAttrs attr5 = new SteeringAttrs();
		attr5.setAttr(140);
		attr5.setModType(1);
		if (currentValue <= 50) {
			calc = new BigDecimal("0.01").multiply(BigDecimal.valueOf(currentValue));
			attr5.setVal( (new BigDecimal("0.5").subtract(calc).doubleValue()) );
		} else {
			calc = new BigDecimal("0.0075").multiply(BigDecimal.valueOf(currentValue));
			attr5.setVal( (new BigDecimal("0").subtract(calc).doubleValue()) );
		}
		attrsList.add(attr5);
		
		// E-Brake Strength
		currentValue = steeringSettings.getV3();
		SteeringAttrs attr6 = new SteeringAttrs();
		attr6.setAttr(78);
		attr6.setModType(0);
		calc = new BigDecimal("0.006").multiply(BigDecimal.valueOf(currentValue));
		attr6.setVal( (new BigDecimal("0.7").add(calc).doubleValue()) );
		attrsList.add(attr6);
		
		// Suspension Strength
		currentValue = steeringSettings.getV4();
		SteeringAttrs attr7 = new SteeringAttrs();
		attr7.setAttr(137);
		attr7.setModType(0);
		calc = new BigDecimal("0.015").multiply(BigDecimal.valueOf(currentValue));
		attr7.setVal( (new BigDecimal("0.25").add(calc).doubleValue()) );
		attrsList.add(attr7);
		
		// Anti-roll Bar
		currentValue = steeringSettings.getV5();
		SteeringAttrs attr8 = new SteeringAttrs();
		attr8.setAttr(139);
		attr8.setModType(1);
		calc = new BigDecimal("0.008").multiply(BigDecimal.valueOf(currentValue));
		attr8.setVal( (new BigDecimal("-0.4").add(calc).doubleValue()) );
		attrsList.add(attr8);
		
		// Tire Grip
		currentValue = steeringSettings.getV6();
		SteeringAttrs attr9 = new SteeringAttrs();
		attr9.setAttr(26);
		attr9.setModType(1);
		if (currentValue <= 50) {
			calc = new BigDecimal("0.06").multiply(BigDecimal.valueOf(currentValue));
			attr9.setVal( (new BigDecimal("-3").add(calc).doubleValue()) );
		} else {
			calc = new BigDecimal("0.09").multiply(BigDecimal.valueOf(currentValue));
			attr9.setVal( (new BigDecimal("-4.5").add(calc).doubleValue()) );
		}
		attrsList.add(attr9);
		
		// Front Wheel Pressure
		currentValue = steeringSettings.getV7();
		SteeringAttrs attr10 = new SteeringAttrs();
		attr10.setAttr(56);
		attr10.setModType(0);
		if (currentValue <= 50) {
			calc = new BigDecimal("0.001").multiply(BigDecimal.valueOf(currentValue));
			attr10.setVal( (new BigDecimal("1.05").subtract(calc).doubleValue()) );
		} else {
			calc = new BigDecimal("0.003").multiply(BigDecimal.valueOf(currentValue));
			attr10.setVal( (new BigDecimal("1.15").subtract(calc).doubleValue()) );
		}
		attrsList.add(attr10);
		
		// Rear Wheel Pressure
		currentValue = steeringSettings.getV8();
		SteeringAttrs attr11 = new SteeringAttrs();
		attr11.setAttr(57);
		attr11.setModType(0);
		if (currentValue <= 50) {
			calc = new BigDecimal("0.003").multiply(BigDecimal.valueOf(currentValue));
			attr11.setVal( (new BigDecimal("0.85").add(calc).doubleValue()) );
		} else {
			calc = new BigDecimal("0.006").multiply(BigDecimal.valueOf(currentValue));
			attr11.setVal( (new BigDecimal("0.7").add(calc).doubleValue()) );
		}
		attrsList.add(attr11);
		
		// Steering Response Time
		currentValue = steeringSettings.getV9();
		SteeringAttrs attr12 = new SteeringAttrs();
		attr12.setAttr(60);
		attr12.setModType(0);
		calc = new BigDecimal("0.002").multiply(BigDecimal.valueOf(currentValue));
		attr12.setVal( (new BigDecimal("1.1").subtract(calc).doubleValue()) );
		attrsList.add(attr12);
		
		SteeringAttrs attr13 = new SteeringAttrs();
		attr13.setAttr(80);
		attr13.setModType(0);
		calc = new BigDecimal("0.004").multiply(BigDecimal.valueOf(currentValue));
		attr13.setVal( (new BigDecimal("0.8").add(calc).doubleValue()) );
		attrsList.add(attr13);
		
		// Turn Radius
		currentValue = steeringSettings.getV10();
		SteeringAttrs attr14 = new SteeringAttrs();
		attr14.setAttr(81);
		attr14.setModType(0);
		calc = new BigDecimal("0.005").multiply(BigDecimal.valueOf(currentValue));
		attr14.setVal( (new BigDecimal("1.25").subtract(calc).doubleValue()) );
		attrsList.add(attr14);
		
		SteeringAttrs attr15 = new SteeringAttrs();
		attr15.setAttr(82);
		attr15.setModType(0);
		calc = new BigDecimal("0.005").multiply(BigDecimal.valueOf(currentValue));
		attr15.setVal( (new BigDecimal("1.25").subtract(calc).doubleValue()) );
		attrsList.add(attr15);
		
		SteeringAttrs attr16 = new SteeringAttrs();
		attr16.setAttr(84);
		attr16.setModType(1);
		calc = new BigDecimal("0.5").multiply(BigDecimal.valueOf(currentValue));
		attr16.setVal( (new BigDecimal("25").subtract(calc).doubleValue()) );
		attrsList.add(attr16);
		
		SteeringAttrs attr17 = new SteeringAttrs();
		attr17.setAttr(85);
		attr17.setModType(1);
		calc = new BigDecimal("0.1").multiply(BigDecimal.valueOf(currentValue));
		attr17.setVal( (new BigDecimal("5").subtract(calc).doubleValue()) );
		attrsList.add(attr17);
		
		// Brake Drift Assist
		currentValue = steeringSettings.getV11();
		SteeringAttrs attr18 = new SteeringAttrs();
		attr18.setAttr(99);
		attr18.setModType(0);
		if (currentValue <= 50) {
			calc = new BigDecimal("0.98").multiply(BigDecimal.valueOf(currentValue));
			attr18.setVal( (new BigDecimal("1").add(calc).doubleValue()) );
		} else {
			calc = new BigDecimal("0.99").multiply(BigDecimal.valueOf(currentValue));
			attr18.setVal( (new BigDecimal("0.5").add(calc).doubleValue()) );
		}
		attrsList.add(attr18);
		
		SteeringAttrs attr19 = new SteeringAttrs();
		attr19.setAttr(100);
		attr19.setModType(0);
		if (currentValue <= 50) {
			calc = new BigDecimal("0.98").multiply(BigDecimal.valueOf(currentValue));
			attr19.setVal( (new BigDecimal("1").add(calc).doubleValue()) );
		} else {
			calc = new BigDecimal("0.99").multiply(BigDecimal.valueOf(currentValue));
			attr19.setVal( (new BigDecimal("0.5").add(calc).doubleValue()) );
		}
		attrsList.add(attr19);
		
		SteeringAttrs attr20 = new SteeringAttrs();
		attr20.setAttr(103);
		attr20.setModType(0);
		if (currentValue <= 50) {
			calc = new BigDecimal("0.98").multiply(BigDecimal.valueOf(currentValue));
			attr20.setVal( (new BigDecimal("1").add(calc).doubleValue()) );
		} else {
			calc = new BigDecimal("0.99").multiply(BigDecimal.valueOf(currentValue));
			attr20.setVal( (new BigDecimal("0.5").add(calc).doubleValue()) );
		}
		attrsList.add(attr20);
		
		// Drift Stability
		currentValue = steeringSettings.getV12();
		SteeringAttrs attr21 = new SteeringAttrs();
		attr21.setAttr(135);
		attr21.setModType(1);
		if (currentValue <= 50) {
			calc = new BigDecimal("0.014").multiply(BigDecimal.valueOf(currentValue));
			attr21.setVal( (new BigDecimal("0.7").subtract(calc).doubleValue()) );
		} else {
			calc = new BigDecimal("0.0013").multiply(BigDecimal.valueOf(currentValue));
			attr21.setVal( (new BigDecimal("0.065").subtract(calc).doubleValue()) );
		}
		attrsList.add(attr21);
		
		SteeringAttrs attr22 = new SteeringAttrs();
		attr22.setAttr(136);
		attr22.setModType(1);
		if (currentValue <= 50) {
			calc = new BigDecimal("0.008").multiply(BigDecimal.valueOf(currentValue));
			attr22.setVal( (new BigDecimal("0.4").subtract(calc).doubleValue()) );
		} else {
			calc = new BigDecimal("0.0013").multiply(BigDecimal.valueOf(currentValue));
			attr22.setVal( (new BigDecimal("0.065").subtract(calc).doubleValue()) );
		}
		attrsList.add(attr22);
		
		SteeringAttrs attr23 = new SteeringAttrs();
		attr23.setAttr(108);
		attr23.setModType(0);
		if (currentValue <= 50) {
			calc = new BigDecimal("0.001").multiply(BigDecimal.valueOf(currentValue));
			attr23.setVal( (new BigDecimal("0.95").add(calc).doubleValue()) );
		} else {
			calc = new BigDecimal("0.0012").multiply(BigDecimal.valueOf(currentValue));
			attr23.setVal( (new BigDecimal("0.9").add(calc).doubleValue()) );
		}
		attrsList.add(attr23);
		
		SteeringAttrs attr24 = new SteeringAttrs();
		attr24.setAttr(109);
		attr24.setModType(0);
		if (currentValue <= 50) {
			calc = new BigDecimal("0.008").multiply(BigDecimal.valueOf(currentValue));
			attr24.setVal( (new BigDecimal("0.6").add(calc).doubleValue()) );
		} else {
			calc = new BigDecimal("0.004").multiply(BigDecimal.valueOf(currentValue));
			attr24.setVal( (new BigDecimal("0.8").add(calc).doubleValue()) );
		}
		attrsList.add(attr24);
		
		SteeringAttrs attr25 = new SteeringAttrs();
		attr25.setAttr(110);
		attr25.setModType(0);
		if (currentValue <= 50) {
			calc = new BigDecimal("0.012").multiply(BigDecimal.valueOf(currentValue));
			attr25.setVal( (new BigDecimal("1.6").subtract(calc).doubleValue()) );
		} else {
			calc = new BigDecimal("0.004").multiply(BigDecimal.valueOf(currentValue));
			attr25.setVal( (new BigDecimal("1.2").subtract(calc).doubleValue()) );
		}
		attrsList.add(attr25);
		
		SteeringAttrs attr26 = new SteeringAttrs();
		attr26.setAttr(111);
		attr26.setModType(0);
		if (currentValue <= 50) {
			calc = new BigDecimal("0.012").multiply(BigDecimal.valueOf(currentValue));
			attr26.setVal( (new BigDecimal("0.4").add(calc).doubleValue()) );
		} else {
			calc = new BigDecimal("0.004").multiply(BigDecimal.valueOf(currentValue));
			attr26.setVal( (new BigDecimal("0.8").add(calc).doubleValue()) );
		}
		attrsList.add(attr26);
		
		SteeringAttrs attr27 = new SteeringAttrs();
		attr27.setAttr(105);
		attr27.setModType(0);
		if (currentValue <= 50) {
			calc = new BigDecimal("0.012").multiply(BigDecimal.valueOf(currentValue));
			attr27.setVal( (new BigDecimal("1.6").subtract(calc).doubleValue()) );
		} else {
			calc = new BigDecimal("0.003").multiply(BigDecimal.valueOf(currentValue));
			attr27.setVal( (new BigDecimal("1.15").subtract(calc).doubleValue()) );
		}
		attrsList.add(attr27);
		
		// Unknown parameter which is changed when all setting values is equal
		SteeringAttrs attr28 = new SteeringAttrs();
		attr28.setAttr(154);
		attr28.setModType(1);
		if (equalValues && currentValue <= 50) {
			calc = new BigDecimal("0.016").multiply(BigDecimal.valueOf(currentValue));
			attr28.setVal( (new BigDecimal("0.8").subtract(calc).doubleValue()) );
		} else {
			attr28.setVal(0); // Still 0 when value is > 50
		}
		attrsList.add(attr28);
		
		// Enable Controller
		currentValue = steeringSettings.getV13();
		SteeringAttrs attr29 = new SteeringAttrs();
		attr29.setAttr(133);
		attr29.setModType(0);
		if (currentValue <= 50) {
			calc = new BigDecimal("0.005").multiply(BigDecimal.valueOf(currentValue));
			attr29.setVal( (new BigDecimal("1.25").subtract(calc).doubleValue()) );
		} else {
			calc = new BigDecimal("0.008").multiply(BigDecimal.valueOf(currentValue));
			attr29.setVal( (new BigDecimal("1.4").subtract(calc).doubleValue()) );
		}
		attrsList.add(attr29);
		
		SteeringAttrs attr30 = new SteeringAttrs();
		attr30.setAttr(18);
		attr30.setModType(0);
		if (currentValue <= 50) {
			calc = new BigDecimal("0.004").multiply(BigDecimal.valueOf(currentValue));
			attr30.setVal( (new BigDecimal("0.8").add(calc).doubleValue()) );
		} else {
			calc = new BigDecimal("0.006").multiply(BigDecimal.valueOf(currentValue));
			attr30.setVal( (new BigDecimal("0.7").add(calc).doubleValue()) );
		}
		attrsList.add(attr30);
		
		return attrsList;
	}
	
}
