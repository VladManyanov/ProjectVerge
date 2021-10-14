package com.pverge.core.api.game;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.JsonArray;
import com.pverge.core.be.EdgePlayersBE;
import com.pverge.core.be.EdgeVehiclesBE;
import com.pverge.core.db.PlayerDBLoader;
import com.pverge.core.db.PlayerVehicleDBLoader;

/**
 * Edge - Decal editor requests. Only on Korean build
 * @author Hypernucle
 */
@Path("/v2")
public class EdgeDecal {

	@EJB
	private EdgePlayersBE edgePlayersBE;
	@EJB
	private EdgeVehiclesBE edgeVehiclesBE;
	@EJB
	private PlayerDBLoader playerDB;
	@EJB
	private PlayerVehicleDBLoader playerVehicleDB;
	
	private static String forcePlayerId = "33";
	// TODO 
	
	/**
	 * Save the vinyl. Needs proper data for client
	 * @return OK response
	 */
	@POST
	@Path("customdecal/{playerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response apiCustomDecalSave(String requestBody, @PathParam("playerId") String playerId) {
		System.out.println("### [Decal] Custom decal save request from player ID " + playerId + ".");
		System.out.println("### [TEST] " + requestBody);
	    return Response.ok().build();
	}
	
	/**
	 * Get saved player wraps
	 */
	@GET
	@Path("customdecal/{playerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiCustomDecalSave(@PathParam("playerId") String playerId, @QueryParam("start") int start, 
			@QueryParam("end") int end) {
		JsonArray rootArrayJson = new JsonArray();
		
		System.out.println("### [Decal] Player decals request from player ID " + playerId + ".");
	    return rootArrayJson.toString();
	}

}