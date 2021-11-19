package com.pverge.core.socket.dataobjects;

import java.util.List;

/**
 * Socket-IO - Collection of various data objects (Rewards of race matches)
 * @author Hypernucle
 */
public class SIOMatchRewardObjects {
	
	public static class RewardHistory {
		private String provider; 
		private String display; 
		private String type; 
		private int code; 
		private int count; 
		private int displayValue; 

		public String getProvider() {
			return provider; 
		}
		public void setProvider(String input) {
			this.provider = input;
		}
		
		public String getDisplay() {
			return display; 
		}
		public void setDisplay(String input) {
			this.display = input;
		}
		
		public String getType() {
			return type; 
		}
		public void setType(String input) {
			this.type = input;
		}
		
		public int getCode() {
			return code; 
		}
		public void setCode(int input) {
			this.code = input;
		}
		
		public int getCount() {
			return count; 
		}
		public void setCount(int input) {
			this.count = input;
		}
		
		public int getDisplayValue() {
			return displayValue; 
		}
		public void setDisplayValue(int input) {
			this.displayValue = input;
		}
	}
	
	public static class RewardSP {
		private int base; 
		private int increment; 
		private List<RewardHistory> history; 

		public int getBase() {
			return base; 
		}
		public void setBase(int input) {
			this.base = input;
		}
		
		public int getIncrement() {
			return increment; 
		}
		public void setIncrement(int input) {
			this.increment = input;
		}
		
		public List<RewardHistory> getHistory() {
			return history; 
		}
		public void setHistory(List<RewardHistory> input) {
			this.history = input;
		}
	}

	public static class RewardExp {
		private int base; 
		private int increment; 
		private List<RewardHistory> history; 

		public int getBase() {
			return base; 
		}
		public void setBase(int input) {
			this.base = input;
		}
		
		public int getIncrement() {
			return increment; 
		}
		public void setIncrement(int input) {
			this.increment = input;
		}
		
		public List<RewardHistory> getHistory() {
			return history; 
		}
		public void setHistory(List<RewardHistory> input) {
			this.history = input;
		}
	}

	public static class RewardDexp {
		private int base; 
		private int increment; 
		private List<RewardHistory> history; 

		public int getBase() {
			return base; 
		}
		public void setBase(int input) {
			this.base = input;
		}
		
		public int getIncrement() {
			return increment; 
		}
		public void setIncrement(int input) {
			this.increment = input;
		}
		
		public List<RewardHistory> getHistory() {
			return history; 
		}
		public void setHistory(List<RewardHistory> input) {
			this.history = input;
		}
	}
	
	public static class Gain {
		private String provider; 
		private String display; 
		private String type; 
		private int code; 
		private int count; 

		public String getProvider() {
			return provider; 
		}
		public void setProvider(String input) {
			this.provider = input;
		}
		
		public String getDisplay() {
			return display; 
		}
		public void setDisplay(String input) {
			this.display = input;
		}
		
		public String getType() {
			return type; 
		}
		public void setType(String input) {
			this.type = input;
		}
		
		public int getCode() {
			return code; 
		}
		public void setCode(int input) {
			this.code = input;
		}
		
		public int getCount() {
			return count; 
		}
		public void setCount(int input) {
			this.count = input;
		}
	}
	
	public static class Lost {
		private String provider; 
		private String display; 
		private String type; 
		private int code; 
		private int count; 

		public String getProvider() {
			return provider; 
		}
		public void setProvider(String input) {
			this.provider = input;
		}
		
		public String getDisplay() {
			return display; 
		}
		public void setDisplay(String input) {
			this.display = input;
		}
		
		public String getType() {
			return type; 
		}
		public void setType(String input) {
			this.type = input;
		}
		
		public int getCode() {
			return code; 
		}
		public void setCode(int input) {
			this.code = input;
		}
		
		public int getCount() {
			return count; 
		}
		public void setCount(int input) {
			this.count = input;
		}
	}
	
	public static class Items {
		private List<Gain> gain; 
		private List<Lost> lost; 

		public List<Gain> getGain() {
			return gain; 
		}
		public void setGain(List<Gain> input) {
			this.gain = input;
		}
		
		public List<Lost> getLost() {
			return lost; 
		}
		public void setLost(List<Lost> input) {
			this.lost = input;
		}
	}
	
	public static class RewardCard {
		private Items items; 

		public Items getItems() {
			return items; 
		}
		public void setItems(Items input) {
			this.items = input;
		}
	}
	
	public static class RewardsOpts {
		private RewardSP sp; 
		private RewardExp exp; 
		private RewardDexp dexp; 
		private RewardCard card; 

		public RewardSP getSp() {
			return sp; 
		}
		public void setSp(RewardSP input) {
			this.sp = input;
		}
		
		public RewardExp getExp() {
			return exp; 
		}
		public void setExp(RewardExp input) {
			this.exp = input;
		}
		
		public RewardDexp getDexp() {
			return dexp; 
		}
		public void setDexp(RewardDexp input) {
			this.dexp = input;
		}
		
		public RewardCard getCard() {
			return card; 
		}
		public void setCard(RewardCard input) {
			this.card = input;
		}
	}
}
