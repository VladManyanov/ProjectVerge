package com.pverge.core.socket.dataobjects;

/**
 * Socket-IO - Collection of Channel connection data objects
 * @author Hypernucle
 */
public class SIOChannelJoinObjects {
	
	public static class OWJoinChatChannelObj {
		private int channelCode; 

		public int getChannelCode() {
			return channelCode; 
		}
		public void setChannelCode(int input) {
			this.channelCode = input;
		}
	}
	
	public static class OWJoinChannelObj {
		private int openworldId; 

		public int getOpenworldId() {
			return openworldId; 
		}
		public void setOpenworldId(int input) {
			this.openworldId = input;
		}
	}
	
	public static class OWJoinOpts {
		private String uri; 
		private Object body; 

		public String getUri() {
			return uri; 
		}
		public void setUri(String input) {
			this.uri = input;
		}
		
		public Object getBody() {
			return body; 
		}
		public void setBody(Object input) {
			this.body = input;
		}
	}

}
