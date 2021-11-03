package com.pverge.core.db.dbobjects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "PLAYERSETTINGS")
@NamedQueries({ 
	@NamedQuery(name = "PlayerSettingsEntity.findByPid", query = "SELECT obj FROM PlayerSettingsEntity obj WHERE obj.pid = :pid"), //
	@NamedQuery(name = "PlayerSettingsEntity.setSettings", query = "UPDATE PlayerSettingsEntity obj SET "
			+ "obj.minimapPosition = :minimapPosition, obj.roomMirrorOff = :roomMirrorOff, obj.useHcs = :useHcs, "
			+ "obj.useEsc = :useEsc, obj.useAbs = :useAbs, obj.actionFeedbackOn = :actionFeedbackOn, obj.keyGuideOn = :keyGuideOn, "
			+ "obj.vehicleCameraMode = :vehicleCameraMode, obj.chatOn = :chatOn, obj.flevron = :flevron, "
			+ "obj.toggleWorldmap = :toggleWorldmap, obj.useItem2 = :useItem2, obj.secondBrake = :secondBrake, "
			+ "obj.throttle = :throttle, obj.brake = :brake, obj.steeringLeft = :steeringLeft, obj.steeringRight = :steeringRight, "
			+ "obj.nitro = :nitro, obj.handBrake = :handBrake, obj.useItem = :useItem, obj.reset = :reset, "
			+ "obj.rearView = :rearView, obj.leftView = :leftView, obj.rightView = :rightView, obj.horn = :horn, "
			+ "obj.toggleCamera = :toggleCamera, obj.toggleMinimap = :toggleMinimap WHERE obj.pid = :pid"), //
})
public class PlayerSettingsEntity {

	@Id
	private int pid;

	// Input
	private String toggleMinimap;
	private String toggleCamera;
	private String horn;
	private String rightView;
	private String leftView;
	private String rearView;
	private String reset;
	private String useItem;
	private String handBrake;
	private String nitro;
	private String steeringRight;
	private String steeringLeft;
	private String brake;
	private String throttle;
	private String secondBrake;
	private String useItem2;
	private String toggleWorldmap;
	// Settings
	private int minimapPosition;
	private boolean roomMirrorOff;
	private boolean useHcs;
	private boolean useEsc;
	private boolean useAbs;
	private boolean actionFeedbackOn;
	private boolean keyGuideOn;
	private int vehicleCameraMode;
	private boolean chatOn;
	private boolean flevron;

	public String getPid() {
		return String.valueOf(pid);
	}
	public void setPid(String pid) {
		this.pid = Integer.parseInt(pid);
	}
	
	public String[] getToggleMinimap() {
		return toggleMinimap.split(",");
	}
	public void setToggleMinimap(String toggleMinimap) {
		this.toggleMinimap = String.join(",", toggleMinimap);
	}
	
	public String[] getToggleCamera() {
		return toggleCamera.split(",");
	}
	public void setToggleCamera(String toggleCamera) {
		this.toggleCamera = String.join(",", toggleCamera);
	}
	
	public String[] getHorn() {
		return horn.split(",");
	}
	public void setHorn(String horn) {
		this.horn = String.join(",", horn);
	}
	
	public String[] getRightView() {
		return rightView.split(",");
	}
	public void setRightView(String rightView) {
		this.rightView = String.join(",", rightView);
	}
	
	public String[] getLeftView() {
		return leftView.split(",");
	}
	public void setLeftView(String leftView) {
		this.leftView = String.join(",", leftView);
	}
	
	public String[] getRearView() {
		return rearView.split(",");
	}
	public void setRearView(String rearView) {
		this.rearView = String.join(",", rearView);
	}
	
	public String[] getReset() {
		return reset.split(",");
	}
	public void setReset(String reset) {
		this.reset = String.join(",", reset);
	}
	
	public String[] getUseItem() {
		return useItem.split(",");
	}
	public void setUseItem(String useItem) {
		this.useItem = String.join(",", useItem);
	}
	
	public String[] getHandBrake() {
		return handBrake.split(",");
	}
	public void setHandBrake(String handBrake) {
		this.handBrake = String.join(",", handBrake);
	}
	
	public String[] getNitro() {
		return nitro.split(",");
	}
	public void setNitro(String nitro) {
		this.nitro = String.join(",", nitro);
	}
	
	public String[] getSteeringRight() {
		return steeringRight.split(",");
	}
	public void setSteeringRight(String steeringRight) {
		this.steeringRight = String.join(",", steeringRight);
	}
	
	public String[] getSteeringLeft() {
		return steeringLeft.split(",");
	}
	public void setSteeringLeft(String steeringLeft) {
		this.steeringLeft = String.join(",", steeringLeft);
	}
	
	public String[] getBrake() {
		return brake.split(",");
	}
	public void setBrake(String brake) {
		this.brake = String.join(",", brake);
	}
	
	public String[] getThrottle() {
		return throttle.split(",");
	}
	public void setThrottle(String throttle) {
		this.throttle = String.join(",", throttle);
	}
	
	public String[] getSecondBrake() {
		return secondBrake.split(",");
	}
	public void setSecondBrake(String secondBrake) {
		this.secondBrake = String.join(",", secondBrake);
	}
	
	public String[] getUseItem2() {
		return useItem2.split(",");
	}
	public void setUseItem2(String useItem2) {
		this.useItem2 = String.join(",", useItem2);
	}
	
	public String[] getToggleWorldmap() {
		return toggleWorldmap.split(",");
	}
	public void setToggleWorldmap(String toggleWorldmap) {
		this.toggleWorldmap = String.join(",", toggleWorldmap);
	}
	
	public int getMinimapPosition() {
		return minimapPosition;
	}
	public void setMinimapPosition(int minimapPosition) {
		this.minimapPosition = minimapPosition;
	}
	
	public boolean isRoomMirrorOff() {
		return roomMirrorOff;
	}
	public void setRoomMirrorOff(boolean roomMirrorOff) {
		this.roomMirrorOff = roomMirrorOff;
	}
	
	public boolean isUseHcs() {
		return useHcs;
	}
	public void setUseHcs(boolean useHcs) {
		this.useHcs = useHcs;
	}
	
	public boolean isUseEsc() {
		return useEsc;
	}
	public void setUseEsc(boolean useEsc) {
		this.useEsc = useEsc;
	}
	
	public boolean isUseAbs() {
		return useAbs;
	}
	public void setUseAbs(boolean useAbs) {
		this.useAbs = useAbs;
	}
	
	public boolean isActionFeedbackOn() {
		return actionFeedbackOn;
	}
	public void setActionFeedbackOn(boolean actionFeedbackOn) {
		this.actionFeedbackOn = actionFeedbackOn;
	}
	
	public boolean isKeyGuideOn() {
		return keyGuideOn;
	}
	public void setKeyGuideOn(boolean keyGuideOn) {
		this.keyGuideOn = keyGuideOn;
	}
	
	public int getVehicleCameraMode() {
		return vehicleCameraMode;
	}
	public void setVehicleCameraMode(int vehicleCameraMode) {
		this.vehicleCameraMode = vehicleCameraMode;
	}
	
	public boolean isChatOn() {
		return chatOn;
	}
	public void setChatOn(boolean chatOn) {
		this.chatOn = chatOn;
	}
	
	public boolean isFlevron() {
		return flevron;
	}
	public void setFlevron(boolean flevron) {
		this.flevron = flevron;
	}

}
