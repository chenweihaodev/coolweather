package com.coolweather.android.contract.weather;

import android.app.Activity;

import com.coolweather.android.common.open.guolin.weather.data.Weather;

/**
 * Created by chenweihao on 2017/8/11.
 */

public interface IWeatherView {

    void showWeather(Weather weather);

    void showToast(String msg);

    void setBackground(String url);

    void setRefreshing(boolean refreshing);
}
