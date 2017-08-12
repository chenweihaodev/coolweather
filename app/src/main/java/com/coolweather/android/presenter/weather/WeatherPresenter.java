package com.coolweather.android.presenter.weather;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.coolweather.android.common.MyApplication;
import com.coolweather.android.common.open.guolin.bing.BingPicRequest;
import com.coolweather.android.common.open.guolin.weather.GsonParse;
import com.coolweather.android.common.open.guolin.weather.WeatherRequest;
import com.coolweather.android.common.open.guolin.weather.data.Weather;
import com.coolweather.android.contract.weather.IWeatherPresenter;
import com.coolweather.android.contract.weather.IWeatherView;
import com.coolweather.android.service.AutoUpdateService;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by chenweihao on 2017/8/11.
 */

public class WeatherPresenter implements IWeatherPresenter {

    IWeatherView weatherView;
    String currentWeatherId;

    public WeatherPresenter(IWeatherView weatherView){
        this.weatherView = weatherView;
    }

    @Override
    public void requestWeather(String weatherId) {

        WeatherRequest.requestWeather(weatherId, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                weatherView.showToast("服务器异常，获取数据失败");
                weatherView.setRefreshing(false);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Weather weather = GsonParse.parseWeather(response.body().string());
                if (weather!=null&&"ok".equals(weather.status)){
                    SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext()).edit();
                    editor.putString("weatherId",weatherId);
                    editor.apply();
                    currentWeatherId = weatherId;
                    weatherView.showWeather(weather);
                }
                weatherView.setRefreshing(false);
            }
        });

        BingPicRequest.requestBingPic(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {}
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                weatherView.setBackground(response.body().string());
            }
        });
    }

    @Override
    public void onRefresh() {
        requestWeather(currentWeatherId);
    }


}
