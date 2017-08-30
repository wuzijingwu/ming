package com.example.yingshi5;

import java.util.ArrayList;

import com.bawei.movie.R;
import com.bawei.movie.bean.Movie.Video_Rec;
import com.lidroid.xutils.BitmapUtils;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyVideoAdapter extends BaseExpandableListAdapter {

	Context context;
	ArrayList<Movie.Video_Rec> vlist;
	public MyVideoAdapter(Context context, ArrayList<Movie.Video_Rec> vlist) {
		// TODO Auto-generated constructor stub
		this.context=context;
		this.vlist=vlist;
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return vlist.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return "�����Ϣ��";
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return vlist.get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		convertView = View.inflate(context, android.R.layout.simple_list_item_1, null);
		TextView text1 = (TextView) convertView.findViewById(android.R.id.text1);
		text1.setText("     ��Ӱ���������:");
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		convertView = View.inflate(context, R.layout.act_item, null);
		ImageView image = (ImageView) convertView.findViewById(R.id.act_image);
		TextView name = (TextView) convertView.findViewById(R.id.act_name);
		BitmapUtils util=new BitmapUtils(context);
		util.display(image, vlist.get(childPosition).cover);
		name.setText(vlist.get(childPosition).title);
		Log.i("vlist-------", vlist.toString());
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}

}
