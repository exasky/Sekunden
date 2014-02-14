package com.ups.sekunden.game;

import android.util.Log;

import com.ups.sekunden.graphic.GraphicalDisk;

public class CounterImpl implements ICounter {

	private int playerPoints;
	private static final int COEFF = 100;

	public CounterImpl() {
		this.playerPoints = 0;
	}

	@Override
	public int addPoint(GraphicalDisk disk) {
		double currentRadius = disk.getCurrentRadius();
		int points = (int) (COEFF * (1 / currentRadius));

		this.playerPoints += points;
		Log.e("POINTS", this.playerPoints + "");
		return points;
	}

	@Override
	public int getScore() {
		return this.playerPoints;
	}

}
