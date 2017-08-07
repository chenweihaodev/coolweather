package com.coolweather.android.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.coolweather.android.R;
import com.coolweather.android.common.open.guolinchina.AreaRequestService;
import com.coolweather.android.common.util.LogUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

    }

}
