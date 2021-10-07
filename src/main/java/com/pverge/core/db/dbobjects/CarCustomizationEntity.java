package com.pverge.core.db.dbobjects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "CARCUSTOMIZATION")
@NamedQueries({ 
	@NamedQuery(name = "CarCustomizationEntity.getItemProperties", query = "SELECT obj FROM CarCustomizationEntity obj WHERE obj.code = :code"), //
	@NamedQuery(name = "CarCustomizationEntity.loadAllItems", query = "SELECT obj FROM CarCustomizationEntity obj") //
})
public class CarCustomizationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int code;
	private String rgbSolid;
	private String rgbSecondary;
	private String subType;
	private int cid;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
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
	
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}
	
	public int getCID() {
		return cid;
	}
	public void setCID(int cid) {
		this.cid = cid;
	}

}
