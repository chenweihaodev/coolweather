package com.coolweather.android.common.open.guolin.weather;

import com.coolweather.android.common.open.guolin.weather.data.Weather;
import com.coolweather.android.common.util.HttpUtil;

import okhttp3.Callback;

/**
 * Created by chenweihao on 2017/8/11.
 */

public class WeatherRequest {

    public static final String ADDRESS = "http://guolin.tech/api/weather";

    public static final String KEY = "78376f32916a413081aa0f78ed8eeadb";

    public static void requestWeather(String cityid, Callback callback){
        String url = ADDRESS + "?key=" + KEY + "&cityid="+cityid;
        HttpUtil.sendOkHttpRequest(url,callback);
    }

}
