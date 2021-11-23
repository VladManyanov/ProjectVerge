package com.pverge.core.be;

import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
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
	
	private static Map<String, String> players = new ConcurrentHashMap<>();
	private final Map<String, UUID> playersSIO = new ConcurrentHashMap<>();
	static String playerState = "idle";
	
	/**
	 * Save the player token
	 */
	public void savePlayerToken(String token, String pid) {
		players.put(token, pid);
    }
	
	/**
	 * Save the player Socket-IO token
	 */
	public void savePlayerSIOToken(String pid, UUID id) {
		playersSIO.put(pid, id);
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
    	System.out.println("HEADEROOO: " + header);
    	String token = header.substring(7); // Cut down the "Bearer "
    	System.out.println("PLAYEROOO: " + players.get(token));
    	
    	String playerId = players.get(token);
		return playerDB.findById(Integer.parseInt(playerId));
    }
    
    /**
    * Get the current player UUID of Socket connection
	*/
    public UUID resolveSIOId(String pid) {
		return playersSIO.get(pid);
   }
	
}
