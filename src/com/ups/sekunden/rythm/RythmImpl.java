package com.ups.sekunden.rythm;

import java.util.ArrayList;
import java.util.List;

import com.ups.sekunden.domain.Disk;

public class RythmImpl implements IRythm {

	private List<IRythmListener> listenerList;
	private int heightScreen;
	private int widthScreen;

	public RythmImpl(int height, int width) {
		this.heightScreen = height;
		this.widthScreen = width;
		this.listenerList = new ArrayList<IRythmListener>();
	}

	@Override
	public void produceDisk() {
		// TODO Auto-generated method stub

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

}
