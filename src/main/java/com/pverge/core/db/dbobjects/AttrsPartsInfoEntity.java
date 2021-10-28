package com.pverge.core.db.dbobjects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "ATTRSPARTSINFO")
@NamedQueries({ 
	@NamedQuery(name = "AttrsPartsInfoEntity.getPartInfo", query = "SELECT obj FROM AttrsPartsInfoEntity obj WHERE obj.partId = :partId"), //
	@NamedQuery(name = "AttrsPartsInfoEntity.findPart", query = "SELECT obj FROM AttrsPartsInfoEntity obj WHERE obj.partType = :partType AND obj.clazz = :clazz AND obj.partLevel = :partLevel") //
})
public class AttrsPartsInfoEntity {

	@Id
	private int partId;
	
	private String partType;
	private String clazz;
	private int partLevel;
	private int uninstallCount;
	
	private int ovrTopSpeed;
	private int ovrAcceleration;
	private int ovrNitroCapacity;
	private int ovrStrength;
	private int ovrDurability;
	
	public int getPartId() {
		return partId;
	}
	public void setPartId(int partId) {
		this.partId = partId;
	}
	
	public String getPartType() {
		return partType;
	}
	public void setPartType(String partType) {
		this.partType = partType;
	}
	
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	
	public int getPartLevel() {
		return partLevel;
	}
	public void setPartLevel(int partLevel) {
		this.partLevel = partLevel;
	}
	
	public int getUninstallCount() {
		return uninstallCount;
	}
	public void setUninstallCount(int uninstallCount) {
		this.uninstallCount = uninstallCount;
	}
	
	public int getOvrTopSpeed() {
		return ovrTopSpeed;
	}
	public void setOvrTopSpeed(int ovrTopSpeed) {
		this.ovrTopSpeed = ovrTopSpeed;
	}
	
	public int getOvrAcceleration() {
		return ovrAcceleration;
	}
	public void setOvrAcceleration(int ovrAcceleration) {
		this.ovrAcceleration = ovrAcceleration;
	}
	
	public int getOvrNitroCapacity() {
		return ovrNitroCapacity;
	}
	public void setOvrNitroCapacity(int ovrNitroCapacity) {
		this.ovrNitroCapacity = ovrNitroCapacity;
	}
	
	public int getOvrStrength() {
		return ovrStrength;
	}
	public void setOvrStrength(int ovrStrength) {
		this.ovrStrength = ovrStrength;
	}
	
	public int getOvrDurability() {
		return ovrDurability;
	}
	public void setOvrDurability(int ovrDurability) {
		this.ovrDurability = ovrDurability;
	}
	
}
