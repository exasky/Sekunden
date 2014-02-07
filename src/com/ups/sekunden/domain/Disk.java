package com.ups.sekunden.domain;

public class Disk {
	private int xCenter;
	private int yCenter;
	private int radius;
	private int msTime;
	
	public Disk(int x, int y, int radius, int time) {
		this.xCenter = x;
		this.yCenter = y;
		this.radius = radius;
		this.msTime = time;
	}
	
	public int getxCenter() {
		return xCenter;
	}
	
	public void setxCenter(int xCenter) {
		this.xCenter = xCenter;
	}
	
	public int getyCenter() {
		return yCenter;
	}
	
	public void setyCenter(int yCenter) {
		this.yCenter = yCenter;
	}
	
	public int getRadius() {
		return radius;
	}
	
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	public int getMsTime() {
		return msTime;
	}
	
	public void setMsTime(int msTime) {
		this.msTime = msTime;
	}
	
}
