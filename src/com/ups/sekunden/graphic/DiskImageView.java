/**
 * 
 */
package com.ups.sekunden.graphic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.ups.sekunden.domain.Disk;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author SERIN Kevin
 *
 */
public class DiskImageView extends ImageView {
	private static int TIME_REFRESH = 50;
	private Handler handler;
	private Runnable runnable;
	private List<GraphicalDisk> disks;
	
	public DiskImageView(Context context, AttributeSet attrs) {
		super(context,attrs);
		this.disks = new CopyOnWriteArrayList<GraphicalDisk>();
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
		
		handler.postDelayed(runnable, TIME_REFRESH);
	}
	
	private void drawCircles(Canvas canvas) {
		Paint paint = new Paint();
		int radius;
		int newRadius;
		Iterator<GraphicalDisk> it = this.disks.iterator();
		while(it.hasNext()) {
			GraphicalDisk disk = it.next();;
			
			//draw colored circle
			paint.setStyle(Paint.Style.FILL);
			paint.setColor(disk.getColor());
			radius = disk.getCurrentRadius();
			canvas.drawCircle(disk.getxCenter(), disk.getyCenter(), radius, paint);
			newRadius = radius - (TIME_REFRESH * disk.getInitialRadius() / disk.getMsTime());
			if(newRadius < GraphicalDisk.RADIUS_MIN) {
				newRadius = GraphicalDisk.RADIUS_MIN;
			}
			disk.setCurrentRadius(newRadius);
			
			
			//draw border
			paint.setStyle(Paint.Style.STROKE);
			paint.setColor(Color.BLACK);
			canvas.drawCircle(disk.getxCenter(), disk.getyCenter(), GraphicalDisk.RADIUS_MIN, paint);
			
			//remove old disk (to not be display anymore)
			if(radius <= GraphicalDisk.RADIUS_MIN) {
				disks.remove(disk);
			}
		}
	}
	
}
