package com.pverge.core.socket.dataobjects;

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

}
