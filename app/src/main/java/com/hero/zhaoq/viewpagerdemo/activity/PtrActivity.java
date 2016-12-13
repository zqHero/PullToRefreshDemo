package com.hero.zhaoq.viewpagerdemo.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.hero.zhaoq.viewpagerdemo.R;
import com.hero.zhaoq.viewpagerdemo.adapter.ListViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/***
 * pullToRefreshListView 修改下拉  上拉刷新 demo
 */
public class PtrActivity extends AppCompatActivity implements PullToRefreshBase.OnRefreshListener2{

    private static PullToRefreshListView ptrListView;

    private static ArrayList<HashMap<String, Object>> list;

    private static ListViewAdapter adapter;

    //接受  刷新后的  UI操作
    static SecureHandler handler = new SecureHandler();

    private static class SecureHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case 5:  //下拉  刷新
                    ptrListView.onRefreshComplete();
                    break;
                case 6:  //上拉  加载
                    adapter.addListData(1);
                    adapter.notifyDataSetChanged();
                    ptrListView.onRefreshComplete();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ptr);

        ptrListView = (PullToRefreshListView) findViewById(R.id.mylistView);
        ptrListView.setMode(PullToRefreshBase.Mode.BOTH);

        initData();

        initListView(); //initPtr
    }

    //初始化   ListView
    private void initListView() {

        adapter = new ListViewAdapter(this,list);
        ptrListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        ptrListView.setOnRefreshListener(this);
        ptrListView.setOverScrollMode(View.OVER_SCROLL_NEVER);

        ptrListView.setScrollingWhileRefreshingEnabled(true);
    }

    //初始化数据
    private void initData() {
        list = new ArrayList<HashMap<String, Object>>();
        for(int i=0;i<10;i++){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("item","这是一个item");
            list.add(map);
        }
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        //发送线程请求数据
        handler.sendEmptyMessageDelayed(5,2000);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        //上拉加载
        handler.sendEmptyMessageDelayed(6,2000);
    }

}
