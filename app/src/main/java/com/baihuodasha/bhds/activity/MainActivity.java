package com.baihuodasha.bhds.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.baihuodasha.bhds.R;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_welcome_page)
    TextView tv_welcom_page;
    int i = 0;
    private Timer mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        tv_welcom_page.setText("欢迎页");

        mTimer = new Timer();//计时器 3秒后关闭
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(i >= 3){
                    toFinish();
                }
                i++;
            }
        },0,1000);
    }

    private void toFinish() {
        if(mTimer !=null){
            mTimer.cancel();
            mTimer = null;
        }
        Intent intent = new Intent(this, MainActivityTabHost.class);
        startActivity(intent);
        overridePendingTransition(0,0);
        finish();
    }
}
