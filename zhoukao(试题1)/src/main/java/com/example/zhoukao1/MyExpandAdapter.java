package com.example.zhoukao1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

/**
 * Created by dell on 2017/7/13.
 */

public class MyExpandAdapter extends BaseExpandableListAdapter {

  private  Context context;
 private   MenuInfo menuInfo;


    public MyExpandAdapter(Context context,MenuInfo menuInfo){

            this.context=context;
        this.menuInfo=menuInfo;

    }



    @Override
    public int getGroupCount() {
        return menuInfo.getResult().getData().size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return menuInfo.getResult().getData().get(groupPosition).getSteps().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
