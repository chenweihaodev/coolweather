package com.coolweather.android.contract.area;

import android.app.Activity;

import com.coolweather.android.entity.area.City;
import com.coolweather.android.entity.area.County;
import com.coolweather.android.entity.area.Province;

import java.util.List;

/**
 * Created by chenweihao on 2017/8/7.
 */

public interface IChooseAreaView {

    void setListView(String title,List<String> list);

    void setBackButtonVisibility(int id);

    Activity getActivity();

    void showProgressDialog();

    void closeProgressDialog();

}
