/**
 * 
 */
package com.ups.sekunden.graphic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ups.sekunden.domain.Disk;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * @author SERIN Kevin
 *
 */
public class DiskImageView extends ImageView {
	private static int TIME_REFRESH = 100;
	private Handler handler;
	private Runnable runnable;
	private List<GraphicalDisk> disks;
	private int speed = 10;
	
	public DiskImageView(Context context, AttributeSet attrs) {
		super(context,attrs);
		Log.d("test", "consr");
		this.disks = new ArrayList<GraphicalDisk>();
		this.handler = new Handler();
		this.runnable = new Runnable() {
			@Override
			public void run() {
				invalidate();
			}
		};
	}
	
	public void addDisk(Disk disk) {
		this.disks.add(new GraphicalDisk(disk));
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		this.drawCircles(canvas);
		
		handler.postDelayed(runnable, 500);
	}
	
	private void drawCircles(Canvas canvas) {
		Paint paint = new Paint();
		paint.setStyle(Paint.Style.FILL);
		
		int radius;
		
		Iterator<GraphicalDisk> it = this.disks.iterator();
		Log.d("test", "appel");
		while(it.hasNext()) {
			Log.d("test", "boucle");
			GraphicalDisk disk = it.next();
			paint.setColor(disk.getColor());
			radius = disk.getCurrentRadius();
			canvas.drawCircle(disk.getxCenter(), disk.getyCenter(), radius, paint);
			disk.setCurrentRadius(radius - 1);
			
			//remove old disk (to not be display anymore)
			if(radius <= 0) {
				it.remove();
			}
		}
	}
	
}
