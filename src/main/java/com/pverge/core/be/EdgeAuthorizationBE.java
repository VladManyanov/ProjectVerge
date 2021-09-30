package com.pverge.core.be;

import com.google.gson.JsonObject;

/**
 * Edge - Initial authorization, session & token management.
 * Majority of requests contains "Authorization: Bearer 123456789.123456789" header, where number is Access Token (in base64) from Auth request
 * @author Hypernucle
 */
public class EdgeAuthorizationBE {

	/**
	 * Validate player token and assign Access token.
	 * Note: Auth methods have a difference on the initial token contents
	 * @return Access token & Request token
	 */
	public String getAuthTokenParams() {
		JsonObject rootJson = new JsonObject();
    	rootJson.addProperty("access_token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwdWJsaXNoZXIiOjIsInFxaWQiOjIxNzc5ODY0MjUsInR5"
				+ "cGVzIjpbInBjIiwiaWdyIiwidGVuY2VudCJdLCJzaWQiOiI2MTQzNzU2MzBiMWUxZTJmMjEwNzQxOWIiLCJhaWQiOiI1YTA4OTE3NzFmNDBiZj"
				+ "AwMDdmNTEyYmIiLCJjbGllbnRJZCI6IjU4NmRlNjRmOTA5YzM2MzA0YjZjZTMxZSIsImx2bCI6NywianRpIjoiNjE0Mzc1NjMwYjFlMWU3Nzgx"
				+ "MDc0MTljIiwiZXhwIjoxNjMzNjEwOTE1fQ.PiqGElLNENibsLBnzIdYhDJysNK62eDx1rpl0N3P3gA"); 
    	rootJson.addProperty("refresh_token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwdWJsaXNoZXIiOjIsInFxaWQiOjIxNzc5ODY0MjUsInR5"
				+ "cGVzIjpbInBjIiwiaWdyIiwidGVuY2VudCJdLCJzaWQiOiI2MTQzNzU2MzBiMWUxZTJmMjEwNzQxOWIiLCJhaWQiOiI1YTA4OTE3NzFmNDBiZj"
				+ "AwMDdmNTEyYmIiLCJjbGllbnRJZCI6IjU4NmRlNjRmOTA5YzM2MzA0YjZjZTMxZSIsImx2bCI6NywianRpIjoiNjE0Mzc1NjMwYjFlMWU3Nzgx"
				+ "MDc0MTljIiwiZXhwIjoxNjMzNjEwOTE1fQ.PiqGElLNENibsLBnzIdYhDJysNK62eDx1rpl0N3P3gA"); 
    	rootJson.addProperty("token_type", "Bearer"); 
    	// token example from official game server
    	// will be decoded as Base64 on client, with splitter ".", JSON array with access_token and exp value number (100)
    	// no clue if splitted parts must differ, on that example it's identical
    	
    	return rootJson.toString();
	}
}
