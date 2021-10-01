package com.pverge.core.socket.dataobjects;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Socket-IO - Collection of various data objects
 * @author Hypernucle
 */
public class SIODataObjects {
	
	// Auth
	public static class IOAuthObject {
	    private boolean result;
	    public IOAuthObject() {
	    }

	    public IOAuthObject(boolean result) {
	        super();
	        this.result = result;
	    }

	    public boolean getResult() {
	        return result;
	    }
	    public void setResult(boolean result) {
	        this.result = result;
	    }
	}
	
	public static class OWJoinChannelObj{
		private int openworldId; 

		public int getOpenworldId() {
			return openworldId; 
		}
		public void setOpenworldId(int input) {
			this.openworldId = input;
		}
	}
	
	public static class OWJoinOpts{
		private String uri; 
		private OWJoinChannelObj body; 

		public String getUri() {
			return uri; 
		}
		public void setUri(String input) {
			this.uri = input;
		}
		
		public OWJoinChannelObj getBody() {
			return body; 
		}
		public void setBody(OWJoinChannelObj input) {
			this.body = input;
		}
	}
	
	public static class RecentOpts{
		private String uri; 
		private String body; 

		public String getUri() {
			return uri; 
		}
		public void setUri(String input) {
			this.uri = input;
		}
		
		public String getBody() {
			return body; 
		}
		public void setBody(String input) {
			this.body = input;
		}
	}
	
	public static class ResourceDataObject {
		private String cmd; 
		private List<Object> opts; 

		@JsonProperty("cmd")
		public String getCmd() {
			return cmd; 
		}
		public void setCmd(String input) {
			this.cmd = input;
		}
		
		@JsonProperty("opts")
		public List<Object> getOpts() {
			return opts; 
		}
		public void setOpts(List<Object> input) {
			this.opts = input;
		}
	}

}
