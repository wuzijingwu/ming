package com.example.wuzijing20170805;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView tv = null;
    ListView lv = null;
    Button btn_selectAll = null;
    Button btn_inverseSelect = null;
    Button btn_calcel = null;
    String name[] = { "G1", "G2", "G3", "G4", "G5", "G6", "G7", "G8", "G9",
            "G10", "G11", "G12", "G13", "G14" };

    ArrayList<String> listStr = null;
    private List<HashMap<String, Object>> list = null;
    private MyAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) this.findViewById(R.id.tv);
        lv = (ListView) this.findViewById(R.id.lv);
        btn_selectAll = (Button) this.findViewById(R.id.selectall);
        btn_inverseSelect = (Button) this.findViewById(R.id.inverseselect);
        btn_calcel = (Button) this.findViewById(R.id.cancel);
        showCheckBoxListView();

        //全选
        btn_selectAll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                listStr = new ArrayList<String>();
                for(int i=0;i<list.size();i++){
                    MyAdapter.isSelected.put(i,true);
                    listStr.add(name[i]);
                }
                adapter.notifyDataSetChanged();//注意这一句必须加上，否则checkbox无法正常更新状态
                tv.setText("已选中"+listStr.size()+"项");
            }
        });

        //反选
        btn_inverseSelect.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                for(int i=0;i<list.size();i++){
                    if(MyAdapter.isSelected.get(i)==false){
                        MyAdapter.isSelected.put(i, true);
                        listStr.add(name[i]);
                    }
                    else{
                        MyAdapter.isSelected.put(i, false);
                        listStr.remove(name[i]);
                    }
                }
                adapter.notifyDataSetChanged();
                tv.setText("已选中"+listStr.size()+"项");
            }

        });

        //取消已选
        btn_calcel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                for(int i=0;i<list.size();i++){
                    if(MyAdapter.isSelected.get(i)==true){
                        MyAdapter.isSelected.put(i, false);
                        listStr.remove(name[i]);
                    }
                }
                adapter.notifyDataSetChanged();
                tv.setText("已选中"+listStr.size()+"项");
            }

        });
    }

    // 显示带有checkbox的listview
    public void showCheckBoxListView() {
        list = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < name.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("item_tv", name[i]);
            map.put("item_cb", false);
            list.add(map);

            adapter = new MyAdapter(this, list, R.layout.listviewitem,
                    new String[] { "item_tv", "item_cb" }, new int[] {
                    R.id.item_tv, R.id.item_cb });
            lv.setAdapter(adapter);
            listStr = new ArrayList<String>();
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View view,
                                        int position, long arg3) {
                    MyAdapter.ViewHolder holder = (MyAdapter.ViewHolder) view.getTag();
                    // 在每次获取点击的item时改变checkbox的状态
                    holder.cb.toggle();
                    // 同时修改map的值保存状态
                    MyAdapter.isSelected.put(position, holder.cb.isChecked());
                    if (holder.cb.isChecked() == true) {
                        listStr.add(name[position]);
                    } else {
                        listStr.remove(name[position]);
                    }
                    tv.setText("已选中"+listStr.size()+"项");
                }

            });
        }
    }

    //为listview自定义适配器内部类
    public static class MyAdapter extends BaseAdapter {

        public static HashMap<Integer, Boolean> isSelected;
        private Context context = null;
        private LayoutInflater inflater = null;
        private List<HashMap<String, Object>> list = null;
        private String keyString[] = null;
        private String itemString = null; // 记录每个item中textview的值
        private int idValue[] = null;// id值

        public MyAdapter(Context context, List<HashMap<String, Object>> list,
                         int resource, String[] from, int[] to) {
            this.context = context;
            this.list = list;
            keyString = new String[from.length];
            idValue = new int[to.length];
            System.arraycopy(from, 0, keyString, 0, from.length);
            System.arraycopy(to, 0, idValue, 0, to.length);
            inflater = LayoutInflater.from(context);
            init();
        }

        // 初始化 设置所有checkbox都为未选择
        public void init() {
            isSelected = new HashMap<Integer, Boolean>();
            for (int i = 0; i < list.size(); i++) {
                isSelected.put(i, false);
            }
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int arg0) {
            return list.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup arg2) {
            ViewHolder holder = null;
            if (holder == null) {
                holder = new ViewHolder();
                if (view == null) {
                    view = inflater.inflate(R.layout.listviewitem, null);
                }
                holder.tv = (TextView) view.findViewById(R.id.item_tv);
                holder.cb = (CheckBox) view.findViewById(R.id.item_cb);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            HashMap<String, Object> map = list.get(position);
            if (map != null) {
                itemString = (String) map.get(keyString[0]);
                holder.tv.setText(itemString);
            }
            holder.cb.setChecked(isSelected.get(position));
            return view;
        }

//        package simtice.test.listview.viewholder;
//        import android.widget.CheckBox;
//        import android.widget.TextView;

        public class ViewHolder {
            public TextView tv = null;
            public CheckBox cb = null;
        }


    }
}