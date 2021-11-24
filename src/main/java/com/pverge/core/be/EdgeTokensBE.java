package com.pverge.core.be;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import com.pverge.core.db.PlayerDBLoader;
import com.pverge.core.db.dbobjects.PlayerEntity;

/**
 * Tokens & users management. Simplified for local server use
 * @author Hypernucle
 */
@Stateful
public class EdgeTokensBE {

	@EJB
	private PlayerDBLoader playerDB;
	EdgeTokensSocketBE tokensSocketBE = new EdgeTokensSocketBE();
	
	private static Map<String, String> tokenAndPid = new ConcurrentHashMap<>();
	private static Map<String, String> pidAndToken = new ConcurrentHashMap<>();
	static String playerState = "idle";
	
	/**
	 * Save the player token
	 */
	public void savePlayerToken(String token, String pid) {
		tokenAndPid.put(token, pid);
		pidAndToken.put(pid, token);
    }
    
	/**
	 * Create new user during authorization
	 */
    public PlayerEntity createNewUser(String username) {
    	PlayerEntity player = new PlayerEntity();
    	player.setVid("1");
		player.setCampaignCode(1);
		player.setUserName(username);
		playerDB.insert(player);
		
		System.out.println("### [Auth] New user: " + username + ".");
		return player;
    }
    
    /**
	 * Get the current player data from header token
	 */
    public PlayerEntity resolveToken(String header) {
    	String token = header.substring(7); // Cut down the "Bearer "
    	String playerId = tokenAndPid.get(token);
		return playerDB.findById(Integer.parseInt(playerId));
    }
    
    /**
	 * Get the current player session from "Player ID - Token" pair
	 */
    public UUID getSessionUUID(String pid) {
    	String playerToken = pidAndToken.get(pid);
		return tokensSocketBE.resolveSIOId(playerToken);
    }
	
}
