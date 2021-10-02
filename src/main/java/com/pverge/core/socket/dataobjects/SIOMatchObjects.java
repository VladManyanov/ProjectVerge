package com.pverge.core.socket.dataobjects;

/**
 * Socket-IO - Collection of various data objects (Race Matches)
 * @author Hypernucle
 */
public class SIOMatchObjects {

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

}
