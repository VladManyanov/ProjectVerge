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
	
	static String playerState = "off";
	static String playerActivity = "openworld";
	static String savePlayerId = "33";
	static int futureTrackId = 22;
	static int room2MaxPlayers = 1;
	static String room2GameModeMeta = "SPEEDINDIVIDUAL";
	static String p1 = "33";
	static String p2 = "34";
	
	/**
	 * Set current player state
	 */
	public void setPlayerState(String state, String playerId) {
		if (!playerState.contentEquals("off")) { // Do not send SIO request before Auth SIO request from client
			eventLauncher.stateChangeSIO(playerId, state);
		}
		playerState = state;
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
	 * Get Room properties
	 */
	public String[] getRoomInfo() {
		return new String[] {room2GameModeMeta, String.valueOf(futureTrackId)};
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
				case "testFinishLoading":
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					eventLauncher.changeMatchEntriesState(p1, p1);
					eventLauncher.changeMatchEntriesState(p1, p2);
					eventLauncher.changeMatchEntriesState(p2, p1);
					eventLauncher.changeMatchEntriesState(p2, p2);
					break;
				}
			}
		};
		Timer timer = new Timer("Timer");
		long delay = 1000L;
		timer.schedule(task, delay);
	}
	
	
}
