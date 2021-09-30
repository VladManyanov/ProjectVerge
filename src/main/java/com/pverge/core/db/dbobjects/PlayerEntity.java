package com.pverge.core.db.dbobjects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "PLAYERS")
@NamedQueries({ 
	@NamedQuery(name = "PlayerEntity.getPlayer", query = "SELECT obj FROM PlayerEntity obj WHERE obj.pid = :pid"), //
	@NamedQuery(name = "PlayerEntity.changeRecentVehicle", query = "UPDATE PlayerEntity obj SET obj.vid = :vid WHERE obj.pid = :pid") //
})
public class PlayerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pid;

	private int vid;

	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	
	public int getVid() {
		return vid;
	}
	public void setVid(int vid) {
		this.vid = vid;
	}

}
