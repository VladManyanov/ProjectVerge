package com.pverge.core.db.dbobjects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ATTRSTORQUE")
public class AttrsTorqueEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int vcode;
	private int grade;
	// Torque graph
	private float x1;
	private float y1;
	private float z1;
	//
	private float x2;
	private float y2;
	private float z2;
	//
	private float x3;
	private float y3;
	private float z3;
	//
	private float x4;
	private float y4;
	private float z4;
	//
	private float x5;
	private float y5;
	private float z5;
	//
	private float x6;
	private float y6;
	private float z6;
	//
	private float x7;
	private float y7;
	private float z7;
	//
	private float x8;
	private float y8;
	private float z8;
	//
	private float x9;
	private float y9;
	private float z9;
	//
	private float x10;
	private float y10;
	private float z10;
	//
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getVCode() {
		return vcode;
	}
	public void setVCode(int vcode) {
		this.vcode = vcode;
	}
	
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	public float getX1() {
		return x1;
	}
	public void setX1(float x1) {
		this.x1 = x1;
	}
	public float getY1() {
		return y1;
	}
	public void setY1(float y1) {
		this.y1 = y1;
	}
	public float getZ1() {
		return z1;
	}
	public void setZ1(float z1) {
		this.z1 = z1;
	}
	
	public float getX2() {
		return x2;
	}
	public void setX2(float x2) {
		this.x2 = x2;
	}
	public float getY2() {
		return y2;
	}
	public void setY2(float y2) {
		this.y2 = y2;
	}
	public float getZ2() {
		return z2;
	}
	public void setZ2(float z2) {
		this.z2 = z2;
	}
	
	public float getX3() {
		return x3;
	}
	public void setX3(float x3) {
		this.x3 = x3;
	}
	public float getY3() {
		return y3;
	}
	public void setY3(float y3) {
		this.y3 = y3;
	}
	public float getZ3() {
		return z3;
	}
	public void setZ3(float z3) {
		this.z3 = z3;
	}
	
	public float getX4() {
		return x4;
	}
	public void setX4(float x4) {
		this.x4 = x4;
	}
	public float getY4() {
		return y4;
	}
	public void setY4(float y4) {
		this.y4 = y4;
	}
	public float getZ4() {
		return z4;
	}
	public void setZ4(float z4) {
		this.z4 = z4;
	}
	
	public float getX5() {
		return x5;
	}
	public void setX5(float x5) {
		this.x5 = x5;
	}
	public float getY5() {
		return y5;
	}
	public void setY5(float y5) {
		this.y5 = y5;
	}
	public float getZ5() {
		return z5;
	}
	public void setZ5(float z5) {
		this.z5 = z5;
	}
	
	public float getX6() {
		return x6;
	}
	public void setX6(float x6) {
		this.x6 = x6;
	}
	public float getY6() {
		return y6;
	}
	public void setY6(float y6) {
		this.y6 = y6;
	}
	public float getZ6() {
		return z6;
	}
	public void setZ6(float z6) {
		this.z6 = z6;
	}
	
	public float getX7() {
		return x7;
	}
	public void setX7(float x7) {
		this.x7 = x7;
	}
	public float getY7() {
		return y7;
	}
	public void setY7(float y7) {
		this.y7 = y7;
	}
	public float getZ7() {
		return z7;
	}
	public void setZ7(float z7) {
		this.z7 = z7;
	}
	
	public float getX8() {
		return x8;
	}
	public void setX8(float x8) {
		this.x8 = x8;
	}
	public float getY8() {
		return y8;
	}
	public void setY8(float y8) {
		this.y8 = y8;
	}
	public float getZ8() {
		return z8;
	}
	public void setZ8(float z8) {
		this.z8 = z8;
	}
	
	public float getX9() {
		return x9;
	}
	public void setX9(float x9) {
		this.x9 = x9;
	}
	public float getY9() {
		return y9;
	}
	public void setY9(float y9) {
		this.y9 = y9;
	}
	public float getZ9() {
		return z9;
	}
	public void setZ9(float z9) {
		this.z9 = z9;
	}
	
	public float getX10() {
		return x10;
	}
	public void setX10(float x10) {
		this.x10 = x10;
	}
	public float getY10() {
		return y10;
	}
	public void setY10(float y10) {
		this.y10 = y10;
	}
	public float getZ10() {
		return z10;
	}
	public void setZ10(float z10) {
		this.z10 = z10;
	}

}
