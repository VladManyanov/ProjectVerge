package com.pverge.core.api;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Generic unresolved API calls should be logged
 * @author Hypernucle
 */
@Path("/")
public class APIGeneric {
	// TODO Make it detect the unknown requests from the game client (currently only web requests are detected)

	@GET
	@Path("{path:.*}")
	@Produces(MediaType.APPLICATION_JSON)
	public String genericUnknownGet(@PathParam("path") String path) {
		System.out.println("??? [API] Unknown REST GET request: " + path);
		return "";
	}

	@POST
	@Path("{path:.*}")
	@Produces(MediaType.APPLICATION_JSON)
	public String genericUnknownPost(@PathParam("path") String path) {
		System.out.println("??? [API] Unknown REST POST request: " + path);
		return "";
	}

	@PUT
	@Path("{path:.*}")
	@Produces(MediaType.APPLICATION_JSON)
	public String genericUnknownPut(@PathParam("path") String path) {
		System.out.println("??? [API] Unknown REST PUT request: " + path);
		return "";
	}
	
	@DELETE
	@Path("{path:.*}")
	@Produces(MediaType.APPLICATION_JSON)
	public String genericUnknownDelete(@PathParam("path") String path) {
		System.out.println("??? [API] Unknown REST DELETE request: " + path);
		return "";
	}

}