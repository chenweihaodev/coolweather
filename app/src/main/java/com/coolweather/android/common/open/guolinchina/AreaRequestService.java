package com.coolweather.android.common.open.guolinchina;

import com.coolweather.android.common.util.HttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by chenweihao on 2017/8/7.
 */

public class AreaRequestService {

    public void queryProvince(Callback callback){
        String address = Configuration.ADDRESS;
        HttpUtil.sendOkHttpRequest(address,callback);
    }

    public void queryCity(int province,Callback callback){
        String address = Configuration.ADDRESS +"/"+ province;
        HttpUtil.sendOkHttpRequest(address,callback);
    }

    public void queryCounty(int province,int city,Callback callback){
        String address = Configuration.ADDRESS + "/" + province + "/" + city;
        HttpUtil.sendOkHttpRequest(address,callback);
    }

}
