package com.ups.sekunden;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

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
	private static int SOUND_ID_HILIGHT_TRIBE_DRAGONFLY = R.raw.hilighttribedragonfly;

	private DiskImageView diskImage;
	private IRythm rythm;
	private Thread rythmTh;
	private Integer xShift = null;
	private Integer yShift = null;
	private MediaPlayer backgroundMusic;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		// full screen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.game_layout);
		Display defaultDisplay = getWindowManager().getDefaultDisplay();

		// play musique
		backgroundMusic = MediaPlayer.create(this,
				SOUND_ID_HILIGHT_TRIBE_DRAGONFLY);
		backgroundMusic.setVolume(1, 1);
		backgroundMusic.setLooping(true);
		backgroundMusic.start(); // no need to call prepare(); create() does
									// that for you

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
		backgroundMusic.stop();
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
	public void doPause(View view) {
		if (rythm.isPaused()) {
			try {
				// ---gradient sound resume
				backgroundMusic.setVolume(0.333f, 0.333f); // setVolume (left,
															// rigt) float
															// values between 0
															// and 1
				backgroundMusic.start();
				Thread.sleep(400);

				backgroundMusic.setVolume(0.666f, 0.666f);
				Thread.sleep(400);

				backgroundMusic.setVolume(1.0f, 1.0f);
				// ---END OF gradient sound reume
				rythm.onResumed();
				diskImage.resume();

			} catch (InterruptedException e) {
			} // caued thread.sleep
		} else {
			rythm.onPaused();
			diskImage.pause();

			try {
				// ---START OF gradient sound pause
				backgroundMusic.setVolume(0.666f, 0.666f);
				Thread.sleep(400);
				backgroundMusic.setVolume(0.333f, 0.333f);
				Thread.sleep(400);
				backgroundMusic.pause();
				// ---END OF gradient sound pause

			} catch (InterruptedException e) {
			} // caused thread.sleep
		}
	}

}