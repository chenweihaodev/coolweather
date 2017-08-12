package com.coolweather.android.view;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.coolweather.android.R;
import com.coolweather.android.common.util.LogUtil;
import com.coolweather.android.view.weather.WeatherActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherId = sharedPreferences.getString("weatherId",null);
        if(weatherId!=null){
            WeatherActivity.activityStart(this,weatherId);
            finish();
        }
    }

}
