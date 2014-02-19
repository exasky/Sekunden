package com.ups.sekunden;

import java.util.List;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ups.sekunden.domain.Score;
import com.ups.sekunden.game.NetworkScoreService;

/**
 * Created by julien on 16/02/14.
 */
public class ScoreActivity extends Activity {
	private List<Score> scores;
	private String[] scoresStr;
	private ListView lv;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scores_layout);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		scores = NetworkScoreService.get_scores("-score");
		scoresStr = new String[scores.size()];

		lv = (ListView) findViewById(R.id.scoreListView);
		if (lv == null)
			Log.e("scoreAct", "fuck");

		for (int i = 0; i < scores.size(); i++)
			scoresStr[i] = scores.get(i).toString();

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1,
				scoresStr);
		lv.setAdapter(adapter);

		Log.d("scoreAct", String.valueOf(lv.getCount()));
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		scores = null;
	}
}