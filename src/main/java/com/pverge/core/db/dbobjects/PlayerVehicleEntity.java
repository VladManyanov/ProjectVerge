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
	@NamedQuery(name = "PlayerVehicleEntity.getVehicleByVid", query = "SELECT obj FROM PlayerVehicleEntity obj WHERE obj.id = :id") //
})
public class PlayerVehicleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String pid;
	private int vcode;
	private String rgbSolid;
	private String rgbSecondary;

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
	
	public String getRGBSolid() {
		return rgbSolid;
	}
	public void setRGBSolid(String rgbSolid) {
		this.rgbSolid = rgbSolid;
	}
	
	public String getRGBSecondary() {
		return rgbSecondary;
	}
	public void setRGBSecondary(String rgbSecondary) {
		this.rgbSecondary = rgbSecondary;
	}

}
