package com.pverge.core.socket.dataobjects;

import java.util.List;

import com.pverge.core.socket.dataobjects.SIODataObjects.Observers;
import com.pverge.core.socket.dataobjects.SIORaceCommonObjects.Clients;

/**
 * Socket-IO - Collection of various data objects (Race Matches, second request)
 * @author Hypernucle
 */
public class SIOMatch2Objects {
	
	public static class MatchPeerOpts {
		private int matchId; 
		private String creator; 
		private String coreGameModeSchematic;
		private String level;
		private int laps;
		private boolean isManagerMode;
		private int clientVersion;
		private List<Clients> clients; 
		private List<Observers> observers; 
		private String schematic;
		private boolean traffic;
		private End end;

		public int getMatchId() {
			return matchId;
		}
		public void setMatchId(int value) {
			this.matchId = value;
		}

		public String getCreator() {
			return creator;
		}
		public void setCreator(String value) {
			this.creator = value;
		}

		public String getCoreGameModeSchematic() {
			return coreGameModeSchematic;
		}
		public void setCoreGameModeSchematic(String value) {
			this.coreGameModeSchematic = value;
		}

		public String getLevel() {
			return level;
		}
		public void setLevel(String value) {
			this.level = value;
		}

		public int getLaps() {
			return laps;
		}
		public void setLaps(int value) {
			this.laps = value;
		}

		public boolean getManagerMode() {
			return isManagerMode;
		}
		public void setManagerMode(boolean value) {
			this.isManagerMode = value;
		}
		
		public int getClientVersion() {
			return clientVersion;
		}
		public void setClientVersion(int value) {
			this.clientVersion = value;
		}

		public List<Clients> getClients() {
			return clients;
		}
		public void setClients(List<Clients> value) {
			this.clients = value;
		}

		public List<Observers> getObservers() {
			return observers;
		}
		public void setObservers(List<Observers> value) {
			this.observers = value;
		}
		
		public String getSchematic() {
			return schematic;
		}
		public void setSchematic(String value) {
			this.schematic = value;
		}
		
		public boolean getTraffic() {
			return traffic;
		}
		public void setTraffic(boolean value) {
			this.traffic = value;
		}
		
		public End getEnd() {
			return end;
		}
		public void setEnd(End value) {
			this.end = value;
		}
	}
	
	public static class TakeDownTarget{}
	
	public static class End {
		private int checker; 
		private int limitTime; 
		private int takeDowns; 
		private List<TakeDownTarget> takeDownTarget; 

		public int getChecker() {
			return checker; 
		}
		public void setChecker(int input) {
			this.checker = input;
		}
		
		public int getLimitTime() {
			return limitTime; 
		}
		public void setLimitTime(int input) {
			this.limitTime = input;
		}
		
		public int getTakeDowns() {
			return takeDowns; 
		}
		public void setTakeDowns(int input) {
			this.takeDowns = input;
		}
		
		public List<TakeDownTarget> getTakeDownTarget() {
			return takeDownTarget; 
		}
		public void setTakeDownTarget(List<TakeDownTarget> input) {
			this.takeDownTarget = input;
		}
	}
}
