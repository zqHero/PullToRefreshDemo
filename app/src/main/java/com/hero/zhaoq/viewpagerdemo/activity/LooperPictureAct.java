package com.hero.zhaoq.viewpagerdemo.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.hero.zhaoq.viewpagerdemo.MainActivity;
import com.hero.zhaoq.viewpagerdemo.R;
import com.hero.zhaoq.viewpagerdemo.adapter.ListViewAdapter;
import com.hero.zhaoq.viewpagerdemo.adapter.GridViewAdapter;
import com.hero.zhaoq.viewpagerdemo.view.LooperViewPager;

import java.util.ArrayList;
import java.util.HashMap;

public class LooperPictureAct extends AppCompatActivity implements LooperViewPager.OnItemClickListener{

    private PullToRefreshListView ptrListView;
    private LooperViewPager viewPager;

    private ArrayList<HashMap<String, Object>> list;

    private View headView;

    public int lastPosition=0; //记录指示点的  位置

    private static final int TOAST = 0x98;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch(msg.what){

                case TOAST:
                    //
                    Toast.makeText(LooperPictureAct.this,"GridView被点击位置："+msg.obj.toString(),Toast.LENGTH_SHORT).show();

                    break;

                default:break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looper_picture);

        headView = LayoutInflater.from(this).inflate(R.layout.looper_head,null,true);

        //1，初始化  数据
        initHeaderLooperImage();
        initGridView();
        initPtrListView();
    }



    // 1.1 初始化头布局    viewPager对象
    public void initHeaderLooperImage(){
        //代码中创建对象   初始化 ViewPager
        viewPager = new LooperViewPager(this);

        initLooperImageUrl();

        //--------注意方法执行顺序
        //添加事件
        viewPager.setImageUrls(imageUrls);  //初始化数据
        viewPager.setCurrentItem(52);  //设置初始化时  显示的界面  为第五十项

        viewPager.addPoint(headView);//添加  滚动指示器
        viewPager.startRoll(); //开启循环

        //1.2   添加ViewPager  滚动监听事件  改变指示器位置
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                //改变滚动指示器的位置：
                position = position % viewPager.getImageUrls().size();

                //切换指示器的点：  获取上一个指示点的ImageView  移动到当前的
                viewPager.getPointList().get(lastPosition).setImageResource(R.drawable.dot_normal);
                viewPager.getPointList().get(position).setImageResource(R.drawable.dot_focus);
                lastPosition = position;
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        viewPager.setOnItemClickListener(this);

//        //1.3  设置切换动画：
//        viewPager.setPageTransformer(true,new DepthPageTransformer());
//
//        //改变viewPager切换动画的   切换时间
//        Field mField = null;
//        FixedSpeedScroller mScroller = null;
//        try {
//            mField = ViewPager.class.getDeclaredField("mScroller");
//            mField.setAccessible(true);
//            mScroller = new FixedSpeedScroller(viewPager.getContext(),new AccelerateInterpolator());
//            mField.set(viewPager, mScroller);
//            mScroller.setmDuration(1000);
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }

    }

    /**
     * 2.1  初始化 GridView
     */
    private void initGridView() {

        //初始化  GridView
        GridView gridView = (GridView) headView.findViewById(R.id.ptr_gridView);

        ArrayList<Integer> selectors = new ArrayList<>();
        selectors.add( R.drawable.selector1);
        selectors.add( R.drawable.selector2);
        selectors.add( R.drawable.selector3);
        selectors.add( R.drawable.selector4);
        selectors.add( R.drawable.selector5);
        selectors.add( R.drawable.selector6);
        //
        String[] titles = {"模块1","模块2","模块3","模块4","模块5","模块6"};

        GridViewAdapter adapter = new GridViewAdapter(this,titles,selectors);

        gridView.setAdapter(adapter);

        //1.3  添加  点击事件
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Message msg = new Message();
                msg.what = TOAST;
                msg.obj = i;
                handler.sendMessage(msg);
            }
        });
    }


    /**
     * 3.1 初始化  listView
     */
    private void initPtrListView() {
        ptrListView = (PullToRefreshListView) findViewById(R.id.ptrListV_addVP);

        ListView listView = ptrListView.getRefreshableView();

        //***   3.2  将  头布局   添加进  Listview 中   完成  整个布局 的编写
         listView.addHeaderView(headView);

        //初始化  数据和适配器
        initPtrData();
        ListViewAdapter adapter = new ListViewAdapter(this,list);
        ptrListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    //初始化数据
    private void initPtrData() {
        list = new ArrayList<HashMap<String, Object>>();
        for(int i=0;i<10;i++){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("item","这是一个item");
            list.add(map);
        }
    }

    /**
     * viewPager的点击事件：
     */
    @Override
    public void onItemClick(int position) {
        Toast.makeText(LooperPictureAct.this,"ViewPager被点击位置："+position,Toast.LENGTH_SHORT).show();
    }


    private ArrayList<String> imageUrls;

    public void initLooperImageUrl() {
        imageUrls = new ArrayList<String>();

        if(imageUrls.size()==0){
            imageUrls.add("http://www.buick.com.cn/img/verano/kv.jpg");
            imageUrls.add("http://news.xinhuanet.com/english/photo/2012-02/20/131419596_131n.jpg");
            imageUrls.add("http://www.buick.com.cn/img/lacrosse/features/exterior-3.jpg");
            imageUrls.add("http://img6.cache.netease.com/ent/2015/5/5/20150505122526e27dc.jpg");
        }
    }
}
