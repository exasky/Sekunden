package com.ups.sekunden;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;

import android.widget.EditText;
import com.ups.sekunden.domain.Score;

/**
 * Created by julien on 19/02/14.
 */
public class GameManagerActivity extends Activity {

    private String author = null; // for get alert return
    private EditText input = null; // for alert
    private Intent intent = null; // syb activity


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

		// super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 0) if (resultCode == RESULT_OK) {
            /**
             * Get result of Game Activity doesn't work,
             * that's because no score adding to DB
             */


            // Log.i("RESULt :", "RESULT  :  "+data.getStringExtra("score"));
            // // int score = Integer.decode(data.getStringExtra("score"));
            // Log.i("zefezfefzfefzefzefezf", "ezfziezninieznfeoinfezf");
            // int score = 2;
            // String result = data.getExtras().getString("score");
            //
            // Log.i("debug result", result);
            // Log.i("ACTIVITYRESULT", data.getDataString() + " Extras:"
            // + data.getStringExtra("score") + " ");
            // TextView tv = (TextView) findViewById(R.id.printScoreView);
            // tv.setText(" " + score + " poins");
            // ask author (dialog box, see example credit)


            AlertDialog.Builder alert = new AlertDialog.Builder(this);

            alert.setTitle("Title");
            alert.setMessage("Message");
            input = new EditText(this);
            // Set an EditText view to get user input
            alert.setView(input);

            alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    author = input.getText().toString();
                    Log.i("Good news", " : " + author);
                    Log.i("Good news", " : " + input);
                    // Do something with value!
                    // NetworkScoreService.add_score(author, score);
                }

            });
            alert.show();
        }

	}
}