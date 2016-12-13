package com.hero.zhaoq.viewpagerdemo.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hero.zhaoq.viewpagerdemo.R;
import com.hero.zhaoq.viewpagerdemo.adapter.MyVPAdapter;

import java.util.ArrayList;

/**
 * Package_name:com.hero.zhaoq.viewpagerdemo.view
 * Author:zhaoQiang
 * Email:zhao_hero@163.com
 * Date:2016/11/27  17:16
 * 自定义实现  图片的循环
 */
public class LooperViewPager extends ViewPager {

    //传递过来的图片数组，这个必须更换，真实项目中有可能是一个集合
    private ArrayList<String> imageUrls;

    private static final int NEXT = 99;//切换下一张图片的标志

    private boolean isRunning = false;//是否自动轮播的标志，默认不自动轮播

    public LooperViewPager(Context context) {
        super(context);
    }

    public LooperViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setImageUrls(ArrayList<String> imageUrls) {
        this.imageUrls = imageUrls;
        setAdapter(new MyVPAdapter(getContext(),imageUrls));
    }

    public ArrayList<String> getImageUrls(){
        return imageUrls;
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case NEXT:
                    if(isRunning==true){
                        //设置当前item+1；相当于设置下一个item，然后余图片数量；
                        setCurrentItem(getCurrentItem()+1);
                        //然后发送空消息延时2秒
                        handler.sendEmptyMessageDelayed(NEXT,2000);
                    }
                    break;
            }
        }
    };

    private int downTime = 0;

    //按下的   XY的坐标
    private int downX = 0;
    private int downY = 0;

    //添加  点击事件
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                //判断是否需要滑动：  获取 当前  点击点
                downX = (int) ev.getX();
                downY = (int) ev.getY();
                downTime = (int) System.currentTimeMillis();

                handler.removeMessages(NEXT);
                break;

            case MotionEvent.ACTION_UP:
                //计算当前  移动距离 以及滑动时间：
                int upX = (int) ev.getX();
                int upY = (int) ev.getY();
                int disX = Math.abs(upX -downX);
                int disY = Math.abs(upY -downY);
                int upTime=(int) System.currentTimeMillis();
                //  计算点击距离  以及点击时间长度
                if(upTime - downTime < 500 && disX -disY < 5){
                    //点击事件处理  处理事件：
                    if(onItemClickListener != null){
                        //当前点击项
                        onItemClickListener.onItemClick(getCurrentItem()%imageUrls.size());
                    }
                }

                //开启轮播
                startRoll();

                break;

            case MotionEvent.ACTION_CANCEL:

                startRoll();

                break;

            default:
                break;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    //当控件挂载到页面上会调用此方法
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    //当控件从页面上移除的时候会调用此方法
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        isRunning = false;
        handler.removeMessages(NEXT);
    }

    //开始轮播
    public void startRoll() {
        isRunning = true;
        handler.sendEmptyMessageDelayed(NEXT,1000);
    }

    private  LinearLayout inViewPager;//放ViewPager

    private ArrayList<ImageView> pointList;  //存放  指示器的控件集合
    private LinearLayout dots;//

    /**
     * 添加  滚动指示器
     * @param headView
     */
    public void addPoint(View headView) {
        inViewPager = (LinearLayout)headView.findViewById(R.id.my_looperViewpager);
        dots = (LinearLayout) headView.findViewById(R.id.dots_All);  //获取指示器
        pointList = new ArrayList<ImageView>();

        if(pointList.size()!=0){
            //滑动指示器：
            pointList.clear();  //清除  之前的数据
        }
        if(dots!=null){
            dots.removeAllViews();//移除  所有的view
        }
        //遍历 重新 添加  数据
        for(int x =0;x< imageUrls.size();x++){
            ImageView imageView = new ImageView(getContext());
            //设置 资源
            imageView.setImageResource(R.drawable.dot_normal);
            //导报的时候指示点的父View是什么布局就导什么布局的params,这里导的是线性布局
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.leftMargin=36;
            dots.addView(imageView,params);
            pointList.add(imageView);
        }
        inViewPager.addView(this);
    }

    /**
     * 获取 当前  指示器集合
     * @return
     */
    public ArrayList<ImageView> getPointList() {
        return pointList;
    }

    //--
    /**
     * 添加   点击事件：  用于计算  是否要发生Viewpager的滑动事件
     */
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener{
        public void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
