package com.pverge.core.db.dbobjects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "VEHICLESTEERING")
@NamedQueries({ 
	@NamedQuery(name = "VehicleSteeringEntity.findByVid", query = "SELECT obj FROM VehicleSteeringEntity obj WHERE obj.vid = :vid"), //
	@NamedQuery(name = "VehicleSteeringEntity.setSteering", query = "UPDATE VehicleSteeringEntity obj SET "
			+ "obj.v0 = :v0, obj.v1 = :v1, obj.v2 = :v2, obj.v3 = :v3, obj.v4 = :v4, obj.v5 = :v5, obj.v6 = :v6, "
			+ "obj.v7 = :v7, obj.v8 = :v8, obj.v9 = :v9, obj.v10 = :v10, obj.v11 = :v11, obj.v12 = :v12, "
			+ "obj.v13 = :v13 WHERE obj.vid = :vid"), //
})
public class VehicleSteeringEntity {

	@Id
	private int vid;

	private int v0; private int v1; private int v2;
	private int v3; private int v4; private int v5;
	private int v6; private int v7; private int v8;
	private int v9; private int v10; private int v11;
	private int v12; private int v13;

	public String getVid() {
		return String.valueOf(vid);
	}
	public void setVid(String vid) {
		this.vid = Integer.parseInt(vid);
	}
	
	public int getV0() {
		return v0;
	}
	public void setV0(int v0) {
		this.v0 = v0;
	}
	
	public int getV1() {
		return v1;
	}
	public void setV1(int v1) {
		this.v1 = v1;
	}
	
	public int getV2() {
		return v2;
	}
	public void setV2(int v2) {
		this.v2 = v2;
	}
	
	public int getV3() {
		return v3;
	}
	public void setV3(int v3) {
		this.v3 = v3;
	}
	
	public int getV4() {
		return v4;
	}
	public void setV4(int v4) {
		this.v4 = v4;
	}
	
	public int getV5() {
		return v5;
	}
	public void setV5(int v5) {
		this.v5 = v5;
	}
	
	public int getV6() {
		return v6;
	}
	public void setV6(int v6) {
		this.v6 = v6;
	}
	
	public int getV7() {
		return v7;
	}
	public void setV7(int v7) {
		this.v7 = v7;
	}
	
	public int getV8() {
		return v8;
	}
	public void setV8(int v8) {
		this.v8 = v8;
	}
	
	public int getV9() {
		return v9;
	}
	public void setV9(int v9) {
		this.v9 = v9;
	}
	
	public int getV10() {
		return v10;
	}
	public void setV10(int v10) {
		this.v10 = v10;
	}
	
	public int getV11() {
		return v11;
	}
	public void setV11(int v11) {
		this.v11 = v11;
	}
	
	public int getV12() {
		return v12;
	}
	public void setV12(int v12) {
		this.v12 = v12;
	}
	
	public int getV13() {
		return v13;
	}
	public void setV13(int v13) {
		this.v13 = v13;
	}
}
