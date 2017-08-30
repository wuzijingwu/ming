package com.example.gongzheng1;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dell on 2017/8/2.
 */

public class SecondActivity extends Activity {

//    private ListView listview;

    Context mContext = null;

    /**
     * 获取库Phon表字段
     **/
    private static final String[] PHONES_PROJECTION = new String[]{
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Photo.PHOTO_ID, ContactsContract.CommonDataKinds.Phone.CONTACT_ID};

    /**
     * 联系人显示名称
     **/
    private static final int PHONES_DISPLAY_NAME_INDEX = 0;

    /**
     * 电话号码
     **/
    private static final int PHONES_NUMBER_INDEX = 1;

    /**
     * 头像ID
     **/
    private static final int PHONES_PHOTO_ID_INDEX = 2;

    /**
     * 联系人的ID
     **/
    private static final int PHONES_CONTACT_ID_INDEX = 3;


    /**
     * 联系人名称
     **/
    private ArrayList<String> mContactsName = new ArrayList<String>();

    /**
     * 联系人头像
     **/
    private ArrayList<String> mContactsNumber = new ArrayList<String>();

    /**
     * 联系人头像
     **/
//    private ArrayList<Bitmap> mContactsPhonto = new ArrayList<Bitmap>();

//    ListView mListView = null;
    MyListAdapter myAdapter = null;
    private ListView mListView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item);


//        mContext = this;
//        mListView = this.getListView();
        mListView = (ListView) findViewById(R.id.listview);


        /**得到手机通讯录联系人信息**/
        getPhoneContacts();

        myAdapter = new MyListAdapter(this);
        mListView.setAdapter(myAdapter);


    }

    /**
     * 得到手机通讯录联系人信息
     **/
    private void getPhoneContacts() {
        ContentResolver resolver = getContentResolver();

        // 获取手机联系人
        Cursor phoneCursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, PHONES_PROJECTION, null, null, null);


        if (phoneCursor != null) {
            while (phoneCursor.moveToNext()) {

                //得到手机号码
                String phoneNumber = phoneCursor.getString(PHONES_NUMBER_INDEX);
                //当手机号码为空的或者为空字段 跳过当前循环
                if (TextUtils.isEmpty(phoneNumber))
                    continue;

                //得到联系人名称
                String contactName = phoneCursor.getString(PHONES_DISPLAY_NAME_INDEX);

                //得到联系人ID
                Long contactid = phoneCursor.getLong(PHONES_CONTACT_ID_INDEX);

                //得到联系人头像ID
                Long photoid = phoneCursor.getLong(PHONES_PHOTO_ID_INDEX);


                mContactsName.add(contactName);
                mContactsNumber.add(phoneNumber);
//                mContactsPhonto.add(contactPhoto);
            }

            phoneCursor.close();
        }
    }


    class MyListAdapter extends BaseAdapter {

        private ImageView iamge;
        private TextView title;
        private TextView text;

        public MyListAdapter(Context context) {
            mContext = context;
        }

        public int getCount() {
            //设置绘制数量
            return mContactsName.size();
        }

        @Override
        public boolean areAllItemsEnabled() {
            return false;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        @Override

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView iamge = null;
            TextView title = null;
            TextView text = null;
            if (convertView == null) {
                convertView = convertView.inflate(SecondActivity.this, R.layout.lay, null);
                iamge = (ImageView) convertView.findViewById(R.id.image);
                title = (TextView) convertView.findViewById(R.id.text1);
                text = (TextView) convertView.findViewById(R.id.text2);
            }
            //绘制联系人名称
            title.setText(mContactsName.get(position));
            //绘制联系人号码
            text.setText(mContactsNumber.get(position));
            //绘制联系人头像

            return convertView;
        }


    }}
