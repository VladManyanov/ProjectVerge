package com.pverge.core.db.dbobjects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "VEHICLES")
@NamedQueries({ 
	@NamedQuery(name = "PlayerVehicleEntity.getPlayerVehicles", query = "SELECT obj FROM PlayerVehicleEntity obj WHERE obj.pid = :pid"), //
	@NamedQuery(name = "PlayerVehicleEntity.getVehicleByVid", query = "SELECT obj FROM PlayerVehicleEntity obj WHERE obj.id = :id"), //
	@NamedQuery(name = "PlayerVehicleEntity.setCustomization", query = "UPDATE PlayerVehicleEntity obj SET obj.colorCode = :colorCode, obj.wheelColor = :wheelColor, obj.wrapCode = :wrapCode WHERE obj.id = :id") //
})
public class PlayerVehicleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String pid;
	private int vcode;
	private int colorCode;
	private int wrapCode;
	private int wheelColor;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	
	public int getVcode() {
		return vcode;
	}
	public void setVcode(int vcode) {
		this.vcode = vcode;
	}
	
	public int getColorCode() {
		return colorCode;
	}
	public void setColorCode(int colorCode) {
		this.colorCode = colorCode;
	}

	public int getWrapCode() {
		return wrapCode;
	}
	public void setWrapCode(int wrapCode) {
		this.wrapCode = wrapCode;
	}
	
	public int getWheelColor() {
		return wheelColor;
	}
	public void setWheelColor(int wheelColor) {
		this.wheelColor = wheelColor;
	}
}
