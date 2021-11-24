package com.pverge.core.socket.dataobjects;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Socket-IO - Collection of various data objects
 * @author Hypernucle
 */
public class SIODataObjects {
	
	// Auth input
	public static class IOAuthInput {
	    private boolean result;
	    private String token;

	    public boolean getResult() {
	        return result;
	    }
	    public void setResult(boolean result) {
	        this.result = result;
	    }
	    
	    public String getToken() {
	        return token;
	    }
	    public void setToken(String token) {
	        this.token = token;
	    }
	}
	
	// Auth output
	public static class IOAuthOutput {
	    private boolean result;
	    
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
	
	public static class MessageDataObject {
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
	
	public static class MessageDataIntObject {
		private String uri; 
		private int body; 

		public String getUri() {
			return uri; 
		}
		public void setUri(String input) {
			this.uri = input;
		}
		
		public int getBody() {
			return body; 
		}
		public void setBody(int input) {
			this.body = input;
		}
	}
	
	public static class MessageDataStringObject {
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
	
	public static class ResourceListDataObject {
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
	
	// Match start socket command
	public static class ResourceDataObject {
		private String cmd; 
		private Object opts; 

		@JsonProperty("cmd")
		public String getCmd() {
			return cmd;
		}
		public void setCmd(String value) {
			this.cmd = value;
		}

		@JsonProperty("opts")
		public Object getOpts() {
			return opts;
		}
		public void setOpts(Object value) {
			this.opts = value;
		}
	}
	
	public static class Observers {
		// TODO Get field data example
	}

}
