/**
 * 
 */
package com.ups.sekunden.graphic;

import android.graphics.Color;

import com.ups.sekunden.domain.Disk;

/**
 * Disk with some more informations for graphical usage
 * @author SERIN Kevin
 *
 */
public class GraphicalDisk extends Disk {
	public static final int RADIUS_INIT = 50;
	public static final int RADIUS_MIN = 25;
	
	private int initialRadius;
	private int currentRadius;	//The current radius, it can change with the time
	private int color;
	
	
	public GraphicalDisk(int x, int y, int time) {
		super(x, y, time);
		this.initialRadius = RADIUS_INIT;
		this.currentRadius = RADIUS_INIT;

		this.changeColor(time);
	}
	
	public GraphicalDisk(Disk disk) {
		this(disk.getxCenter(), disk.getyCenter(), disk.getMsTime());
	}
	
	public int getCurrentRadius() {
		return this.currentRadius;
	}
	
	public void setCurrentRadius(int radius) {
		this.currentRadius = radius;
	}
	
	public int getInitialRadius() {
		return this.initialRadius;
	}
	
	public int getColor() {
		return this.color;
	}
	
	public void setColor(int color) {
		this.color = color;
	}
	
	private void changeColor(int time) {
		if(time <= 300) {
			this.color = Color.RED;
		}
		else if(time <= 500) {
			this.color = Color.MAGENTA;
		}
		else {
			this.color = Color.BLUE;
		}
	}
}
