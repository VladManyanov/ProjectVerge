package com.pverge.core.socket.dataobjects;

import java.util.List;

import com.pverge.core.socket.dataobjects.SIODataObjects.Observers;
import com.pverge.core.socket.dataobjects.SIORaceCommonObjects.Clients;

/**
 * Socket-IO - Collection of various data objects (Time Trial)
 * @author Hypernucle
 */
public class SIOTimeTrialObjects {

	public static class TTOpts {
		private int matchId; 
		private String creator; 
		private String coreGameModeSchematic;
		private String level;
		private int laps;
		private boolean isManagerMode;
		private List<Clients> clients; 
		private List<Observers> observers; 

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
			return laps;}
		public void setLaps(int value) {
			this.laps = value;
		}

		public boolean getManagerMode() {
			return isManagerMode;
		}
		public void setManagerMode(boolean value) {
			this.isManagerMode = value;
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
	}
}
