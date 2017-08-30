package com.example.widget;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.R;

public class FloatService extends Service {

	WindowManager wm = null;
	WindowManager.LayoutParams wmParams = null;
	View view;
	private float mTouchStartX;
	private float mTouchStartY;
	private float x;
	private float y;
	int state;
	TextView tx1;
	TextView tx;
	ImageView iv;
	private float StartX;
	private float StartY;
	int delaytime = 1000;

	@Override
	public void onCreate() {
		Log.d("FloatService", "onCreate");
		super.onCreate();
		view = LayoutInflater.from(this).inflate(R.layout.floating, null);
		tx = (TextView) view.findViewById(R.id.memunused);
		tx1 = (TextView) view.findViewById(R.id.memtotal);
		tx.setText("" + memInfo.getmem_UNUSED(this) + "KB");
		tx1.setText("" + memInfo.getmem_TOLAL() + "KB");
		iv = (ImageView) view.findViewById(R.id.img2);
		iv.setVisibility(View.GONE);
		createView();
		handler.postDelayed(task, delaytime);
	}

	private void createView() {
		SharedPreferences shared = getSharedPreferences("float_flag",
				Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = shared.edit();
		editor.putInt("float", 1);
		editor.commit();
		// 锟斤拷取WindowManager
		wm = (WindowManager) getApplicationContext().getSystemService("window");
		// 锟斤拷锟斤拷LayoutParams(全锟街憋拷锟斤拷锟斤拷夭锟斤拷锟�
		wmParams = ((MyApplication) getApplication()).getMywmParams();
		wmParams.type = 2002;
		wmParams.flags |= 8;
		wmParams.gravity = Gravity.LEFT | Gravity.TOP; // 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷辖锟�
		// 锟斤拷锟斤拷幕锟斤拷锟较斤拷为原锟姐，锟斤拷锟斤拷x锟斤拷y锟斤拷始值
		wmParams.x = 0;
		wmParams.y = 0;
		// 锟斤拷锟斤拷锟斤拷诔锟斤拷锟斤拷锟斤拷
		wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
		wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
		wmParams.format = 1;

		wm.addView(view, wmParams);

		view.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				// 锟斤拷取锟斤拷锟斤拷锟侥伙拷锟斤拷锟疥，锟斤拷锟斤拷锟斤拷幕锟斤拷锟较斤拷为原锟斤拷
				x = event.getRawX();
				y = event.getRawY() - 25; // 25锟斤拷系统状态8锟侥高讹拷
				Log.i("currP", "currX" + x + "====currY" + y);// 锟斤拷锟斤拷锟斤拷息
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					state = MotionEvent.ACTION_DOWN;
					StartX = x;
					StartY = y;
					// 锟斤拷取锟斤拷锟絍iew锟斤拷锟斤拷辏拷锟斤拷源锟絍iew锟斤拷锟较斤拷为原锟斤拷
					mTouchStartX = event.getX();
					mTouchStartY = event.getY();
					Log.i("startP", "startX" + mTouchStartX + "====startY"
							+ mTouchStartY);// 锟斤拷锟斤拷锟斤拷息
					break;
				case MotionEvent.ACTION_MOVE:
					state = MotionEvent.ACTION_MOVE;
					updateViewPosition();
					break;

				case MotionEvent.ACTION_UP:
					state = MotionEvent.ACTION_UP;

					updateViewPosition();
					showImg();
					mTouchStartX = mTouchStartY = 0;
					break;
				}
				return true;
			}
		});

		iv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent serviceStop = new Intent();
				serviceStop.setClass(FloatService.this, FloatService.class);
				stopService(serviceStop);
			}
		});

	}

	public void showImg() {
		if (Math.abs(x - StartX) < 1.5 && Math.abs(y - StartY) < 1.5
				&& !iv.isShown()) {
			iv.setVisibility(View.VISIBLE);
		} else if (iv.isShown()) {
			iv.setVisibility(View.GONE);
		}
	}

	private Handler handler = new Handler();
	private Runnable task = new Runnable() {
		public void run() {
			// TODO Auto-generated method stub
			dataRefresh();
			handler.postDelayed(this, delaytime);
			wm.updateViewLayout(view, wmParams);
		}
	};

	public void dataRefresh() {
		tx.setText("" + memInfo.getmem_UNUSED(this) + "KB");
		tx1.setText("" + memInfo.getmem_TOLAL() + "KB");
	}

	private void updateViewPosition() {
		// 锟斤拷锟铰革拷锟斤拷锟斤拷锟斤拷位锟矫诧拷锟斤拷
		wmParams.x = (int) (x - mTouchStartX);
		wmParams.y = (int) (y - mTouchStartY);
		wm.updateViewLayout(view, wmParams);
	}

	@Override
	public void onStart(Intent intent, int startId) {
		Log.d("FloatService", "onStart");
		setForeground(true);
		super.onStart(intent, startId);
	}

	@Override
	public void onDestroy() {
		handler.removeCallbacks(task);
		Log.d("FloatService", "onDestroy");
		wm.removeView(view);
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
}
