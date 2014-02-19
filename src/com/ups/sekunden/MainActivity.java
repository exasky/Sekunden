package com.ups.sekunden;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
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
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		/**
		 * test Network score service try{ List<Score> lst =
		 * NetworkScoreService.get_scores("-score"); Log.d("Network_score",
		 * lst.toString()); lst = NetworkScoreService.get_scores("score");
		 * Log.d("Network_score",lst.toString()); Score tmp = lst.get(0);
		 * tmp.author="kamehameha"; Log.d("Network_score",
		 * String.valueOf(NetworkScoreService.add_score(tmp))); lst =
		 * NetworkScoreService.get_scores(); Log.d("Network_score",
		 * lst.toString()); }catch (Exception e){
		 * Log.e("Network_score",e.getStackTrace().toString()); }
		 */
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * method call when exit is pressed. To exit the app
	 * 
	 * @param view
	 */
	public void selfDestruct(View view) {
		Log.i(classTag, "exit app");
		finish();
	}

	/**
	 * method call when show score is pressed. To launch the game
	 * 
	 * @param view
	 */
	public void openScore(View view) {
		Intent intent = new Intent(this, ScoreActivity.class);
		Log.i(classTag, "show Scores");
		startActivity(intent);
	}

	/**
	 * method call when start game is pressed. To launch the game
	 * 
	 * @param view
	 */

	public void startGame(View view) {
		Intent intent = new Intent(this, GameManagerActivity.class);
		Log.i(classTag, "start game");
		startActivity(intent);
	}

	/**
	 * action when click on credit button
	 * 
	 * @param view
	 */
	public void openCredit(View view) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setTitle("Credits").setMessage(R.string.credit)
				.setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();
	}
}
