package com.ups.sekunden;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import com.ups.sekunden.domain.Disk;
import com.ups.sekunden.graphic.DiskImageView;
import com.ups.sekunden.rythm.IRythm;
import com.ups.sekunden.rythm.IRythmListener;
import com.ups.sekunden.rythm.RythmRandomImpl;

/**
 * Created by julien on 07/02/14.
 */

public class GameActivity extends Activity implements IRythmListener {
	public String classTag = "GameActivity";

	private DiskImageView diskImage;
	private IRythm rythm;
	private Thread rythmTh;
	private Integer xShift = null;
	private Integer yShift = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        //full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.game_layout);
		Display defaultDisplay = getWindowManager().getDefaultDisplay();

        diskImage = (DiskImageView) findViewById(R.id.viewDisk);
		rythm = new RythmRandomImpl(defaultDisplay.getHeight(),
				defaultDisplay.getWidth());
		rythm.registerListener(this);

		rythmTh = new Thread(rythm);
		rythmTh.start();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		rythm.onStop();
	}

	@Override
	public void notifyGame(Disk disk) {
		Log.e(classTag, disk.toString());
		diskImage.addDisk(disk);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (xShift == null || yShift == null) {
			yShift = getWindowManager().getDefaultDisplay().getHeight()
					- diskImage.getHeight();
			xShift = getWindowManager().getDefaultDisplay().getWidth()
					- diskImage.getWidth();
		}

		diskImage.onTouch((int) event.getX() - xShift, (int) event.getY()
				- yShift);
		return super.onTouchEvent(event);
	}

    /**
     * user click on pause button
     */
    public void doPause(View view){
        if (rythm.isPaused()) {
        	try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
			}
            rythm.onResumed();
            diskImage.resume();
        }
        else {
            rythm.onPaused();
            diskImage.pause();
        }
    }
}