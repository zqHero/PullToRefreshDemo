package com.hero.zhaoq.viewpagerdemo.adapter;

import android.content.Context;
import android.media.Image;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hero.zhaoq.viewpagerdemo.R;
import com.hero.zhaoq.viewpagerdemo.activity.LooperPictureAct;

import java.util.ArrayList;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Package_name:com.hero.zhaoq.viewpagerdemo.adapter
 * Author:zhaoQiang
 * Email:zhao_hero@163.com
 * Date:2016/12/1  22:31
 */
public class MyVPAdapter extends PagerAdapter{

    public ArrayList<String> imageUrls;

    private Context context;

    public MyVPAdapter(Context context, ArrayList<String> urls) {
        this.imageUrls = urls;
        this.context = context;
    }

    @Override
    public int getCount() {
        //返回最大值   表明   方便以后  循环滑动
        return Integer.MAX_VALUE;
    }



    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //这个必须取余数，不然会下标越界
        position=position%imageUrls.size();

        ImageView imageView = (ImageView) View.inflate(context, R.layout.image_view,null);

        //自己设置  图片  网址：
        //imageView.setImageResource(imageUrls.get(position));

        //网路获取
        Picasso.with(context).load(imageUrls.get(position))
                .error(R.mipmap.failure_one)
                .into(imageView);

        container.addView(imageView);

        return imageView;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
