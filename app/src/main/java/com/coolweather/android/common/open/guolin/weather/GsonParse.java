package com.coolweather.android.common.open.guolin.weather;

import com.coolweather.android.common.open.guolin.weather.data.Weather;
import com.coolweather.android.common.util.LogUtil;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by chenweihao on 2017/8/11.
 */

public class GsonParse {

    public static Weather parseWeather(String jsonStr){
        LogUtil.e("GsonParse",jsonStr);
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String weatherStr = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherStr,Weather.class);
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

}
