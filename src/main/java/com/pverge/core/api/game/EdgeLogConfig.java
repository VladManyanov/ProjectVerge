package com.pverge.core.api.game;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Edge - LogApiServer requests
 * Majority of requests contains some unknown path value, example: _=1632169536992
 * @author Hypernucle
 */
@Path("logs/v2")
public class EdgeLogConfig {
	
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

}