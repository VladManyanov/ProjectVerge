package com.pverge.core.be;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Tokens & users management of Socket connection. Simplified for local server use
 * @author Hypernucle
 */
public class EdgeTokensSocketBE {
	
	private static Map<String, UUID> tokenAndUUID = new ConcurrentHashMap<>();
	private static Map<UUID, String> uuidAndToken = new ConcurrentHashMap<>();
	
	/**
	 * Save the player Socket-IO token
	 */
	public void keepPlayerSIOToken(String token, UUID id) {
		tokenAndUUID.put(token, id);
		uuidAndToken.put(id, token);
    }
	
	/**
	 * Delete player Socket-IO token
	 */
	public void removePlayerSIOToken(UUID id) {
		String token = uuidAndToken.get(id);
		tokenAndUUID.remove(token);
		uuidAndToken.remove(id);
    }
    
    /**
    * Get the current player session UUID of Socket connection
	*/
    public UUID resolveSIOId(String token) {
    	return tokenAndUUID.get(token);
   }
	
}
