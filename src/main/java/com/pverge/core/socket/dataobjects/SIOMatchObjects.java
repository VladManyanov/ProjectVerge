package com.pverge.core.socket.dataobjects;

import java.util.List;

import com.pverge.core.socket.dataobjects.SIOAssetVehicleObjects.Steering;
import com.pverge.core.socket.dataobjects.SIODataObjects.Observers;

/**
 * Socket-IO - Collection of various data objects (Race Matches)
 * @author Hypernucle
 */
public class SIOMatchObjects {

	public static class MatchPlayer {
		private String pid; 
		private String sid; 
		private int rank; 
		private boolean retire; 
		private String channel; 
		private int team; 
		private Vehicle vehicle; 
		private boolean forceLeave; 
		private int abuseStatus; 

		public String getPid() {
			return pid; 
		}
		public void setPid(String input) {
			this.pid = input;
		}
		
		public String getSid() {
			return sid; 
		}
		public void setSid(String input) {
			this.sid = input;
		}
		
		public int getRank() {
			return rank; 
		}
		public void setRank(int input) {
			this.rank = input;
		}
		
		public boolean getRetire() {
			return retire; 
		}
		public void setRetire(boolean input) {
			this.retire = input;
		}
		
		public String getChannel() {
			return channel; 
		}
		public void setChannel(String input) {
			this.channel = input;
		}
		
		public int getTeam() {
			return team; 
		}
		public void setTeam(int input) {
			this.team = input;
		}
		
		public Vehicle getVehicle() {
			return vehicle; 
		}
		public void setVehicle(Vehicle input) {
			this.vehicle = input;
		}
		
		public boolean getForceLeave() {
			return forceLeave; 
		}
		public void setForceLeave(boolean input) {
			this.forceLeave = input;
		}
		
		public int getAbuseStatus() {
			return abuseStatus; 
		}
		public void setAbuseStatus(int input) {
			this.abuseStatus = input;
		}
	}
	
	public static class MatchEnd {
		private int id; 
		private int trackCode; 
		private String gameMode; 
		private List<MatchPlayer> players; 
		private int matchEndAt; 

		public int getId() {
			return id; 
		}
		public void setId(int input) {
			this.id = input;
		}
		
		public int getTrackCode() {
			return trackCode; 
		}
		public void setTrackCode(int input) {
			this.trackCode = input;
		}
		
		public String getGameMode() {
			return gameMode; 
		}
		public void setGameMode(String input) {
			this.gameMode = input;
		}
		
		public List<MatchPlayer> getPlayers() {
			return players; 
		}
		public void setPlayers(List<MatchPlayer> input) {
			this.players = input;
		}
		
		public int getMatchEndAt() {
			return matchEndAt; 
		}
		public void setMatchEndAt(int input) {
			this.matchEndAt = input;
		}
	}
	
	public static class NumberPlate {
		private String pid; 
		private String prefix; 
		private String plateNumber; 
		private int templateCode; 
		private int background; 
		private String fontColor; 

		public String getPid() {
			return pid; 
		}
		public void setPid(String input) {
			this.pid = input;
		}
		
		public String getPrefix() {
			return prefix; 
		}
		public void setPrefix(String input) {
			this.prefix = input;
		}
		
		public String getPlateNumber() {
			return plateNumber; 
		}
		public void setPlateNumber(String input) {
			this.plateNumber = input;
		}
		
		public int getTemplateCode() {
			return templateCode; 
		}
		public void setTemplateCode(int input) {
			this.templateCode = input;
		}
		
		public int getBackground() {
			return background; 
		}
		public void setBackground(int input) {
			this.background = input;
		}
		
		public String getFontColor() {
			return fontColor; 
		}
		public void setFontColor(String input) {
			this.fontColor = input;
		}
	}
	
	public static class MatchCreatedOpts {
		private int matchId; 
		private String gameMode; 
		private List<Players> players;
		private List<Integer> ais; 
		private List<Observers> observers; 
		private int trackCode;
		private boolean useDediSvr;
		private String schematic;

		public int getMatchId() {
			return matchId;
		}
		public void setMatchId(int value) {
			this.matchId = value;
		}

		public String getGameMode() {
			return gameMode;
		}
		public void setGameMode(String value) {
			this.gameMode = value;
		}

		public String getSchematic() {
			return schematic;
		}
		public void setSchematic(String value) {
			this.schematic = value;
		}

		public int getTrackCode() {
			return trackCode;
		}
		public void setTrackCode(int value) {
			this.trackCode = value;
		}

		public boolean getUseDediSvr() {
			return useDediSvr;
		}
		public void setUseDediSvr(boolean value) {
			this.useDediSvr = value;
		}

