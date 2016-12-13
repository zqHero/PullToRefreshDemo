package com.hero.zhaoq.viewpagerdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hero.zhaoq.viewpagerdemo.R;

import java.util.ArrayList;

/**
 * Package_name:com.hero.zhaoq.viewpagerdemo.adapter
 * Author:zhaoQiang
 * Email:zhao_hero@163.com
 * Date:2016/12/3  18:51
 */
public class GridViewAdapter extends BaseAdapter {

    private Context context;

    private String[] title;

    private ArrayList<Integer> selectors;

    public GridViewAdapter(Context context,  String[] title, ArrayList<Integer> selectors) {
        this.context = context;
        this.title = title;
        this.selectors = selectors;
    }

    public int getCount() {
        return selectors.size();
    }
    public Object getItem(int position) {
        return position;
    }
    public long getItemId(int position) {
        return position;
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if(convertView ==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_view_item,null,false);

            viewHolder = new ViewHolder();

            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.grid_view_item_image);
            viewHolder.textView  = (TextView) convertView.findViewById(R.id.grid_view_item_text);

            convertView.setTag(viewHolder);
        }

        viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.imageView.setBackgroundResource(selectors.get(position));
        viewHolder.textView.setText(title[position]);

        return convertView;
    }

    class ViewHolder{

        private ImageView imageView;

        private TextView textView;

        public ImageView getImageView() {
            return imageView;
        }

        public void setImageView(ImageView imageView) {
            this.imageView = imageView;
        }

        public TextView getTextView() {
            return textView;
        }

        public void setTextView(TextView textView) {
            this.textView = textView;
        }
    }


}
