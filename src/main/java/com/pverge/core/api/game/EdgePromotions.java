package com.pverge.core.api.game;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pverge.core.be.EdgeTokensBE;
import com.pverge.core.db.dbobjects.PlayerEntity;

/**
 * Edge - Promotion events requests
 * @author Hypernucle
 */
@Path("/v2")
public class EdgePromotions {
	
	@EJB
	private EdgeTokensBE tokensBE;
	@Context
	private HttpServletRequest sr;
	// TODO 
	
	/**
	 * Get current Promotions request
	 */
	@GET
	@Path("promotion2/promotions")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiPromotions() {
		PlayerEntity player = tokensBE.resolveToken(sr.getHeader("Authorization"));
		JsonArray rootArrayJson = new JsonArray();
		JsonObject promoJson = new JsonObject();
		rootArrayJson.add(promoJson);
		
		promoJson.addProperty("title", "Promo Title");
		promoJson.addProperty("desc", "Promo Desc");
		promoJson.addProperty("type", "WEBEVENT");
		promoJson.addProperty("webpageAddr", "https://github.com");
		promoJson.addProperty("__v", 0);
		
		JsonObject conditionJson = new JsonObject();
		promoJson.add("condition", conditionJson);
		conditionJson.addProperty("startAt", "2018-08-09T05:25:00.000Z");
		conditionJson.addProperty("endAt", "2077-09-27T18:30:00.000Z");
		JsonObject targetUserJson = new JsonObject();
		targetUserJson.addProperty("igr", false);
		conditionJson.add("targetUser", targetUserJson);
		
		conditionJson.addProperty("repeatLimit", 1);
		conditionJson.addProperty("repeatCycle", 2);
		JsonArray dayOfWeekJson = new JsonArray();
		conditionJson.add("dayOfWeek", dayOfWeekJson);
		dayOfWeekJson.add(true);
		dayOfWeekJson.add(true);
		dayOfWeekJson.add(true);
		dayOfWeekJson.add(true);
		dayOfWeekJson.add(true);
		dayOfWeekJson.add(true);
		dayOfWeekJson.add(true);
		conditionJson.addProperty("endTimeStr", "00:00");
		conditionJson.addProperty("startTimeStr", "00:00");
		conditionJson.addProperty("endTime", 1440);
		conditionJson.addProperty("startTime", 0);
		
		JsonObject showJson = new JsonObject();
		promoJson.add("show", showJson);
		showJson.addProperty("startAt", "2018-08-09T05:00:00.000Z");
		showJson.addProperty("endAt", "2077-09-27T18:30:00.000Z");
		showJson.addProperty("listOrder", 0);
		showJson.addProperty("bannerOrder", -1);
		showJson.addProperty("isShown", true);
		
		promoJson.addProperty("code", 100002);
		promoJson.addProperty("id", "12345");
		
		System.out.println("### [Promotions] Current Promotions information request from player ID " + player.getPid() + ".");
	    return rootArrayJson.toString();
	}
	
	/**
	 * Promotions records request
	 */
	@GET
	@Path("promotion2/records")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiPromotionsRecords() {
		PlayerEntity player = tokensBE.resolveToken(sr.getHeader("Authorization"));
		JsonArray rootArrayJson = new JsonArray();
		JsonObject recordJson = new JsonObject();
		rootArrayJson.add(recordJson);
		
		recordJson.addProperty("pid", player.getPid()); 
		recordJson.addProperty("promotionId", "12345");
		
		JsonObject dtJson = new JsonObject();
		dtJson.addProperty("updatedAt", "2021-09-18T17:01:56.980Z");
		dtJson.addProperty("record", 1720);
		dtJson.addProperty("step", 1);
		recordJson.add("dt", dtJson);
		
		recordJson.addProperty("__v", 0);
		
		JsonObject repeatInfoJson = new JsonObject();
		repeatInfoJson.addProperty("cycleCount", 1);
		recordJson.add("repeatInfo", repeatInfoJson);
		
		recordJson.addProperty("id", "5bf076ab34dcb33871e3b644");
		
		System.out.println("### [Promotions] Promotions records request from player ID " + player.getPid() + ".");
	    return rootArrayJson.toString();
	}
	
	/**
	 * Promotions banners request. Always returns empty on original server
	 */
	@GET
	@Path("promotion2/banners")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiPromotionsBanners() {
		PlayerEntity player = tokensBE.resolveToken(sr.getHeader("Authorization"));
		JsonArray rootArrayJson = new JsonArray();
		JsonObject bannerJson = new JsonObject();
		rootArrayJson.add(bannerJson);
		
		System.out.println("### [Promotions] Banners request from player ID " + player.getPid() + ".");
	    return rootArrayJson.toString();
	}
    
}