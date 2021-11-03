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
@Table(name = "PLAYERS")
@NamedQueries({ 
	@NamedQuery(name = "PlayerEntity.getPlayer", query = "SELECT obj FROM PlayerEntity obj WHERE obj.pid = :pid"), //
	@NamedQuery(name = "PlayerEntity.findUsername", query = "SELECT obj FROM PlayerEntity obj WHERE obj.username = :username"), //
	@NamedQuery(name = "PlayerEntity.changeRecentVehicle", query = "UPDATE PlayerEntity obj SET obj.vid = :vid WHERE obj.pid = :pid"), //
	@NamedQuery(name = "PlayerEntity.setCampaignCode", query = "UPDATE PlayerEntity obj SET obj.campaignCode = :campaignCode WHERE obj.pid = :pid") //
})
public class PlayerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pid; // Game stores it as String

	@ManyToOne
	@JoinColumn(name = "GAMESETTINGS", referencedColumnName = "PID", foreignKey = @ForeignKey(name = "GAMESETTINGS_FK"))
	private PlayerSettingsEntity playerSettings;
	
	private int vid; // Game stores it as String
	private int campaignCode;
	private String username;
	
	public String getPid() {
		return String.valueOf(pid);
	}
	public void setPid(String pid) {
		this.pid = Integer.parseInt(pid);
	}
	
	public String getVid() {
		return String.valueOf(vid);
	}
	public void setVid(String vid) {
		this.vid = Integer.parseInt(vid);
	}
	
	public int getCampaignCode() {
		return campaignCode;
	}
	public void setCampaignCode(int campaignCode) {
		this.campaignCode = campaignCode;
	}
	
	public String getUserName() {
		return username;
	}
	public void setUserName(String username) {
		this.username = username;
	}
	
	public PlayerSettingsEntity getPlayerSettings() {
		return playerSettings;
	}
	public void setPlayerSettings(PlayerSettingsEntity playerSettings) {
		this.playerSettings = playerSettings;
	}

}
