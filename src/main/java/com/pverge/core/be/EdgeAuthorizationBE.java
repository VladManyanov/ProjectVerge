package com.pverge.core.be;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.google.gson.JsonObject;

/**
 * Edge - Initial authorization, session & token management.
 * Majority of requests contains "Authorization: Bearer 123456789.123456789" header, where number is Access Token (in base64) from Auth request
 * @author Hypernucle
 */
public class EdgeAuthorizationBE {

	private static String forcePlayerId = "33";
	
	/**
	 * Validate player token and assign Access token.
	 * Note: Auth methods have a difference on the initial token contents
	 * @return Access token & Request token
	 */
	public String getAuthTokenParams() {
		// Token example (fake data)
		//"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwdWJsaXNoZXIiOjIsInFxaWQiOjEyMzQ1Njc" +
		//"4OSwidHlwZXMiOlsicGMiLCJpZ3IiLCJ0ZW5jZW50Il0sInNpZCI6IjA5ODc2NTQzMjEiLCJhaWQiOiIzMyIsImNsaWVudElkIjoiMD" +
		//"A5OTg4Nzc2NjU1NDQzMzIyMTEiLCJsdmwiOjcsImp0aSI6IjExMjIzMzQ0NTU2Njc3ODg5OSIsImV4cCI6MTYzNDY4MjUxOH0" +
		//".os8E0L4HrRex-hieoCepNTG3o4P1HXPmEBNYM-uvRyY"
		// last part from official server example: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.
		String token = "";
		LocalDateTime ldt = LocalDateTime.now().plusDays(7); // Expiration date
		try {
		    Algorithm algorithm = Algorithm.HMAC256("ProjectVerge");
		    token = JWT.create()
		    		.withClaim("publisher", 2)
		    		.withClaim("qqid", 123456789)
		    		.withArrayClaim("types", new String[]{"pc","igr","tc"})
		    		.withClaim("sid", "0987654321")
		    		.withClaim("aid", forcePlayerId)
		    		.withClaim("clientId", "00998877665544332211")
		    		.withClaim("lvl", 7)
		    		.withClaim("jti", "112233445566778899")
		    		.withClaim("exp", ldt.toEpochSecond(ZoneOffset.UTC))
		    		.sign(algorithm);
		} catch (JWTCreationException exception){
			System.out.println("!!! [Auth] Token creation failure, type: OAuth2.");
		}
		JsonObject rootJson = new JsonObject();
    	rootJson.addProperty("access_token", token); 
    	rootJson.addProperty("refresh_token", token); 
    	rootJson.addProperty("token_type", "Bearer"); 
    	
    	return rootJson.toString();
	}
}
