package com.pverge.core.socket.dataobjects;

import java.util.List;

/**
 * Socket-IO - Collection of various data objects (Players)
 * @author Hypernucle
 */
public class SIOPlayerObjects {
	
	public static class GameSetting{
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

		public int getMinimapPosition(){
			return minimapPosition; 
		}
		public void setMinimapPosition(int input){
			this.minimapPosition = input;
		}
		
		public boolean getRoomMirrorOff(){
			return roomMirrorOff; 
		}
		public void setRoomMirrorOff(boolean input){
			this.roomMirrorOff = input;
		}
		
		public boolean getUseHcs(){
			return useHcs; 
		}
		public void setUseHcs(boolean input){
			this.useHcs = input;
		}
		
		public boolean getUseEsc(){
			return useEsc; 
		}
		public void setUseEsc(boolean input){
			this.useEsc = input;
		}
		
		public boolean getUseAbs(){
			return useAbs; 
		}
		public void setUseAbs(boolean input){
			this.useAbs = input;
		}
		
		public boolean getActionFeedbackOn(){
			return actionFeedbackOn; 
		}
		public void setActionFeedbackOn(boolean input){
			this.actionFeedbackOn = input;
		}
		
		public boolean getKeyGuideOn(){
			return keyGuideOn; 
		}
		public void setKeyGuideOn(boolean input){
			this.keyGuideOn = input;
		}
		
		public int getVehicleCameraMode(){
			return vehicleCameraMode; 
		}
		public void setVehicleCameraMode(int input){
			this.vehicleCameraMode = input;
		}
		
		public boolean getChatOn(){
			return chatOn; 
		}
		public void setChatOn(boolean input){
			this.chatOn = input;
		}
		
		public boolean getFlevron(){
			return flevron; 
		}
		public void setFlevron(boolean input){
			this.flevron = input;
		}
	}
	
	public static class InputKey{
		private List<Integer> toggleWorldmap; 
		private List<Integer> useItem2; 
		private List<Integer> secondBrake; 
		private List<Integer> throttle; 
		private List<Integer> brake; 
		private List<Integer> steeringLeft; 
		private List<Integer> steeringRight; 
		private List<Integer> nitro; 
		private List<Integer> handBrake; 
		private List<Integer> useItem; 
		private List<Integer> reset; 
		private List<Integer> rearView; 
		private List<Integer> leftView; 
		private List<Integer> rightView; 
		private List<Integer> horn; 
		private List<Integer> toggleCamera; 
		private List<Integer> toggleMinimap;
		
		public List<Integer> getToggleWorldmap() {
			return toggleWorldmap;
		}
		public void setToggleWorldmap(List<Integer> input) {
			this.toggleWorldmap = input;
		}
		
		public List<Integer> getUseItem2() {
			return useItem2;
		}
		public void setUseItem2(List<Integer> input) {
			this.useItem2 = input;
		}
		
		public List<Integer> getSecondBrake() {
			return secondBrake;
		}
		public void setSecondBrake(List<Integer> input) {
			this.secondBrake = input;
		}
		
		public List<Integer> getThrottle() {
			return throttle;
		}
		public void setThrottle(List<Integer> input) {
			this.throttle = input;
		}
		
		public List<Integer> getBrake() {
			return brake;
		}
		public void setBrake(List<Integer> input) {
			this.brake = input;
		}
		
		public List<Integer> getSteeringLeft() {
			return steeringLeft;
		}
		public void setSteeringLeft(List<Integer> input) {
			this.steeringLeft = input;
		}
		
		public List<Integer> getSteeringRight() {
			return steeringRight;
		}
		public void setSteeringRight(List<Integer> input) {
			this.steeringRight = input;
		}
		
		public List<Integer> getNitro() {
			return nitro;
		}
		public void setNitro(List<Integer> input) {
			this.nitro = input;
		}
		
		public List<Integer> getHandBrake() {
			return handBrake;
		}
		public void setHandBrake(List<Integer> input) {
			this.handBrake = input;
		}
		
		public List<Integer> getUseItem() {
			return useItem;
		}
		public void setUseItem(List<Integer> input) {
			this.useItem = input;
		}
		
		public List<Integer> getReset() {
			return reset;
		}
		public void setReset(List<Integer> input) {
			this.reset = input;
		}
		
		public List<Integer> getRearView() {
			return rearView;
		}
		public void setRearView(List<Integer> input) {
			this.rearView = input;
		}
		
		public List<Integer> getLeftView() {
			return leftView;
		}
		public void setLeftView(List<Integer> input) {
			this.leftView = input;
		}
		
		public List<Integer> getRightView() {
			return rightView;
		}
		public void setRightView(List<Integer> input) {
			this.rightView = input;
		}
		
		public List<Integer> getHorn() {
			return horn;
		}
		public void setHorn(List<Integer> input) {
			this.horn = input;
		}
		
		public List<Integer> getToggleCamera() {
			return toggleCamera;
		}
		public void setToggleCamera(List<Integer> input) {
			this.toggleCamera = input;
		}
		
		public List<Integer> getToggleMinimap() {
			return toggleMinimap;
		}
		public void setToggleMinimap(List<Integer> input) {
			this.toggleMinimap = input;
		} 
	}
	
	public static class PlayerConfig{
		private int version; 
		private InputKey inputKey; 
		private GameSetting gameSetting; 
		private int __v; 
		private String id; 

		public int getVersion(){
			return version; 
		}
		public void setVersion(int input){
			this.version = input;
		}
		
		public InputKey getInputKey(){
			return inputKey; 
		}
		public void setInputKey(InputKey input){
			this.inputKey = input;
		}
		
		public GameSetting getGameSetting(){
			return gameSetting; 
		}
		public void setGameSetting(GameSetting input){
			this.gameSetting = input;
		}
		
		public int getV(){
			return __v; 
		}
		public void setV(int input){
			this.__v = input;
		}
		
		public String getId(){
			return id; 
		}
		public void setId(String input){
			this.id = input;
		}
	}
}
