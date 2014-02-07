package com.ups.sekunden.rythm;

import java.util.ArrayList;
import java.util.Iterator;
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
		Disk disk = new Disk(rand.nextInt(this.widthScreen),
				rand.nextInt(this.heightScreen), rand.nextInt(10), 500);

		notifyListeners(disk);
	}

	@Override
	public void registerListener(IRythmListener listener) {
		this.listenerList.add(listener);
	}

	@Override
	public void unRegisterListener(IRythmListener listener) {
		Iterator<IRythmListener> it = this.listenerList.iterator();
		while (it.hasNext()) {
			IRythmListener listenerIt = it.next();
			if (listenerIt == listener) {
				it.remove();
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
