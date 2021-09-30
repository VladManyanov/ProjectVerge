package com.pverge.core.api.game;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.pverge.core.be.EdgeAuthorizationBE;
import com.pverge.core.socket.NettySocketIO;

/**
 * Edge - Initial authorization, session & token management.
 * Majority of requests contains "Authorization: Bearer 123456789.123456789" header, where number is Access Token (in base64) from Auth request
 * @author Hypernucle
 */
@Path("/v2")
public class EdgeAuthorization {
	
	EdgeAuthorizationBE edgeAuthBE = new EdgeAuthorizationBE();
	private static String forcePlayerId = "33";
	// TODO Real parsing of player tokens
	
	/**
	 * Initial Auth request (Dev-mode)
	 * @return Access token
	 */
    @POST
    @Path("oauth2/token")
    @Produces(MediaType.APPLICATION_JSON)
    public String apiDevTokenAuth() {
    	System.out.println("### [Auth] Login request from player ID " + forcePlayerId + ", type: OAuth2.");
        return edgeAuthBE.getAuthTokenParams();
    }
    
    /**
	 * Initial Auth request (Chinese server mode)
	 * @return Access token
	 */
    @POST
    @Path("tc/token")
    @Produces(MediaType.APPLICATION_JSON)
    public String apiTCTokenAuth() {
    	System.out.println("### [Auth] Login request from player ID " + forcePlayerId + ", type: TC.");
        return edgeAuthBE.getAuthTokenParams();
    }
    
    /**
	 * Initial Auth request (Korean server mode)
	 * @return Access token
	 */
    @POST
    @Path("nx/sso")
    @Produces(MediaType.APPLICATION_JSON)
    public String apiNXTokenAuth() {
    	System.out.println("### [Auth] Login request from player ID " + forcePlayerId + ", type: NX.");
        return edgeAuthBE.getAuthTokenParams();
    }
    
    /**
	 * Session creation request - server-side only
	 */
	@POST
	@Path("session")
	@Produces(MediaType.APPLICATION_JSON)
	public Response apiCreateSession() {
		System.out.println("### [Auth] Session request from player ID " + forcePlayerId + ".");
	    return Response.ok().build();
	}
	
	/**
	 * Session delete request - server-side only
	 */
	@DELETE
	@Path("session")
	@Produces(MediaType.TEXT_HTML)
	public Response edgeEndSession() {
		System.out.println("### [Auth] Session delete request from player ID " + forcePlayerId + ".");
	    return Response.ok().build();
	}
}