package com.example.dayandnightdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * （用的比较新的ADT）,最下面会有demo链接，导入自己的项目中的时候记得导入你自己的appcompat_v7，
 * 具体方法是鼠标右键DayAndNightDemo--》Properties--》Android--》Library--》remove--》add。
 * 
 * @author Administrator
 * 
 */
public class MainActivity extends ActionBarActivity {
	private static MenuItem changeItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		View rootView;
		TextView helloText;
		Button button;
		BroadcastReceiver receiver = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				if (!Util.flag) {
					rootView.setBackgroundColor(Color.BLACK);
					helloText.setBackgroundColor(Color.BLACK);
					helloText.setTextColor(Color.WHITE);
					button.setBackgroundColor(Color.DKGRAY);
					button.setTextColor(Color.WHITE);
					MainActivity.changeItem
							.setTitle(R.string.action_change_day);
				} else if (Util.flag) {
					rootView.setBackgroundColor(Color.WHITE);
					helloText.setBackgroundColor(Color.WHITE);
					helloText.setTextColor(Color.BLACK);
					button.setBackgroundColor(Color.GRAY);
					button.setTextColor(Color.BLACK);
					MainActivity.changeItem
							.setTitle(R.string.action_change_night);
				}
			}
		};

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			helloText = (TextView) rootView.findViewById(R.id.main_hello);
			button = (Button) rootView.findViewById(R.id.main_btn);
			button.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(getActivity(),
							SecondActivity.class);
					getActivity().startActivity(intent);
				}
			});
			IntentFilter filter = new IntentFilter();
			filter.addAction(Util.filter);
			getActivity().registerReceiver(receiver, filter);
			return rootView;
		}

		@Override
		public void onDestroy() {
			// TODO Auto-generated method stub
			super.onDestroy();
			getActivity().unregisterReceiver(receiver);
			android.os.Process.killProcess(android.os.Process.myPid());
		}

	}

}
