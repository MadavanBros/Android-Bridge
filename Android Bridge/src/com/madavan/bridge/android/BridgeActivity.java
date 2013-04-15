package com.madavan.bridge.android;

import java.io.DataInputStream;
import java.io.DataOutputStream;
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

	}

	public class BridgeClient extends AsyncTask<Void, Void, Void> {

		private static final String HOST = "serverHost";
		private static final int PORT = 2221;

		private DataOutputStream _dataOut;
		private DataInputStream _dataIn;
		private Socket _socket;

		private boolean _isFinished;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			try {
				_socket = new Socket(HOST, PORT);
				_dataIn = new DataInputStream(_socket.getInputStream());
				_dataOut = new DataOutputStream(_socket.getOutputStream());
			} catch (IOException e) {
				Log.e("BridgeActivity",
						"BridgeClient@doInBackground: " + e.toString());
			}
		}

		@Override
		protected Void doInBackground(Void... params) {
			try {
				while (!_isFinished) {
					String data = "";
					while (_dataIn.available() > 0)
						data = _dataIn.readUTF();
					String[] commands = data.split("\\.");

					for (String cmd : commands) {
						// Do shit for each command
						// publishProgress(null);
					}
				}
			} catch (IOException e) {
				Log.e("BridgeActivity",
						"BridgeClient@doInBackground: " + e.toString());
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