		public List<Players> getPlayers() {
			return players;
		}
		public void setPlayers(List<Players> value) {
			this.players = value;
		}

		public List<Observers> getObservers() {
			return observers;
		}
		public void setObservers(List<Observers> value) {
			this.observers = value;
		}
		
		public List<Integer> getAIs() {
			return ais;
		}
		public void setAIs(List<Integer> aisList) {
			this.ais = aisList;
		}
	}

	public static class AIs {}
	
	public static class Players {
		private String id; 
		private int team; 
		private Vehicle vehicle;
		private NumberPlate plate; 

		public String getId() {
			return id; 
		}
		public void setId(String input) {
			this.id = input;
		}
		
		public Vehicle getVehicle() {
			return vehicle; 
		}
		public void setVehicle(Vehicle input) {
			this.vehicle = input;
		}
		
		public NumberPlate getPlate() {
			return plate; 
		}
		public void setPlate(NumberPlate input) {
			this.plate = input;
		}
		
		public int getTeam() {
			return team; 
		}
		public void setTeam(int input) {
			this.team = input;
		}
	}
	
	public static class Vehicle {
		private String id; 
		private int code; 
		private int ovr; 
		private int grade; 
		private Paint paint; 
		private Parts parts; 
		private Status status; 
		private boolean igr; 
		private List<Steering> steering; 
		
		public String getId() {
			return id; 
		}
		public void setId(String input) {
			this.id = input;
		}
		
		public int getCode() {
			return code; 
		}
		public void setCode(int input) {
			this.code = input;
		}
		
		public int getOvr() {
			return ovr; 
		}
		public void setOvr(int input) {
			this.ovr = input;
		}
		
		public int getGrade() {
			return grade; 
		}
		public void setGrade(int input) {
			this.grade = input;
		}
		
		public boolean getIGR() {
			return igr; 
		}
		public void setIGR(boolean input) {
			this.igr = input;
		}
		
		public Paint getPaint() {
			return paint; 
		}
		public void setPaint(Paint input) {
			this.paint = input;
		}
		
		public Parts getParts() {
			return parts; 
		}
		public void setParts(Parts input) {
			this.parts = input;
		}
		
		public Status getStatus() {
			return status; 
		}
		public void setStatus(Status input) {
			this.status = input;
		}
		  
		public List<Steering> getSteering() {
			return steering; 
		}
		public void setSteering(List<Steering> input) {
			this.steering = input;
		}
	}
	
	public static class Paint {
		private int wheelCode; 
		private int wrapCode; 
		private int colorCode; 

		public int getWheelCode() {
			return wheelCode; 
		}
		public void setWheelCode(int input) {
			this.wheelCode = input;
		}
		
		public int getWrapCode() {
			return wrapCode; 
		}
		public void setWrapCode(int input) {
			this.wrapCode = input;
		}
		
		public int getColorCode() {
			return colorCode; 
		}
		public void setColorCode(int input) {
			this.colorCode = input;
		}
	}
	public static class Parts {
		private int frame; 
		private int bumper; 
		private int nitroTank; 
		private int transmission; 
		private int engine; 

		public int getFrame() {
			return frame; 
		}
		public void setFrame(int input) {
			this.frame = input;
		}
		
		public int getBumper() {
			return bumper; 
		}
		public void setBumper(int input) {
			this.bumper = input;
		}
		
		public int getNitroTank() {
			return nitroTank; 
		}
		public void setNitroTank(int input) {
			this.nitroTank = input;
		}
		
		public int getTransmission() {
			return transmission; 
		}
		public void setTransmission(int input) {
			this.transmission = input;
		}
		
		public int getEngine() {
			return engine; 
		}
		public void setEngine(int input) {
			this.engine = input;
		}
	}
	public static class Status {
		private int topSpeed; 
		private int acceleration; 
		private int nitroCapacity; 
		private int strength; 
		private int durability; 

		public int getTopSpeed() {
			return topSpeed; 
		}
		public void setTopSpeed(int input) {
			this.topSpeed = input;
		}
		
		public int getAcceleration() {
			return acceleration; 
		}
		public void setAcceleration(int input) {
			this.acceleration = input;
		}
		
		public int getNitroCapacity() {
			return nitroCapacity; 
		}
		public void setNitroCapacity(int input) {
			this.nitroCapacity = input;
		}
		
		public int getStrength() {
			return strength; 
		}
		public void setStrength(int input) {
			this.strength = input;
		}
		
		public int getDurability() {
			return durability; 
		}
		public void setDurability(int input) {
			this.durability = input;
		}
	}

}
