package com.pverge.core.be;

/**
 * Player presence current status & socket event actions
 * @author Hypernucle
 */
public class EdgePresenceBE {

	EdgeEventLauncherBE eventLauncher = new EdgeEventLauncherBE();
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
	
	public void setPlayerActivity(String activity, String trackId) {
		playerActivity = activity;
		futureTrackId = trackId;
	}
}

//TimerTask task = new TimerTask() {
//public void run() {
	
//}
//};
//Timer timer = new Timer("Timer");

//long delay = 2000L;
//timer.schedule(task, delay);
