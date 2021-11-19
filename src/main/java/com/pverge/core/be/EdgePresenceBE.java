package com.pverge.core.be;

import java.util.Timer;
import java.util.TimerTask;

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
	static String savePlayerId = "33";
	static int futureTrackId = 22;
	static int room2MaxPlayers = 1;
	static String room2GameModeMeta = "SPEEDINDIVIDUAL";
	
	/**
	 * Set current player state
	 */
	public void setPlayerState(String state, String playerId) {
		playerState = state;
		eventLauncher.stateChangeSIO(playerId, state);
	}
	
	/**
	 * Get current player state
	 */
	public String getPlayerState() {
		return playerState;
	}
	
	/**
	 * Send appropriate socket request to initiate event
	 */
	public void initRaceEvent() {
		switch(playerActivity) {
		case "timetrial":
			eventLauncher.prepareTimeTrial(savePlayerId, eventLauncher.getTrackLevel(futureTrackId));
			break;
		case "room2superpeer":
			eventLauncher.prepareRoomSuperPeer(savePlayerId, futureTrackId, room2GameModeMeta, room2MaxPlayers);
			eventLauncher.prepareRoomSuperPeer2(savePlayerId, futureTrackId, room2GameModeMeta, room2MaxPlayers);
			break;
		default:
			break;
		}
	}
	
	/**
	 * Set current player activity
	 */
	public void setPlayerActivity(String activity, int trackId, String playerId) {
		playerActivity = activity;
		futureTrackId = trackId;
		savePlayerId = playerId;
	}
	
	/**
	 * Set current player vehicle (Socket request)
	 */
	public void changeRecentVehicleSIORequest(String pid, String vid) {
		eventLauncher.changeRecentVehicleSIO(pid, vid);
	}
	
	/**
	 * Set Room properties
	 */
	public void changeRoomProperties(int maxPlayers, String gameModeMeta) {
		room2MaxPlayers = maxPlayers;
		room2GameModeMeta = gameModeMeta;
	}
	
	/**
	 * Start Socket events with delay
	 */
	public void startWithDelay(String action) {
		TimerTask task = new TimerTask() {
			public void run() {
				switch(action) {
				case "initRaceEvent":
					initRaceEvent();
					break;
				case "matchFinish":
					eventLauncher.MatchRewardSIO(savePlayerId, room2GameModeMeta, futureTrackId);
					break;
				}
			}
		};
		Timer timer = new Timer("Timer");
		long delay = 1000L;
		timer.schedule(task, delay);
	}
	
	
}
