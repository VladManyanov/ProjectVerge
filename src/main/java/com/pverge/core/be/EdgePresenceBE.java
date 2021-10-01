package com.pverge.core.be;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Player presence current status & socket event actions
 * @author Hypernucle
 */
@Stateless
public class EdgePresenceBE {

	@EJB
	private EdgeEventLauncherBE eventLauncher;
	
	static String playerState = "idle";
	static String playerActivity = "openworld";
	static String futureTrackId = "1";
	
	/**
	 * Set current player state, and send appropriate socket request 
	 */
	public void setPlayerState(String state, String playerId) {
		playerState = state;
		if (playerState.contentEquals("busy")) {
			switch(playerActivity) {
			case "timetrial":
				eventLauncher.prepareTimeTrial(playerId, eventLauncher.getTrackLevel(futureTrackId));
				break;
			default:
				break;
			}
		}
	}
	
	/**
	 * Set current player activity (Game Mode and Track Code)
	 */
	public void setPlayerActivity(String activity, String trackId) {
		playerActivity = activity;
		futureTrackId = trackId;
	}
	
	/**
	 * Set current player vehicle (Socket request)
	 */
	public void changeRecentVehicleSIORequest(String pid, String vid) {
		eventLauncher.changeRecentVehicleSIO(pid, vid);
	}
}

//TimerTask task = new TimerTask() {
//public void run() {
	
//}
//};
//Timer timer = new Timer("Timer");

//long delay = 2000L;
//timer.schedule(task, delay);
