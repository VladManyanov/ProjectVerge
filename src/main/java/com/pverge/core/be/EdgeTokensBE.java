package com.pverge.core.be;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.pverge.core.db.PlayerDBLoader;
import com.pverge.core.db.dbobjects.PlayerEntity;

/**
 * Tokens & users management. Simplified for local server use
 * @author Hypernucle
 */
@Stateless
public class EdgeTokensBE {

	@EJB
	private PlayerDBLoader playerDB;
	
	private final Map<String, Integer> players = new ConcurrentHashMap<>();
	static String playerState = "idle";
	
	/**
	 * Save the player token
	 */
	public void savePlayerToken(String token, int pid) {
		players.put(token, pid);
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
    	int playerId = players.get(token);
		return playerDB.findById(playerId);
    }
	
}
