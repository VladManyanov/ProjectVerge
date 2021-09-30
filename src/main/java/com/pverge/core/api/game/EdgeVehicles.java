package com.pverge.core.api.game;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonArray;
import com.pverge.core.be.EdgePlayersBE;
import com.pverge.core.be.EdgeVehiclesBE;

/**
 * Edge - Vehicles requests & management
 * @author Hypernucle
 */
@Path("/v2")
public class EdgeVehicles {
	
	@EJB
	private EdgeVehiclesBE edgeVehiclesBE;
	
	EdgePlayersBE edgePlayersBE = new EdgePlayersBE();
	private static String forcePlayerId = "33";
	// TODO 
	
	/**
	 * Fetch player vehicles request (Array)
	 * @return Player vehicles
	 */
	@GET
	@Path("vehicles")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiVehicles(@HeaderParam("_") String someValue) {
		System.out.println("### [Vehicles] Player vehicles request from player ID " + forcePlayerId + ".");
	    return edgeVehiclesBE.loadPlayerVehicles(forcePlayerId);
	}
	
	/**
	 * IGR Vehicles request. Probably related to the Rental or Premium cars
	 */
	@GET
	@Path("igrvehicles")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiIGRVehicles(@HeaderParam("_") String someValue) {
		JsonArray rootArrayJson = new JsonArray();
		
		System.out.println("### [Vehicles] Player IGR vehicles request from player ID " + forcePlayerId + ".");
	    return rootArrayJson.toString();
	}
	
	
    
}