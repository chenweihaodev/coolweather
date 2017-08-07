package com.coolweather.android.contract.area;

/**
 * Created by chenweihao on 2017/8/7.
 */

public interface IChooseAreaPresenter {

    void loadProvince();

    void loadCity(int province);

    void loadCounty(int province,int city);

}
