package com.pverge.core.socket.dataobjects;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Socket-IO - Collection of various data objects (Vehicle asset)
 * @author Hypernucle
 */
public class SIOAssetVehicleObjects {
	
	public static class Steering {
		private int code; 
		private int val; 

		public int getCode(){
			return code; 
		}
		public void setCode(int input){
			this.code = input;
		}
		public int getVal(){
			return val; 
		}
		public void setVal(int input){
			this.val = input;
		}
	}
	
	public static class SteeringOpts {
		private List<Steering> steering; 

		@JsonProperty("steering")
		public List<Steering> getSteering(){
			return steering; 
		}
		public void setSteering(List<Steering> input){
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

		public int getTopSpeed(){ 
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
	
	public static class AssetBody {
		private String embededId; 
		private String pid; 
		private int code; 
		private String createdat; 
		private String checkedat; 
		private int usage; 
		private int __v; 
		private List<Steering> steering; 
		private int depotStatus; 
		private Paint paint; 
		private Parts parts; 
		private boolean favorite; 
		private int grade; 
		private String id; 
		private Status status; 
		private int ovr; 
		private String clazz; 

		public String getEmbededId() {
			return embededId; 
		}
		public void setEmbededId(String input) {
			this.embededId = input;
		}
		
		public String getPid() {
			return pid; 
		}
		public void setPid(String input) {
			this.pid = input;
		}
		
		public int getCode() {
			return code; 
		}
		public void setCode(int input) {
			this.code = input;
		}
		
		public String getCreatedat() {
			return createdat; 
		}
		public void setCreatedat(String input) {
			this.createdat = input;
		}
		
		public String getCheckedat() {
			return checkedat; 
		}
		public void setCheckedat(String input) {
			this.checkedat = input;
		}
		
		public int getUsage() {
			return usage; 
		}
		public void setUsage(int input) {
			this.usage = input;
		}
		
		public int getV() {
			return __v; 
		}
		public void setV(int input) {
			this.__v = input;
		}
		
		public List<Steering> getSteering() {
			return steering; 
		}
		public void setSteering(List<Steering> input) {
			this.steering = input;
		}
		
		public int getDepotStatus() {
			return depotStatus; 
		}
		public void setDepotStatus(int input) {
			this.depotStatus = input;
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
		
		public boolean getFavorite() {
			return favorite; 
		}
		public void setFavorite(boolean input) {
			this.favorite = input;
		}
		
		public int getGrade() {
			return grade; 
		}
		public void setGrade(int input) {
			this.grade = input;
		}
		
		public String getId() {
			return id; 
		}
		public void setId(String input) {
			this.id = input;
		}
		
		public Status getStatus() {
			return status; 
		}
		public void setStatus(Status input) {
			this.status = input;
		}
		
		public int getOvr() {
			return ovr; 
		}
		public void setOvr(int input) {
			this.ovr = input;
		}
		
		public String getClazz() {
			return clazz; 
		}
		public void setClazz(String input) {
			this.clazz = input;
		}
	}

}
