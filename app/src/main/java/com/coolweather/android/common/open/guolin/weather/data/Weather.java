package com.coolweather.android.common.open.guolin.weather.data;

import java.util.List;

/**
 * Created by chenweihao on 2017/8/10.
 */

public class Weather {

    public String status;

    public Basic basic;

    public AQI aqi;

    public Now now;

    public Suggestion suggestion;

    public List<Forecast> daily_forecast;

}
