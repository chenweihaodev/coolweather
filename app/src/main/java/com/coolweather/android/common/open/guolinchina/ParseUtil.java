package com.coolweather.android.common.open.guolinchina;

import android.text.TextUtils;

import com.coolweather.android.entity.area.City;
import com.coolweather.android.entity.area.County;
import com.coolweather.android.entity.area.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenweihao on 2017/8/7.
 */

public class ParseUtil {

    public static List<Province> parseProvince(String jsonStr){
        List<Province> list = null;
        if(!TextUtils.isEmpty(jsonStr)){
            list = new ArrayList<>();
            try {
                JSONArray array = new JSONArray(jsonStr);
                for (int i=0;i<array.length();i++){
                    JSONObject object = array.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceCode(object.getInt("id"));
                    province.setProvinceName(object.getString("name"));
                    list.add(province);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static List<City> parseCity(String jsonStr,int provinceId){
        List<City> cityList = null;
        if(!TextUtils.isEmpty(jsonStr)){
         cityList = new ArrayList<>();
            try {
                JSONArray array = new JSONArray(jsonStr);
                for (int i=0;i<array.length();i++){
                    JSONObject object = array.getJSONObject(i);
                    City city = new City();
                    city.setCityCode(object.getInt("id"));
                    city.setCityName(object.getString("name"));
                    city.setProvinceId(provinceId);
                    cityList.add(city);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return cityList;
    }

    public static List<County> parseCounty(String jsonStr,int cityId){
        List<County> countyList = null;
        if (!TextUtils.isEmpty(jsonStr)){
            countyList = new ArrayList<>();
            try {
                JSONArray array = new JSONArray(jsonStr);
                for(int i=0;i<array.length();i++){
                    JSONObject object = array.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(object.getString("name"));
                    county.setWeatherId(object.getString("weather_id"));
                    county.setCityId(cityId);
                    countyList.add(county);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return countyList;
    }

}
