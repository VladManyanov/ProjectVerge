package com.pverge.core.db.dbobjects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "RATINGVEHICLES")
@NamedQueries({ 
	@NamedQuery(name = "RatingVehiclesEntity.findByCode", query = "SELECT obj FROM RatingVehiclesEntity obj WHERE obj.vcode = :vcode") //
})
public class RatingVehiclesEntity {

	@Id
	private int vcode;

	private int topSpeed;
	private int acceleration;
	private int nitroCapacity;
	private int strength;
	private int durability;
	private int ovrDefault;
	private String clazz;
	
	public int getVcode() {
		return vcode;
	}
	public void setVcode(int vcode) {
		this.vcode = vcode;
	}
	
	public int getTopSpeed() {
		return topSpeed;
	}
	public void setTopSpeed(int topSpeed) {
		this.topSpeed = topSpeed;
	}
	
	public int getAcceleration() {
		return acceleration;
	}
	public void setAcceleration(int acceleration) {
		this.acceleration = acceleration;
	}
	
	public int getNitroCapacity() {
		return nitroCapacity;
	}
	public void setNitroCapacity(int nitroCapacity) {
		this.nitroCapacity = nitroCapacity;
	}
	
	public int getStrength() {
		return strength;
	}
	public void setStrength(int strength) {
		this.strength = strength;
	}
	
	public int getDurability() {
		return durability;
	}
	public void setDurability(int durability) {
		this.durability = durability;
	}
	
	public int getOvrDefault() {
		return ovrDefault;
	}
	public void setOvrDefault(int ovrDefault) {
		this.ovrDefault = ovrDefault;
	}
	
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
}
