package com.pverge.core.socket.dataobjects;

import java.util.List;

import com.pverge.core.socket.dataobjects.SIOMatchObjects.NumberPlate;

/**
 * Socket-IO - Collection of various data objects (Common race obkects)
 * @author Hypernucle
 */
public class SIORaceCommonObjects {

	public static class SteeringAttrs {
		private int attr; 
		private int modType; 
		private double val; 

		public int getAttr() {
			return attr; 
		}
		public void setAttr(int input) {
			this.attr = input;
		}
		
		public int getModType() {
			return modType; 
		}
		public void setModType(int input) {
			this.modType = input;
		}
		
		public double getVal() {
			return val; 
		}
		public void setVal(double input) {
			this.val = input;
		}
	}
	
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

	public static class AppearanceInfo {
		private ColorData colorData; 
		private int wheelId; 
		private int wrapId; 
		private NumberPlate plate; 

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
		
		public NumberPlate getPlate() {
			return plate; 
		}
		public void setPlate(NumberPlate input) {
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
		private List<SteeringAttrs> steeringAttrs; 
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
		
		public List<SteeringAttrs> getSteeringAttrs() {
			return steeringAttrs; 
		}
		public void setSteeringAttrs(List<SteeringAttrs> input) {
			this.steeringAttrs = input;
		}
		
		public String getVid() {
			return vid; 
		}
		public void setVid(String input) {
			this.vid = input;
		}
	}
}
