package com.ups.sekunden.rythm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.ups.sekunden.domain.Disk;

public class RythmRandomImpl implements IRythm {
	public String classTag = "RythmeRandomImpl";

	private List<IRythmListener> listenerList = new ArrayList<IRythmListener>();
	private int heightScreen;
	private int widthScreen;
	private boolean stop; // true when the runnable must be stopped

	public RythmRandomImpl(int height, int width) {
		this.heightScreen = height;
		this.widthScreen = width;
		this.stop = false;
	}

	@Override
	public void produceDisk() {
		Random rand = new Random();
		Disk disk = new Disk(rand.nextInt(this.widthScreen),
				rand.nextInt(this.heightScreen), 2000);
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
		while (!this.stop) {
			produceDisk();
			try {
				Thread.sleep(700);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onStop() {
		this.stop = true;
	}
}
