package com.example.dayandnightdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SecondActivity extends ActionBarActivity {
	private LinearLayout linear;
	private TextView text;

	private MenuItem changeItem;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		changeItem = menu.findItem(R.id.action_change);
		if (!Util.flag)// 夜晚
			changeItem.setTitle(R.string.action_change_day);
		else
			changeItem.setTitle(R.string.action_change_night);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		} else if (id == R.id.action_change) {

			Intent intent = new Intent();
			if (item.getTitle().toString()
					.equals(getString(R.string.action_change_night)))
				Util.flag = false;// 夜晚
			else if (item.getTitle().toString()
					.equals(getString(R.string.action_change_day)))
				Util.flag = true;// 白天

			intent.setAction(Util.filter);
			this.sendBroadcast(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		init();
		IntentFilter filter = new IntentFilter();
		filter.addAction(Util.filter);
		registerReceiver(receiver, filter);
	}

	private void init() {
		// TODO Auto-generated method stub
		linear = (LinearLayout) findViewById(R.id.second_linear);
		text = (TextView) findViewById(R.id.second_text);
		if (!Util.flag)// 夜晚
		{
			linear.setBackgroundColor(Color.BLACK);
			text.setBackgroundColor(Color.BLACK);
			text.setTextColor(Color.WHITE);
		}
	}

	BroadcastReceiver receiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			if (!Util.flag) {
				linear.setBackgroundColor(Color.BLACK);
				text.setBackgroundColor(Color.BLACK);
				text.setTextColor(Color.WHITE);
				changeItem.setTitle(R.string.action_change_day);
			} else if (Util.flag) {
				linear.setBackgroundColor(Color.WHITE);
				text.setBackgroundColor(Color.WHITE);
				text.setTextColor(Color.BLACK);
				changeItem.setTitle(R.string.action_change_night);
			}
		}
	};

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(receiver);
	}

}
