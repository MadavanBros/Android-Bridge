package com.madavan.bridge.android;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.SurfaceView;

public class BridgeView extends SurfaceView {
	
	private GestureDetector _gestureDetector;
	private SimpleOnGestureListener _gestureListener = new SimpleOnGestureListener() {		
		@Override
		public boolean onDown(MotionEvent e) {
			return true;
		}
	};
	
	public BridgeView(Context context) {
		super(context);
		_gestureDetector = new GestureDetector(context, _gestureListener);
	}
	
	public BridgeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		_gestureDetector = new GestureDetector(context, _gestureListener);
	}
	
	public BridgeView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		_gestureDetector = new GestureDetector(context, _gestureListener);
	}

	@Override
	public void draw(Canvas canvas) {
		
	}

	@Override
	public boolean onTouchEvent(MotionEvent e) {
		return _gestureDetector.onTouchEvent(e);
	}
	
	private float convertDpToPixel(int dp) {
		Resources resources = this.getResources();
	    DisplayMetrics metrics = resources.getDisplayMetrics();
	    float px = dp * (metrics.densityDpi/160f);
	    return px;
	}
	
	private float convertPixelsToDp(float px){
	    Resources resources = this.getResources();
	    DisplayMetrics metrics = resources.getDisplayMetrics();
	    float dp = px / (metrics.densityDpi / 160f);
	    return dp;

	}
}
