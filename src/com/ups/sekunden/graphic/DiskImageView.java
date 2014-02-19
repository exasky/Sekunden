/**
 * 
 */
package com.ups.sekunden.graphic;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.ups.sekunden.R;
import com.ups.sekunden.domain.Disk;
import com.ups.sekunden.game.CounterImpl;
import com.ups.sekunden.game.ICounter;
import com.ups.sekunden.touch.ITouchReceiver;

/**
 * @author SERIN Kevin
 * 
 */
public class DiskImageView extends ImageView implements ITouchReceiver {
	private static int TIME_REFRESH = 50;
	private Handler handler;
	private Runnable runnable;
	private List<GraphicalDisk> disks;
	private boolean isPaused;
	private ICounter score;

	public DiskImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		super.setBackgroundResource(R.drawable.game_background);
		this.isPaused = false;
		this.disks = new CopyOnWriteArrayList<GraphicalDisk>();
		this.handler = new Handler();
		this.score = new CounterImpl();
		this.runnable = new Runnable() {
			@Override
			public void run() {
				invalidate();
			}
		};
	}

	public void addDisk(Disk disk) {
		Log.d("VIEWSIZE", "x: " + this.getHeight() + " y: " + this.getWidth());
		this.disks.add(new GraphicalDisk(disk));
	}

	public void pause() {
		this.isPaused = true;
	}

	public void resume() {
		this.isPaused = false;
	}

	public boolean isPaused() {
		return this.isPaused;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		this.drawScore(canvas);

		this.drawCircles(canvas);

		if (!this.isPaused) {
			this.actualizeDisks(); // Don't actualize if the game is in pause
			handler.postDelayed(runnable, TIME_REFRESH);
		}

	}

	private void actualizeDisks() {
		Iterator<GraphicalDisk> it = this.disks.iterator();
		while (it.hasNext()) {
			GraphicalDisk disk = it.next();

			int radius = disk.getCurrentRadius();

			int newRadius = radius
					- (TIME_REFRESH * disk.getInitialRadius() / disk
							.getMsTime());
			Log.i("radius", newRadius + "");
			if (newRadius < GraphicalDisk.RADIUS_MIN) {
				newRadius = GraphicalDisk.RADIUS_MIN;
			}
			disk.setCurrentRadius(newRadius);

			// remove old disk (to not be display anymore)
			if (radius <= GraphicalDisk.RADIUS_MIN) {
				disks.remove(disk);
			}
		}
	}

	private void drawScore(Canvas canvas) {
		Paint paint = new Paint();
		paint.setColor(Color.CYAN);
		paint.setTextSize(36);
		int widthMargin = 0;

		if (this.score.getScore() < 100) {
			widthMargin = 50;
		} else if (this.score.getScore() < 1000) {
			widthMargin = 70;
		} else if (this.score.getScore() < 10000) {
			widthMargin = 90;
		} else if (this.score.getScore() < 100000) {
			widthMargin = 100;
		} else if (this.score.getScore() < 1000000) {
			widthMargin = 130;
		}

		canvas.drawText(this.score.getScore() + "", canvas.getWidth()
				- widthMargin, 50, paint);
	}

	private void drawCircles(Canvas canvas) {
		Paint paint = new Paint();
		int radius;
		Iterator<GraphicalDisk> it = this.disks.iterator();
		int number = 1;

		while (it.hasNext()) {
			GraphicalDisk disk = it.next();

			// draw colored circle
			paint.setStyle(Paint.Style.FILL);
			paint.setColor(disk.getColor());
			radius = disk.getCurrentRadius();
			canvas.drawCircle(disk.getxCenter(), disk.getyCenter(), radius,
					paint);

			// draw border
			paint.setStyle(Paint.Style.STROKE);
			paint.setColor(Color.BLACK);
			canvas.drawCircle(disk.getxCenter(), disk.getyCenter(),
					GraphicalDisk.RADIUS_MIN, paint);

			// draw number
			paint.setTextSize(28);
			canvas.drawText(number + "", disk.getxCenter() - 8,
					disk.getyCenter() + 10, paint);

			// remove old disk (to not be display anymore)
			if (radius <= GraphicalDisk.RADIUS_MIN) {
				disks.remove(disk);
			}

			number++;
		}
	}

	@Override
	public void onTouch(int x, int y) {
		if (!this.disks.isEmpty()) {
			GraphicalDisk disk = this.disks.get(0);
			if (isTouchCorrect(x, y, disk)) {
				Log.e("Disk Touched", "Yaaaaaaaaaaaaaa");
				this.score.addPoint(disk);
				Canvas canvas = new Canvas();
				Paint p = new Paint();
				p.setColor(Color.BLUE);
				p.setTextSize(20);
				canvas.drawText("zefzfzef", 100, 100, p);
				this.draw(canvas);
				this.disks.remove(disk);
			} else {
				Log.e("Disk NOT Touched", "BOUOUOUOUOUOUU");
			}
		}
	}

	private boolean isTouchCorrect(int xTouch, int yTouch, GraphicalDisk disk) {

		int radius = disk.getCurrentRadius();
		int xCenter = disk.getxCenter();
		int yCenter = disk.getyCenter();
		double distNoSqrt = Math.pow((xTouch - xCenter), 2)
				+ Math.pow((yTouch - yCenter), 2);
		double sqrt = Math.sqrt(distNoSqrt);
		Log.e("RADIUS", radius + "");
		Log.e("DISTANCE", sqrt + "");
		return (sqrt < 2 * radius);
	}
}
