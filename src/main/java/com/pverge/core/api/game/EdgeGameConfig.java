package com.pverge.core.api.game;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.JsonObject;

/**
 * Edge - Initial game configuration parameters
 * Majority of requests contains some unknown path value, example: _=1632169536992
 * @author Hypernucle
 */
@Path("/v2")
public class EdgeGameConfig {

	@Context
	private HttpServletRequest sr;
	
	// TODO Load Push address from some config file
    
    /**
	 * Sand SDK API Server request. Always points on localhost address
	 * @return IP address of Sand SDK
	 */
	@GET
	@Path("tc/sandsdk/apiserver")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiSandSDKAddress(@HeaderParam("_") String someValue) {
		JsonObject rootJson = new JsonObject();
		rootJson.addProperty("apiServer", "127.0.0.1");
	    
		System.out.println("### [GameConfig] Sand SDK address request from IP " + sr.getRemoteAddr() + ".");
	    return rootJson.toString();
	}
	
	/**
	 * Push endpoint address to Socket-IO module.
	 * Note: IP will be parsed and cut up to the port value (http://.../socket-io)
	 * @return Socket-IO module IP and port
	 */
	@GET
	@Path("push/address")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiPushAddress(@HeaderParam("_") String someValue) {
		JsonObject rootJson = new JsonObject();
		rootJson.addProperty("pushServer", "http://127.0.0.1:3000");

		System.out.println("### [GameConfig] Socket-IO Push address request from IP " + sr.getRemoteAddr() + ".");
	    return rootJson.toString();
	}
	
	/**
	 * Player remote address
	 * @return Player remote IP address
	 */
	@GET
	@Path("remoteaddress")
	@Produces(MediaType.APPLICATION_JSON)
	public String apiRemoteAddress(@HeaderParam("_") String someValue) {
		JsonObject rootJson = new JsonObject();
		rootJson.addProperty("remoteAddress", sr.getRemoteAddr());

		System.out.println("### [GameConfig] Player remote address is " + sr.getRemoteAddr() + ".");
		return rootJson.toString();
	}
	
	/**
	 * NOTICE banners request. Not used by main API server, since game load the stuff from separate CDN server
	 * @return unknown
	 */
	@GET
	@Path("noticebanners")
	@Produces(MediaType.APPLICATION_JSON)
	public Response apiNoticeBanners() {
		System.out.println("### [GameConfig] NOTICE banners request from player IP " + sr.getRemoteAddr() + ".");
	    return Response.ok().build();
	}
}