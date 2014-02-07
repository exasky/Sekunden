package com.ups.sekunden.rythm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ups.sekunden.domain.Disk;

public class RythmRandomImpl implements IRythm, Runnable {

	private List<IRythmListener> listenerList;
	private int heightScreen;
	private int widthScreen;

	public RythmRandomImpl(int height, int width) {
		this.heightScreen = height;
		this.widthScreen = width;
		this.listenerList = new ArrayList<IRythmListener>();
	}

	@Override
	public void produceDisk() {

		Random rand = new Random();
		Disk disk = new Disk();

		disk.setxCenter(rand.nextInt(this.widthScreen));
		disk.setyCenter(rand.nextInt(this.heightScreen));
		disk.setRadius(rand.nextInt(10));
		disk.setMsTime(500);

		notifyListeners(disk);
	}

	@Override
	public void registerListener(IRythmListener listener) {
		this.listenerList.add(listener);
	}

	@Override
	public void unRegisterListener(IRythmListener listener) {
		for (int i = 0; i < this.listenerList.size(); i++) {
			if (this.listenerList.get(i) == listener) {
				this.listenerList.remove(i);
				break;
			}
		}
	}

	@Override
	public void notifyListeners(Disk disk) {
		for (IRythmListener listener : listenerList) {
			listener.notifyGame(disk);
		}
	}

	@Override
	public void run() {
		while (true) {
			produceDisk();
			try {
				wait(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
