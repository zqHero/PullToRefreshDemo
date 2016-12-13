package com.hero.zhaoq.viewpagerdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hero.zhaoq.viewpagerdemo.activity.LooperPictureAct;
import com.hero.zhaoq.viewpagerdemo.activity.PtrActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button ptrButton,looperPicture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ptrButton = (Button) findViewById(R.id.OnPullToRefreshListViewDemo);
        looperPicture = (Button) findViewById(R.id.looperPicture);

        ptrButton.setOnClickListener(this);
        looperPicture.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            //PullToRefreshListView 实例：
            case R.id.OnPullToRefreshListViewDemo:
                startActivity(new Intent(MainActivity.this,PtrActivity.class));
                break;

            //轮播图片
            case R.id.looperPicture:
                startActivity(new Intent(MainActivity.this,LooperPictureAct.class));
                break;

            default:break;
        }
    }
}
