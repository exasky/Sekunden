package com.ups.sekunden;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    public String classTag = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

    /**
     * method call when exit is pressed.
     * To exit the app
     */
    public void selfDestruct(View view){
        Log.i(classTag, "exit app");
        finish();
    }

    /**
     * method call when start game is pressed.
     * To launch the game
     */
    public void startGame(View view){
        Intent intent = new Intent(this, GameActivity.class);
        Log.i(classTag, "start game");
        startActivity(intent);
    }
}
