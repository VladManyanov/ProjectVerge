package com.pverge.core.api.game;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pverge.core.be.EdgePlayersBE;
import com.pverge.core.be.EdgeSocketVehiclesBE;
import com.pverge.core.be.EdgeVehiclesBE;
import com.pverge.core.db.PlayerVehicleDBLoader;
import com.pverge.core.db.VehicleSteeringDBLoader;
import com.pverge.core.db.dbobjects.PlayerVehicleEntity;
import com.pverge.core.db.dbobjects.VehicleSteeringEntity;
import com.pverge.core.socket.dataobjects.SIOAssetVehicleObjects.SteeringOpts;

/**
 * Edge - Vehicles requests & management
 * @author Hypernucle
 */
@Path("/v2")
public class EdgeVehicles {
	
	@EJB
	private EdgeVehiclesBE edgeVehiclesBE;
	@EJB
	private EdgePlayersBE edgePlayersBE;
	@EJB
	private EdgeSocketVehiclesBE edgeSocketVehiclesBE;
	@EJB
	private PlayerVehicleDBLoader playerVehicleDB;
	@EJB
	private VehicleSteeringDBLoader vehicleSteeringDB;
	
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
	
	/**
	 * Get Time Trial exclusive vehicles request (Array)
	 * @return Vehicles array
	 */
	@GET
	@Path("timetrial/exclusivevehicles")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiExclusiveVehicles(@HeaderParam("_") String someValue) {
		JsonArray rootArrayJson = new JsonArray();
		
		System.out.println("### [Vehicles] TT exclusive vehicles request from player ID " + forcePlayerId + ".");
	    return rootArrayJson.toString();
	}
	
	/**
	 * Save the custom steering vehicle settings
	 * @return Current updated vehicle
	 */
	@PUT
	@Path("vehicles/{vehicleId}/steering")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiVehiclesSteering(String requestBody, @HeaderParam("_") String someValue, 
			@PathParam(value = "vehicleId") String vehicleId) {
		SteeringOpts newSteering = new Gson().fromJson(requestBody, SteeringOpts.class);
		PlayerVehicleEntity vehicle = playerVehicleDB.getVehicleByVid(vehicleId);
		
		if (vehicleSteeringDB.findByVid(vehicleId) == null) {
			edgeVehiclesBE.createDefaultSteering(vehicleId, vehicle);
		}
		vehicleSteeringDB.updateSteering(vehicleId, newSteering);
		JsonObject carJson = edgeVehiclesBE.prepareVehicleData(vehicle);
		edgeSocketVehiclesBE.prepareAssetVehicleUpdate(forcePlayerId, "/asset/vehicles/steering");
		
		System.out.println("### [Vehicles] Vehicle ID " + vehicleId + 
				" new steering settings request from player ID " + forcePlayerId + ".");
	    return carJson.toString();
	}
	
}