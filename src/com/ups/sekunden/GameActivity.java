package com.ups.sekunden;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
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
public class GameActivity extends Activity implements IRythmListener{
    public String classTag = "GameActivity";

    private DiskImageView diskImage;
    private IRythm rythm;
    private Thread rythmTh;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set fullscreen mode
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.game_layout);
        Display defaultDisplay = getWindowManager().getDefaultDisplay();

        diskImage = (DiskImageView)findViewById(R.id.viewDisk);
        rythm = new RythmRandomImpl(defaultDisplay.getHeight(),defaultDisplay.getWidth());
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
        //Log.e(classTag, disk.toString());
        diskImage.addDisk(disk);
    }

    /**
     * user click on pause button
     */
    public void doPause(View view){
        if (rythm.isPaused())
            rythm.onResumed();
        else
            rythm.onPaused();
    }
}