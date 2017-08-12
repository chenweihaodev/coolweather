package com.coolweather.android.contract.weather;

/**
 * Created by chenweihao on 2017/8/11.
 */

public interface IWeatherPresenter {

    void requestWeather(String weatherId);

    void onRefresh();

}
