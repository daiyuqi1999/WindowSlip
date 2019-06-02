package net.hunau.windowslip;

//import android.support.v7.app.AppCompatActivity;
import java.util.Timer;
import java.util.TimerTask;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.view.Menu;

import net.hunau.windowslip.DBAdapter.DBAdapter;
import net.hunau.windowslip.Fragment.FragmentAdapter;
import net.hunau.windowslip.entity.User;

public class MainActivity extends AppCompatActivity  {

    FragmentAdapter mAdapter;
    ViewPager mPager;

    TextView title;
    TextView text;

    Timer mTimer;
    TimerTask mTask;
    int pageIndex = 1;
    boolean isTaskRun;

    String msg = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        setTitle("滑动窗口由王豪杰设计");

        /* 标题 */
        title = (TextView) findViewById(R.id.title);

        final DBAdapter dbAdepter = new DBAdapter(this);
        dbAdepter.open();
        for(int i = 0; i < 10; i++) {
            User user=new User();
            user.setName(""+i+(i+1)+(i+2));
            user.setPwd(""+i+i+i);
            user.setSexy("男");
            user.setIsused(true);
            dbAdepter.insert(user);
        }
        User[] users = dbAdepter.queryAllData();

        for (int i = 0; i < users.length; i++) {
            msg += users[i].toString() + "\n";
        }
        setMsg(msg);

        // 设置ViewPager
        mPager = (ViewPager) findViewById(R.id.viewpager);
        mAdapter = new FragmentAdapter(getSupportFragmentManager());
        mPager.setAdapter(mAdapter);

        mPager.setOnPageChangeListener(new OnPageChangeListener() {
            /* 更新手动滑动时的位置 */
            @Override
            public void onPageSelected(int position) {
                pageIndex = position;
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            /* state: 0空闲，1是滑行中，2加载完毕 */
            @Override
            public void onPageScrollStateChanged(int state) {
                // TODO Auto-generated method stub
                System.out.println("state:" + state);
                if (state == 0 && !isTaskRun) {
                    setCurrentItem();
                    startTask();
                } else if (state == 1 && isTaskRun)
                    stopTask();
            }
        });
    }


    public String getMsg(){
        return msg;
    }

    public void setMsg(String msg1){
        this.msg=msg1;
    }

    /**
     * 开启定时任务
     */
    private void startTask() {
        // TODO Auto-generated method stub
        isTaskRun = true;
        mTimer = new Timer();
        mTask = new TimerTask() {
            @Override
            public void run() {
                pageIndex++;
                mHandler.sendEmptyMessage(0);
            }
        };
        mTimer.schedule(mTask, 2 * 1000, 2 * 1000);// 这里设置自动切换的时间，单位是毫秒，2*1000表示2秒
    }

    // 处理EmptyMessage(0)
    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            setCurrentItem();
        }
    };

    /**
     * 处理Page的切换逻辑
     */
    private void setCurrentItem() {
        if (pageIndex == 0) {
            pageIndex = 6;
        } else if (pageIndex == 7) {
            pageIndex = 1;
        }
        mPager.setCurrentItem(pageIndex, false);// 取消动画
    }

    /**
     * 停止定时任务
     */
    private void stopTask() {
        // TODO Auto-generated method stub
        isTaskRun = false;
        mTimer.cancel();
    }

    public void onResume() {
        super.onResume();
        setCurrentItem();
        startTask();
    }

    @Override
    public void onPause() {
        super.onPause();
        stopTask();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}

