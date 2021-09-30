package com.pverge.core.socket.dataobjects;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Socket-IO - Collection of various data objects (Time Trial)
 * @author Hypernucle
 */
public class SIOTimeTrialObjects {

	public static class Attrs {
		private String attr; 
		private String type; 
		private float val; 

		public String getAttr() {
			return attr; 
		}
		public void setAttr(String input) {
			this.attr = input;
		}
		
		public String getType() {
			return type; 
		}
		public void setType(String input) {
			this.type = input;
		}
		
		public float getVal() {
			return val; 
		}
		public void setVal(float input) {
			this.val = input;
		}
	}
	
	public static class AttrsGraph {
		private String attr; 
		private String type; 
		private List<GraphValue> val; 

		public String getAttr() {
			return attr; 
		}
		public void setAttr(String input) {
			this.attr = input;
		}
		
		public String getType() {
			return type; 
		}
		public void setType(String input) {
			this.type = input;
		}
		
		public List<GraphValue> getVal() {
			return val; 
		}
		public void setVal(List<GraphValue> input) {
			this.val = input;
		}
	}
	
	public static class GraphValue {
		private float x; 
		private float y; 
		private float z; 

		public float getX() {
			return x; 
		}
		public void setX(float input) {
			this.x = input;
		}
		
		public float getY() {
			return y; 
		}
		public void setY(float input) {
			this.y = input;
		}
		
		public float getZ() {
			return z; 
		}
		public void setZ(float input) {
			this.z = input;
		}
	}
	
	public static class Rgb {
		private String solid; 
		private String secondary; 

		public String getSolid() {
			return solid; 
		}
		public void setSolid(String input) {
			this.solid = input;
		}
		
		public String getSecondary() {
			return secondary; 
		}
		public void setSecondary(String input) {
			this.secondary = input;
		}
	}
	
	public static class ColorData {
		private Rgb rgb; 

		public Rgb getRgb() {
			return rgb; 
		}
		public void setRgb(Rgb input) {
			this.rgb = input;
		}
	}
	public static class Plate {
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
	public static class AppearanceInfo {
		private ColorData colorData; 
		private int wheelId; 
		private int wrapId; 
		private Plate plate; 

		public ColorData getColorData() {
			return colorData; 
		}
		public void setColorData(ColorData input) {
			this.colorData = input;
		}
		
		public int getWheelId() {
			return wheelId; 
		}
		public void setWheelId(int input) {
			this.wheelId = input;
		}
		
		public int getWrapId() {
			return wrapId; 
		}
		public void setWrapId(int input) {
			this.wrapId = input;
		}
		
		public Plate getPlate() {
			return plate; 
		}
		public void setPlate(Plate input) {
			this.plate = input;
		}
	}

	public static class Clients {
		private String playerid; 
		private String accountid; 
		private int vcode; 
		private List<Object> attrs; 
		private boolean ai; 
		private int team; 
		private int positionOnStartingGrid; 
		private AppearanceInfo appearanceInfo; 
		private String vid; 

		public String getPlayerId() {
			return playerid; 
		}
		public void setPlayerId(String input) {
			this.playerid = input;
		}
		
		public String getAccountId() {
			return accountid; 
		}
		public void setAccountId(String input) {
			this.accountid = input;
		}
		
		public int getVCode() {
			return vcode; 
		}
		public void setVCode(int input) {
			this.vcode = input;
		}
		
		public List<Object> getAttrs() {
			return attrs; 
		}
		public void setAttrs(List<Object> input) {
			this.attrs = input;
		}
		
		public boolean getAI() {
			return ai; 
		}
		public void setAI(boolean input) {
			this.ai = input;
		}
		
		public int getTeam() {
			return team; 
		}
		public void setTeam(int input) {
			this.team = input;
		}
		
		public int getPositionOnStartingGrid() {
			return positionOnStartingGrid; 
		}
		public void setPositionOnStartingGrid(int input) {
			this.positionOnStartingGrid = input;
		}
		
		public AppearanceInfo getAppearanceInfo() {
			return appearanceInfo; 
		}
		public void setAppearanceInfo(AppearanceInfo input) {
			this.appearanceInfo = input;
		}
		
		public String getVid() {
			return vid; 
		}
		public void setVid(String input) {
			this.vid = input;
		}
	}
	
	public static class Observers {
		// TODO Get field data example
	}

	public static class TTOpts {
		private int matchId; 
		private String creator; 
		private String coreGameModeSchematic;
		private String level;
		private int laps;
		private boolean isManagerMode;
		private List<Clients> clients; 
		private List<Observers> observers; 

		public int getMatchId() {
			return matchId;
		}
		public void setMatchId(int value) {
			this.matchId = value;
		}

		public String getCreator() {
			return creator;
		}
		public void setCreator(String value) {
			this.creator = value;
		}

		public String getCoreGameModeSchematic() {
			return coreGameModeSchematic;
		}
		public void setCoreGameModeSchematic(String value) {
			this.coreGameModeSchematic = value;
		}

		public String getLevel() {
			return level;
		}
		public void setLevel(String value) {
			this.level = value;
		}

		public int getLaps() {
			return laps;}
		public void setLaps(int value) {
			this.laps = value;
		}

		public boolean getManagerMode() {
			return isManagerMode;
		}
		public void setManagerMode(boolean value) {
			this.isManagerMode = value;
		}

		public List<Clients> getClients() {
			return clients;
		}
		public void setClients(List<Clients> value) {
			this.clients = value;
		}

		public List<Observers> getObservers() {
			return observers;
		}
		public void setObservers(List<Observers> value) {
			this.observers = value;
		}
	}

	// Time Trial start socket command
	public static class TTRootObject{
		private String cmd; 
		private TTOpts ttOpts; 

		@JsonProperty("cmd")
		public String getCmd() {
			return cmd;
		}
		public void setCmd(String value) {
			this.cmd = value;
		}

		@JsonProperty("opts")
		public TTOpts getTTOpts() {
			return ttOpts;
		}
		public void setTTOpts(TTOpts value) {
			this.ttOpts = value;
		}
	}
}
