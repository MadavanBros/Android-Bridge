package com.madavan.bridge.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	public void onLogin(View view) {
		String username = ((TextView) this.findViewById(R.id.LoginActivity_usernameText)).toString();
		String password = ((TextView) this.findViewById(R.id.LoginActivity_passwordText)).toString();
		
		Intent i = new Intent(this, GameRoomActivity.class);
		i.putExtra("username", username);
		i.putExtra("password", password);
		startActivity(i);
	}
}
