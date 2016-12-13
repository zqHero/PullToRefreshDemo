package com.hero.zhaoq.viewpagerdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Package_name:com.hero.zhaoq.viewpagerdemo.view
 * Author:zhaoQiang
 * Email:zhao_hero@163.com
 * Date:2016/12/3  20:17
 *
 * 重写测量模式  解决冲突    只显示一行的问题
 */
public class MyGridView extends GridView {


    public MyGridView(Context context) {
        super(context);
    }

    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        setMeasuredDimension(measureWidth(widthMeasureSpec),measureHigt(heightMeasureSpec));

        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, expandSpec);

    }

    private int measureHigt(int heightMeasureSpec) {
        int result = 0;
        int specModel = MeasureSpec.getMode(heightMeasureSpec);
        int specSize = MeasureSpec.getSize(heightMeasureSpec);
        if(specModel == MeasureSpec.EXACTLY){
            result = specSize;
        }else{
            result = 200;//默认值
            if(specModel == MeasureSpec.AT_MOST){
                result = Math.min(result,specSize);
            }
        }
        return  result;
    }
}
