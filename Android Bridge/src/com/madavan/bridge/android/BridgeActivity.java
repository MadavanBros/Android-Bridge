package com.madavan.bridge.android;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.madavan.bridge.cards.Card;

public class BridgeActivity extends Activity {

	private ArrayList<Card> _cards;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bridge);
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	public class BridgeClient extends AsyncTask<Void, Void, Void> {

		private static final String HOST = "serverHost";
		private static final int PORT = 2221;

		private BridgePlayer _player;

		private boolean _isFinished;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			try {
				_player = new BridgePlayer(new Socket(HOST, PORT));
			} catch (IOException e) {
				Log.e("BridgeActivity", "BridgeClient@doInBackground: " + e.toString());
			}
		}

		@SuppressWarnings("finally")
		@Override
		protected Void doInBackground(Void... params) {
			try {
				while (!_isFinished) {
					if(_player.isReady()) {
						String[] commands = _player.readAll().split("\\.");
						for (String cmd : commands) {
						// Do shit for each command
						// publishProgress(null);
						}
					}					
				}
			} catch (IOException e) {
				Log.e("BridgeActivity", "BridgeClient@doInBackground: " + e.toString());
			} finally {
				return null;
			}
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			super.onProgressUpdate(values);
			// Update graphics based on new data
		}
	}
}
