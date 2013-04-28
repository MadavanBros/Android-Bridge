package com.madavan.bridge.android;

import java.util.ArrayList;
import java.util.HashMap;

import com.madavan.bridge.cards.Card;

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

	public static HashMap<Card, Integer> _cardImages;
	public ArrayList<Card> _cards;

	/*
	 * I think we should create a cardImage class or something that contains its
	 * x,y coordinates so that we can move the cards along with their finger.
	 * onMove would change x,y and onUp would check/reset. that way draw would
	 * only need to just get the x and y locations and then it would be done
	 * 
	 * Or we could create a selectedCard variable that we move around...
	 * 
	 * Also we could just use ImageView for the cards. You can move ImageView so
	 * that is actually viable and it would mean not having to paint shit
	 * 
	 */

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
		for (int i = 0; i < _cards.size(); i++) {
			_cardImages.get(_cards.get(i));
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent e) {
		return _gestureDetector.onTouchEvent(e);
	}

	private float convertDpToPixel(int dp) {
		Resources resources = this.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		float px = dp * (metrics.densityDpi / 160f);
		return px;
	}

	private float convertPixelsToDp(float px) {
		Resources resources = this.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		float dp = px / (metrics.densityDpi / 160f);
		return dp;

	}
}
