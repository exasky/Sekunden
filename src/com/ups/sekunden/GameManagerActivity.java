package com.ups.sekunden;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ups.sekunden.domain.Score;

/**
 * Created by julien on 19/02/14.
 */
public class GameManagerActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_manager_layout);
		launchGame(null);
	}

	/**
	 * launch game
	 * 
	 * @param view
	 */
	public void launchGame(View view) {
		// launch partie
		Intent intent = new Intent(this, GameActivity.class);
		Log.i("GM Acitvity", "start game");
		Score score = null;

		// When result is coming, 'onActivityResult(int requestCode, int
		// resultCode, Intent data)' is call
		startActivityForResult(intent, 0);
		// setup timeout(In game)
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 0) {

			if (resultCode == RESULT_OK) {
				// Log.e("RESULT OK", "RESULT");
				// // int score = Integer.decode(data.getStringExtra("score"));
				// Log.e("zefezfefzfefzefzefezf", "ezfziezninieznfeoinfezf");
				// int score = 2;
				// String result = data.getExtras().getString("score");
				//
				// Log.e("debug result", result);
				// Log.e("ACTIVITYRESULT", data.getDataString() + " Extras:"
				// + data.getStringExtra("score") + " ");
				// TextView tv = (TextView) findViewById(R.id.printScoreView);
				// tv.setText(" " + score + " poins");
				// ask author (dialog box, see example credit)
				String author = null;

				// popup "p
				// if (!NetworkScoreService.add_score(author, score))
				;// serialize score to add later for example;
			}
		}

	}
}