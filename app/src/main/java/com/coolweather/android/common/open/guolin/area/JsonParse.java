package com.coolweather.android.common.open.guolin.area;

import android.app.MediaRouteActionProvider;
import android.content.Intent;
import android.text.TextUtils;

import com.coolweather.android.entity.area.City;
import com.coolweather.android.entity.area.County;
import com.coolweather.android.entity.area.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenweihao on 2017/8/7.
 */

public class JsonParse {

    public static List<Area> parseProvince(String jsonStr){
        List<Area> areaList = new ArrayList<>();

        if(!TextUtils.isEmpty(jsonStr)){
            try {
                JSONArray array = new JSONArray(jsonStr);
                for (int i=0;i<array.length();i++){
                    JSONObject object = array.getJSONObject(i);
                    areaList.add(new Area(object.getInt("id"),object.getString("name")));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return areaList;
    }

    public static List<Area> parseCity(String jsonStr){
        List<Area> areaList = new ArrayList<>();

        if(!TextUtils.isEmpty(jsonStr)){
            try {
                JSONArray array = new JSONArray(jsonStr);
                for (int i=0;i<array.length();i++){
                    JSONObject object = array.getJSONObject(i);
                    areaList.add(new Area(object.getInt("id"),object.getString("name")));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return areaList;
    }

    public static List<Area> parseCounty(String jsonStr){
        List<Area> areaList = new ArrayList<>();

        if (!TextUtils.isEmpty(jsonStr)){
            try {
                JSONArray array = new JSONArray(jsonStr);
                for(int i=0;i<array.length();i++){
                    JSONObject object = array.getJSONObject(i);
                    areaList.add(new Area(object.getInt("id"),object.getString("name"),object.getString("weather_id")));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return areaList;
    }

}
