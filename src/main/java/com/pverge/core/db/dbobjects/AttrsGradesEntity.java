package com.pverge.core.db.dbobjects;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "ATTRSGRADES")
@NamedQueries({ 
	@NamedQuery(name = "AttrsGradesEntity.getAttrsByGrade", query = "SELECT obj FROM AttrsGradesEntity obj WHERE obj.vcode = :vcode AND obj.grade = :grade") //
})
public class AttrsGradesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int vcode;
	private int grade;
	
	@ManyToOne
	@JoinColumn(name = "ATTRSTORQUEID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "ATTRSTORQUE_FK"))
	private AttrsTorqueEntity attrsTorque;
	
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
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getVCode() {
		return vcode;
	}
	public void setVCode(int vcode) {
		this.vcode = vcode;
	}
	
	public AttrsTorqueEntity getAttrsTorque() {
		return attrsTorque;
	}
	public void setAttrsTorque(AttrsTorqueEntity attrsTorque) {
		this.attrsTorque = attrsTorque;
	}
	
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
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
