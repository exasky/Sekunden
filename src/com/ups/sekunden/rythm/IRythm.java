package com.ups.sekunden.rythm;

import com.ups.sekunden.domain.Disk;

public interface IRythm extends Runnable {
	public void produceDisk();

	public void registerListener(IRythmListener listener);

	public void unRegisterListener(IRythmListener listener);

	public void notifyListeners(Disk disk);
}
