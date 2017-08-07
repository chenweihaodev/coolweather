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

    void showProvinceList(List<Province> provinceList);

    void showCityList(List<City> cityList);

    void showCountyList(List<County> countyList);

    Activity getActivity();

}
