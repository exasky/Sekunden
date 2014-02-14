package com.ups.sekunden.game;

import com.ups.sekunden.graphic.GraphicalDisk;

public interface ICounter {
	int addPoint(GraphicalDisk disk);

	int getScore();
}
