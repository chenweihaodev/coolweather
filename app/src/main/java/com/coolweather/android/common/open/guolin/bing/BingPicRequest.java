package com.coolweather.android.common.open.guolin.bing;

import com.coolweather.android.common.util.HttpUtil;

import okhttp3.Callback;

/**
 * Created by chenweihao on 2017/8/11.
 */

public class BingPicRequest {

    public static final String ADDRESS = "http://guolin.tech/api/bing_pic";

    public static void requestBingPic(Callback callback){
        HttpUtil.sendOkHttpRequest(ADDRESS,callback);
    }

}
