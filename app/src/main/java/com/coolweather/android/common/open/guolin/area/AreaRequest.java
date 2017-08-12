package com.coolweather.android.common.open.guolin.area;

import com.coolweather.android.common.util.HttpUtil;

import okhttp3.Callback;

/**
 * Created by chenweihao on 2017/8/7.
 */

public class AreaRequest {

    public static final String ADDRESS = "http://guolin.tech/api/china";

    public static void queryProvince(Callback callback){
        String address = AreaRequest.ADDRESS;
        HttpUtil.sendOkHttpRequest(address,callback);
    }

    public static void queryCity(int province,Callback callback){
        String address = AreaRequest.ADDRESS +"/"+ province;
        HttpUtil.sendOkHttpRequest(address,callback);
    }

    public static void queryCounty(int province,int city,Callback callback){
        String address = AreaRequest.ADDRESS + "/" + province + "/" + city;
        HttpUtil.sendOkHttpRequest(address,callback);
    }

}
