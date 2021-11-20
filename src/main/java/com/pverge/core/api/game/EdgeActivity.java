package com.pverge.core.api.game;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pverge.core.be.EdgeTokensBE;
import com.pverge.core.be.EdgeVehicleAttributesBE;
import com.pverge.core.db.dbobjects.PlayerEntity;

/**
 * Edge - Various in-game activities
 * @author Hypernucle
 */
@Path("/v2")
public class EdgeActivity {
	
	@EJB
	private EdgeTokensBE tokensBE;
	@EJB
	private EdgeVehicleAttributesBE vehicleAttributesBE;
	@Context
	private HttpServletRequest sr;
	
	/**
	 * Daily quests request, up to 5 quests. All quests properties is taken by ID from client Static Data list
	 * @return Quests list
	 */
	@GET
	@Path("quests")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiQuests(@HeaderParam("_") String someValue) {
		//PlayerEntity player = tokensBE.resolveToken(sr.getHeader("Authorization"));
		
		JsonObject rootJson = new JsonObject();
		//rootJson.addProperty("id", String.valueOf(player.getPid()));
		rootJson.addProperty("id", "33");
		rootJson.addProperty("qp", 290);
		rootJson.addProperty("qpResetTime", "2022-09-30T16:00:00.000Z");
		rootJson.addProperty("qpResetType", "MONTHLY");
		
		JsonArray questProgressArray = new JsonArray();
		rootJson.add("questProgress", questProgressArray);
		JsonObject q1Json = new JsonObject();
		q1Json.addProperty("code", 10103);
		q1Json.addProperty("value", 1);
		q1Json.addProperty("completed", false);
		questProgressArray.add(q1Json);
		
		rootJson.addProperty("questResetTime", "2022-09-19T16:00:00.000Z");
		rootJson.addProperty("questResetType", "DAILY");
		
		//System.out.println("### [Activity] Quests list request from player ID " + 
		//		String.valueOf(player.getPid()) + ".");
		System.out.println("### [Activity] Quests list request from player ID 33.");
	    return rootJson.toString();
	}
	
	/**
	 * Get Licenses mode data request
	 */
	@GET
	@Path("licenses/{playerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiLicensesGet(@PathParam(value = "playerId") String playerId) {
		JsonObject rootJson = new JsonObject();
		rootJson.addProperty("code", 4);
		rootJson.addProperty("createdat", "2017-11-12T18:34:50.153Z");
		rootJson.addProperty("__v", 24);
		
		JsonArray completedTestArray = new JsonArray();
		rootJson.add("completedTest", completedTestArray);
		
		rootJson.addProperty("id", playerId);
		
		System.out.println("### [Activity] Licenses data request from player ID " + playerId + ".");
	    return rootJson.toString();
	}
	
	/**
	 * Match vehicle attributes request, used during race start
	 */
	@GET
	@Path("match2/vehicle/attrs")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiMatchVehicleAttrs(@QueryParam("code") String code, @QueryParam("grade") String grade) {
		//PlayerEntity player = tokensBE.resolveToken(sr.getHeader("Authorization"));
		//System.out.println("### [Activity] Match vehicle attrib request from player ID " + player.getPid() + 
		//		", vehicle code: " + code + ".");
		List<Object> attrsObj = vehicleAttributesBE.getStockCarAttrs(Integer.parseInt(code), Integer.parseInt(grade));
		
		System.out.println("### [Activity] Match vehicle attrib request from player ID 33, vehicle code: " + code + ".");
	    return new Gson().toJson(attrsObj);
	}
    
}