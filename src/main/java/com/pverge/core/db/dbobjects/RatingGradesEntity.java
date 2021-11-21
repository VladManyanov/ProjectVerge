package com.pverge.core.db.dbobjects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "RATINGGRADES")
@NamedQueries({ 
	@NamedQuery(name = "RatingGradesEntity.findGrade", query = "SELECT obj FROM RatingGradesEntity obj WHERE obj.vcode = :vcode AND obj.grade = :grade") //
})
public class RatingGradesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int vcode;
	private int grade;
	private int topSpeed;
	private int acceleration;
	private int nitroCapacity;
	private int strength;
	private int durability;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getVcode() {
		return vcode;
	}
	public void setVcode(int vcode) {
		this.vcode = vcode;
	}
	
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
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

}
