package com.hero.zhaoq.viewpagerdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hero.zhaoq.viewpagerdemo.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Package_name:com.hero.zhaoq.viewpagerdemo.adapter
 * Author:zhaoQiang
 * Email:zhao_hero@163.com
 * Date:2016/11/27  17:24
 */
public class ListViewAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<HashMap<String,Object>> list;

    public ListViewAdapter(Context context,  ArrayList<HashMap<String, Object>> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        int ret =0;
        if(list!= null){
            ret = list.size();
        }
        return ret;
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

            ViewHolder holder = null;

            if(convertView == null){

                convertView = LayoutInflater.from(context).inflate(R.layout.item_view,null,true);

                holder = new ViewHolder(convertView);

                convertView.setTag(holder);
            }

            holder = (ViewHolder) convertView.getTag();
            //设置数据
            holder.setData(i);

        return convertView;
    }

    //每次添加  五条数据
    public void addListData(int i) {
        for(int a=0;a<i;a++){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("item","这是一个item");
            list.add(map);
        }
    }


    class ViewHolder{

        TextView item;

        //查找  控件
        public ViewHolder(View view){
            item = (TextView) view.findViewById(R.id.item);
        }

        //绑定数据
        public void setData(int position){
            item.setText(list.get(position).get("item")+ ""+ position);
        }
    }

}
