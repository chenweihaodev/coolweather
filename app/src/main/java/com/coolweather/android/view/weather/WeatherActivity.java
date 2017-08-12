package com.coolweather.android.view.weather;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.coolweather.android.R;
import com.coolweather.android.common.MyApplication;
import com.coolweather.android.common.open.guolin.weather.data.Forecast;
import com.coolweather.android.common.open.guolin.weather.data.Weather;
import com.coolweather.android.common.util.LogUtil;
import com.coolweather.android.contract.weather.IWeatherPresenter;
import com.coolweather.android.contract.weather.IWeatherView;
import com.coolweather.android.presenter.weather.WeatherPresenter;
import com.coolweather.android.service.AutoUpdateService;

public class WeatherActivity extends AppCompatActivity implements IWeatherView {

    public DrawerLayout drawerLayout;

    Button navButton;

    TextView titleCity;

    TextView titleUpdateTime;

    TextView nowTmp;

    TextView nowCond;

    LinearLayout forecastLayout;

    TextView aqiText;

    TextView pm25Text;

    TextView comfortText;

    TextView cwText;

    TextView sportText;

    ImageView background;

    SwipeRefreshLayout swipeRefreshLayout;

    public IWeatherPresenter weatherPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT>=21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.weather_layout);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        navButton = (Button)findViewById(R.id.nav_button);
        titleCity = (TextView)findViewById(R.id.title_city_name);
        titleUpdateTime = (TextView)findViewById(R.id.title_update_time);
        nowTmp = (TextView)findViewById(R.id.now_tmp_text);
        nowCond = (TextView)findViewById(R.id.now_cond_text);
        forecastLayout = (LinearLayout)findViewById(R.id.forecast_layout);
        aqiText = (TextView)findViewById(R.id.aqi_text);
        pm25Text = (TextView)findViewById(R.id.pm25_text);
        comfortText = (TextView)findViewById(R.id.comf_text);
        cwText = (TextView)findViewById(R.id.cw_text);
        sportText = (TextView)findViewById(R.id.sport_text);
        background = (ImageView)findViewById(R.id.background);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        weatherPresenter = new WeatherPresenter(this);

        swipeRefreshLayout.setOnRefreshListener(()-> {
            weatherPresenter.onRefresh();
        });

        navButton.setOnClickListener((view)->{
            drawerLayout.openDrawer(GravityCompat.START);
        });

        weatherPresenter.requestWeather(getIntent().getStringExtra("weatherId"));
    }

    public void showWeather(Weather weather){

        runOnUiThread(()->{
            titleCity.setText(weather.basic.city);
            titleUpdateTime.setText(weather.basic.update.loc);
            nowTmp.setText(weather.now.tmp);
            nowCond.setText(weather.now.cond.txt);

            forecastLayout.removeAllViews();
            for (Forecast forecast:weather.daily_forecast){
                View view = LayoutInflater.from(this).inflate(R.layout.forecast_item,forecastLayout,false);
                TextView date = (TextView)view.findViewById(R.id.date_text);
                TextView cond = (TextView)view.findViewById(R.id.cond_text);
                TextView max = (TextView)view.findViewById(R.id.tmp_max_text);
                TextView min = (TextView)view.findViewById(R.id.tmp_min_text);
                date.setText(forecast.date);
                cond.setText(forecast.cond.txt_d);
                max.setText(forecast.tmp.max);
                min.setText(forecast.tmp.min);
                forecastLayout.addView(view);
            }

            if (weather.aqi!=null){
                aqiText.setText(weather.aqi.city.aqi);
                pm25Text.setText(weather.aqi.city.pm25);
            }

            comfortText.setText("舒适度："+weather.suggestion.comf.txt);
            cwText.setText("洗车指数："+weather.suggestion.cw.txt);
            sportText.setText("运动建议："+weather.suggestion.sport.txt);

            Intent intent = new Intent(this, AutoUpdateService.class);
            startService(intent);
        });

    }

    @Override
    public void showToast(String msg) {
        runOnUiThread(()->{
            Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void setBackground(String url) {
        runOnUiThread(()->{
            Glide.with(WeatherActivity.this).load(url).into(background);
        });
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        runOnUiThread(()->{
            swipeRefreshLayout.setRefreshing(refreshing);
        });
    }

    public static void activityStart(Context context,String weatherId){
        Intent intent = new Intent(context,WeatherActivity.class);
        intent.putExtra("weatherId",weatherId);
        context.startActivity(intent);
    }


}
