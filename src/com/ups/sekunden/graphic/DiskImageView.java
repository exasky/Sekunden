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
import android.widget.ImageView;

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
		
		handler.postDelayed(runnable, TIME_REFRESH);
	}
	
	private void drawCircles(Canvas canvas) {
		Paint paint = new Paint();
		paint.setStyle(Paint.Style.FILL);
		
		int radius;
		
		Iterator<GraphicalDisk> it = this.disks.iterator();
		while(it.hasNext()) {
			GraphicalDisk disk = it.next();
			paint.setColor(disk.getColor());
			radius = disk.getCurrentRadius();
			canvas.drawCircle(disk.getxCenter(), disk.getyCenter(), radius, paint);
			disk.setCurrentRadius(radius - (TIME_REFRESH * disk.getInitialRadius() / disk.getMsTime()));
			
			//remove old disk (to not be display anymore)
			if(radius <= GraphicalDisk.RADIUS_MIN) {
				it.remove();
			}
		}
	}
	
}
