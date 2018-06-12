package com.baihuodasha.bhds.activity.myself;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;
import com.baihuodasha.bhds.R;

public class ActivityMyselfInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myself_info);
        ButterKnife.bind(this);
    }
}
