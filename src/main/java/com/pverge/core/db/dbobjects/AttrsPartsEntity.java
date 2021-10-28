package com.pverge.core.db.dbobjects;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "ATTRSPARTS")
@NamedQueries({ 
	@NamedQuery(name = "AttrsPartsEntity.getPart", query = "SELECT obj FROM AttrsPartsEntity obj WHERE obj.partId = :partId"), //
	@NamedQuery(name = "AttrsPartsEntity.loadAll", query = "SELECT obj FROM AttrsPartsEntity obj") //
})
public class AttrsPartsEntity {

	@Id
	private int partId;
	
	@ManyToOne
	@JoinColumn(name = "ATTRSPARTSTORQUEID", referencedColumnName = "PARTID", foreignKey = @ForeignKey(name = "ATTRSPARTSTORQUE_FK"))
	private AttrsPartsTorqueEntity attrsPartsTorque;
	
	@ManyToOne
	@JoinColumn(name = "INFOID", referencedColumnName = "PARTID", foreignKey = @ForeignKey(name = "INFO_FK"))
	private AttrsPartsInfoEntity attrsPartsInfo;
	
	// Top Speed
	private float speedLimiter;
	private float speedLimiterNOS;
	// Acceleration
	private float dragCoefficient;
	private float gearChangeTime;
	private float torqueSplit;
	private float torqueSplitInDrift;
	// Nitro
	private float nosTorqueBoost;
	private float nosCapacity;
	private float nosRechargeFactor;
	private float nosDispenseFactor;
	// Strength
	private float strength;
	// Durability
	private float durCapacity;
	
	private float chassisMass;
	
	public int getPartId() {
		return partId;
	}
	public void setPartId(int partId) {
		this.partId = partId;
	}
	
	public AttrsPartsTorqueEntity getAttrsPartsTorque() {
		return attrsPartsTorque;
	}
	public void setAttrsPartsTorque(AttrsPartsTorqueEntity attrsPartsTorque) {
		this.attrsPartsTorque = attrsPartsTorque;
	}
	
	public AttrsPartsInfoEntity getAttrsPartsInfo() {
		return attrsPartsInfo;
	}
	public void setAttrsPartsTorque(AttrsPartsInfoEntity attrsPartsInfo) {
		this.attrsPartsInfo = attrsPartsInfo;
	}
	
	public float getSpeedLimiter() {
		return speedLimiter;
	}
	public void setSpeedLimiter(float speedLimiter) {
		this.speedLimiter = speedLimiter;
	}
	
	public float getSpeedLimiterNOS() {
		return speedLimiterNOS;
	}
	public void setSpeedLimiterNOS(float speedLimiterNOS) {
		this.speedLimiterNOS = speedLimiterNOS;
	}
	
	public float getDragCoefficient() {
		return dragCoefficient;
	}
	public void setDragCoefficient(float dragCoefficient) {
		this.dragCoefficient = dragCoefficient;
	}
	
	public float getGearChangeTime() {
		return gearChangeTime;
	}
	public void setGearChangeTime(float gearChangeTime) {
		this.gearChangeTime = gearChangeTime;
	}
	
	public float getTorqueSplit() {
		return torqueSplit;
	}
	public void setTorqueSplit(float torqueSplit) {
		this.torqueSplit = torqueSplit;
	}
	
	public float getTorqueSplitInDrift() {
		return torqueSplitInDrift;
	}
	public void setTorqueSplitInDrift(float torqueSplitInDrift) {
		this.torqueSplitInDrift = torqueSplitInDrift;
	}
	
	public float getNosTorqueBoost() {
		return nosTorqueBoost;
	}
	public void setNosTorqueBoost(float nosTorqueBoost) {
		this.nosTorqueBoost = nosTorqueBoost;
	}
	
	public float getNosCapacity() {
		return nosCapacity;
	}
	public void setNosCapacity(float nosCapacity) {
		this.nosCapacity = nosCapacity;
	}
	
	public float getNosRechargeFactor() {
		return nosRechargeFactor;
	}
	public void setNosRechargeFactor(float nosRechargeFactor) {
		this.nosRechargeFactor = nosRechargeFactor;
	}
	
	public float getNosDispenseFactor() {
		return nosDispenseFactor;
	}
	public void setNosDispenseFactor(float nosDispenseFactor) {
		this.nosDispenseFactor = nosDispenseFactor;
	}
	
	public float getStrength() {
		return strength;
	}
	public void setStrength(float strength) {
		this.strength = strength;
	}
	
	public float getDurCapacity() {
		return durCapacity;
	}
	public void setDurCapacity(float durCapacity) {
		this.durCapacity = durCapacity;
	}
	
	public float getChassisMass() {
		return chassisMass;
	}
	public void setChassisMass(float chassisMass) {
		this.chassisMass = chassisMass;
	}

}
