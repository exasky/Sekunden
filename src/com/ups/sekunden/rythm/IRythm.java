package com.ups.sekunden.rythm;

import com.ups.sekunden.domain.Disk;

public interface IRythm extends Runnable {
	void produceDisk();

	void registerListener(IRythmListener listener);

	void unRegisterListener(IRythmListener listener);

	void notifyListeners(Disk disk);
	
	/**
	 * Called when the rythm generator must be stopped
	 */
	void onStop();
}
