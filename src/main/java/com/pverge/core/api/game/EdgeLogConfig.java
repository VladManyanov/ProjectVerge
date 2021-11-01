package com.pverge.core.api.game;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Edge - LogApiServer requests
 * Majority of requests contains some unknown path value, example: _=1632169536992
 * @author Hypernucle
 */
@Path("logs/v2")
public class EdgeLogConfig {
	
	@Context
	private HttpServletRequest sr;
	
	private static String forcePlayerId = "33";
	
	/**
	 * TODO Not researched
	 * Log config request
	 * @return Unknown
	 */
	@GET
	@Path("logs/log/config")
	@Produces(MediaType.APPLICATION_JSON)
	public Response apiLogConfig(@HeaderParam("_") String someValue) {
		System.out.println("### [LogConfig] Logs configuration request from player ID " + forcePlayerId + ".");
	    return Response.ok().build();
	}
	
	/**
	 * PVerge logs collector
	 */
	@GET
	@Path("acceptlogs")
	@Produces(MediaType.APPLICATION_JSON)
	public void debugLogsGet(@HeaderParam("_") String someValue) {
		System.out.println("### [DebugLog] GET, OrigURL: " + sr.getHeader("OrigURL") +
				" , response: " + sr.getHeader("OrigResp"));
	}
	
	/**
	 * PVerge logs collector
	 */
	@POST
	@Path("acceptlogs")
	@Produces(MediaType.APPLICATION_JSON)
	public void debugLogsPost(@HeaderParam("_") String someValue) {
		System.out.println("### [DebugLog] POST, OrigURL: " + sr.getHeader("OrigURL") + 
				" , parameters: " + sr.getHeader("OrigParams") + " , response: " + sr.getHeader("OrigResp"));
	}
	
	/**
	 * PVerge logs collector
	 */
	@PUT
	@Path("acceptlogs")
	@Produces(MediaType.APPLICATION_JSON)
	public void debugLogsPut(@HeaderParam("_") String someValue) {
		System.out.println("### [DebugLog] PUT, OrigURL: " + sr.getHeader("OrigURL") + 
				" , parameters: " + sr.getHeader("OrigParams") + " , response: " + sr.getHeader("OrigResp"));
	}
	
	/**
	 * PVerge logs collector
	 */
	@DELETE
	@Path("acceptlogs")
	@Produces(MediaType.APPLICATION_JSON)
	public void debugLogsDelete(@HeaderParam("_") String someValue) {
		System.out.println("### [DebugLog] DELETE, OrigURL: " + sr.getHeader("OrigURL") + 
				" , parameters: " +  sr.getHeader("OrigParams") + " , response: " + sr.getHeader("OrigResp"));
	}

}