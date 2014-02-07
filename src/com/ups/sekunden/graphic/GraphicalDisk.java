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
	private int currentRadius;	//The current radius, it can change with the time
	private int color;
	
	
	public GraphicalDisk(int x, int y, int radius, int time) {
		super(x, y, radius, time);
		this.currentRadius = radius;
		this.color = Color.WHITE;
	}
	
	public GraphicalDisk(Disk disk) {
		this(disk.getxCenter(), disk.getyCenter(), disk.getRadius(), disk.getMsTime());
	}
	
	public int getCurrentRadius() {
		return this.currentRadius;
	}
	
	public void setCurrentRadius(int radius) {
		this.currentRadius = radius;
	}
	
	public int getInitialRadius() {
		return this.getRadius();
	}
	
	public int getColor() {
		return this.color;
	}
	
	public void setColor(int color) {
		this.color = color;
	}
}
