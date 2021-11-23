package com.pverge.core.api.game;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.pverge.core.be.EdgeAuthorizationBE;
import com.pverge.core.be.EdgeTokensBE;
import com.pverge.core.db.dbobjects.PlayerEntity;

/**
 * Edge - Initial authorization, session & token management.
 * Majority of requests contains "Authorization: Bearer 123456789.123456789" header, where number is Access Token (in base64) from Auth request
 * @author Hypernucle
 */
@Path("/v2")
public class EdgeAuthorization {
	
	@EJB
	private EdgeAuthorizationBE edgeAuthBE;
	@EJB
	private EdgeTokensBE tokensBE;
	@Context
	private HttpServletRequest sr;
	// TODO Real parsing of player tokens
	
	/**
	 * Initial Auth request (Dev-mode)
	 * @return Access token
	 */
    @POST
    @Path("oauth2/token")
    @Produces(MediaType.APPLICATION_JSON)
    public String apiDevTokenAuth(String requestBody) {
    	JsonObject requestJson = new Gson().fromJson(requestBody, JsonObject.class);
		String username = requestJson.get("username").getAsString();
    	
    	System.out.println("### [Auth] Login request from Username " + username + ", type: OAuth2.");
        return edgeAuthBE.getAuthTokenParams(username);
    }
    
    /**
	 * Initial Auth request (Chinese server mode)
	 * @return Access token
	 */
    @POST
    @Path("tc/token")
    @Produces(MediaType.APPLICATION_JSON)
    public String apiTCTokenAuth() {
    	System.out.println("### [Auth] Login request from player ID " + "33" + ", type: TC.");
        return edgeAuthBE.getAuthTokenParams("tc");
    }
    
    /**
	 * Initial Auth request (Korean server mode)
	 * @return Access token
	 */
    @POST
    @Path("nx/sso")
    @Produces(MediaType.APPLICATION_JSON)
    public String apiNXTokenAuth() {
    	System.out.println("### [Auth] Login request from player ID " + "33" + ", type: NX.");
        return edgeAuthBE.getAuthTokenParams("nx");
    }
    
    /**
	 * Session creation request - server-side only
	 */
	@POST
	@Path("session")
	@Produces(MediaType.APPLICATION_JSON)
	public Response apiCreateSession() {
		PlayerEntity player = tokensBE.resolveToken(sr.getHeader("Authorization"));
		
		System.out.println("### [Auth] Session request from player ID " + player.getPid() + ".");
	    return Response.ok().build();
	}
	
	/**
	 * Session creation request (Debug mode)
	 */
	@POST
	@Path("session/@debug")
	@Produces(MediaType.APPLICATION_JSON)
	public Response apiCreateDebugSession() {
		PlayerEntity player = tokensBE.resolveToken(sr.getHeader("Authorization"));
		
		System.out.println("### [Auth] Session request on Debug mode from player ID " + player.getPid() + ".");
	    return Response.ok().build();
	}
	
	/**
	 * Session delete request - server-side only
	 */
	@DELETE
	@Path("session")
	@Produces(MediaType.TEXT_HTML)
	public Response edgeEndSession() {
		PlayerEntity player = tokensBE.resolveToken(sr.getHeader("Authorization"));
		
		System.out.println("### [Auth] Session delete request from player ID " + player.getPid() + ".");
	    return Response.ok().build();
	}
}