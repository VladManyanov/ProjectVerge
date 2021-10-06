package com.pverge.core.socket.dataobjects;

import java.util.List;

/**
 * Socket-IO - Collection of various data objects (Inbox)
 * @author Hypernucle
 */
public class SIOInboxObjects {
	
	public static class Bunch {
		private String Id; 
		private int price; 
		private String currency; 
		private int count; 

		public String getId() {
			return Id; 
		}
		public void setId(String input) {
			this.Id = input;
		}
		
		public int getPrice() {
			return price; 
		}
		public void setPrice(int input) {
			this.price = input;
		}
		
		public String getCurrency() {
			return currency; 
		}
		public void setCurrency(String input) {
			this.currency = input;
		}
		
		public int getCount() {
			return count; 
		}
		public void setCount(int input) {
			this.count = input;
		}
	}
	
	public static class Opts {
		private String orderNo; 
		private Bunch bunch; 
		private String sellingShop; 

		public String getOrderNo() {
			return orderNo; 
		}
		public void setOrderNo(String input) {
			this.orderNo = input;
		}
		
		public Bunch getBunch() {
			return bunch; 
		}
		public void setBunch(Bunch input) {
			this.bunch = input;
		}
		
		public String getSellingShop() {
			return sellingShop; 
		}
		public void setSellingShop(String input) {
			this.sellingShop = input;
		}
	}
	
	public static class Rewards {
		private int count; 
		private int code; 
		private String type; 
		private String Id; 

		public int getCount() {
			return count; 
		}
		public void setCount(int input) {
			this.count = input;
		}
		
		public int getCode() {
			return code; 
		}
		public void setCode(int input) {
			this.code = input;
		}
		
		public String getType() {
			return type; 
		}
		public void setType(String input) {
			this.type = input;
		}
		
		public String getId() {
			return Id; 
		}
		public void setId(String input) {
			this.Id = input;
		}
	}
	
	public static class InboxItemBody {
		private String pid; 
		private String type; 
		private String title; 
		private String desc; 
		private String imgpath; 
		private Opts opts; 
		private String createdat; 
		private int count; 
		private List<Rewards> rewards; 
		private String id; 

		public String getPid() {
			return pid; 
		}
		public void setPid(String input) {
			this.pid = input;
		}
		
		public String getType() {
			return type; 
		}
		public void setType(String input) {
			this.type = input;
		}
		
		public String getTitle() {
			return title; 
		}
		public void setTitle(String input) {
			this.title = input;
		}
		
		public String getDesc() {
			return desc; 
		}
		public void setDesc(String input) {
			this.desc = input;
		}
		
		public String getImgpath() {
			return imgpath; 
		}
		public void setImgpath(String input) {
			this.imgpath = input;
		}
		
		public Opts getOpts() {
			return opts; 
		}
		public void setOpts(Opts input) {
			this.opts = input;
		}
		
		public String getCreatedat() {
			return createdat; 
		}
		public void setCreatedat(String input) {
			this.createdat = input;
		}
		
		public int getCount() {
			return count; 
		}
		public void setCount(int input) {
			this.count = input;
		}
		
		public List<Rewards> getRewards() {
			return rewards; 
		}
		public void setRewards(List<Rewards> input) {
			this.rewards = input;
		}
		
		public String getId() {
			return id; 
		}
		public void setId(String input) {
			this.id = input;
		}
	}
	
	public static class ItemChangeBody {
		private int code; 
		private String updatedat; 
		private String createdat; 
		private String addedat; 
		private int count; 
		private String id; 

		public int getCode() {
			return code; 
		}
		public void setCode(int input) {
			this.code = input;
		}
		
		public String getUpdatedat() {
			return updatedat; 
		}
		public void setUpdatedat(String input) {
			this.updatedat = input;
		}
		
		public String getCreatedat() {
			return createdat; 
		}
		public void setCreatedat(String input) {
			this.createdat = input;
		}
		
		public String getAddedat() {
			return addedat; 
		}
		public void setAddedat(String input) {
			this.addedat = input;
		}
		
		public int getCount() {
			return count; 
		}
		public void setCount(int input) {
			this.count = input;
		}
		
		public String getId() {
			return id; 
		}
		public void setId(String input) {
			this.id = input;
		}
	}
}
