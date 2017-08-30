package com.example.yingshi5;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bawei.movie.MainActivity;
import com.bawei.movie.R;
import com.bawei.movie.bean.Movie;
import com.bawei.movie.bean.Movie.Act_s;
import com.bawei.movie.bean.Movie.Playlinks;
import com.bawei.movie.bean.Movie.Video_Rec;
import com.lidroid.xutils.BitmapUtils;

public class MyAdapter extends BaseAdapter {

	MainActivity context;
	Movie movie;

	public MyAdapter(MainActivity context, Movie movie) {
		super();
		this.context = context;
		this.movie = movie;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return movie;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = View.inflate(context, R.layout.item, null);
		ImageView item_image = (ImageView) view.findViewById(R.id.item_image);
		TextView title = (TextView) view.findViewById(R.id.title);
		TextView act=(TextView) view.findViewById(R.id.act);
		
		TextView desc = (TextView) view.findViewById(R.id.desc);
		TextView tag = (TextView) view.findViewById(R.id.tag);
		TextView area = (TextView) view.findViewById(R.id.area);
		TextView dir = (TextView) view.findViewById(R.id.dir);
		
		BitmapUtils bitmapUtils = new BitmapUtils(context);
		bitmapUtils.display(item_image, movie.result.cover);
		title.setText("��Ӱ���ƣ�"+movie.result.title);
		act.setText(movie.result.act);
		desc.setText("��Ӱ��飺"+movie.result.desc);
		tag.setText("��Ӱ���ͣ�"+movie.result.tag);
		area.setText("������"+movie.result.area);
		dir.setText("���ݣ�"+movie.result.dir);
		
		return view;
	}
}
