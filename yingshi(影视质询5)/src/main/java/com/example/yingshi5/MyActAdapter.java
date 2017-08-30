package com.example.yingshi5;

import java.util.ArrayList;

import com.bawei.movie.R;
import com.bawei.movie.bean.Movie.Act_s;
import com.lidroid.xutils.BitmapUtils;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyActAdapter extends BaseExpandableListAdapter {

	Context context;
	ArrayList<Movie.Act_s> alist;
	
	public MyActAdapter(Context context, ArrayList<Movie.Act_s> alist) {
		this.context=context;
		this.alist=alist;
		
	}



	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return alist.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return "���ݣ�";
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return alist.get(childPosition);
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
		convertView = View.inflate(context, android.R.layout.simple_list_item_1, null);
		TextView textview = (TextView) convertView.findViewById(android.R.id.text1);
		textview.setText("    ����");
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		convertView = View.inflate(context, R.layout.act_item, null);
		ImageView act_image=(ImageView) convertView.findViewById(R.id.act_image);
		TextView act_name=(TextView) convertView.findViewById(R.id.act_name);
		BitmapUtils util=new BitmapUtils(context);
		util.display(act_image, alist.get(childPosition).image);
		act_name.setText(alist.get(childPosition).name);
		Log.i("alist======", alist.toString());
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}

}
