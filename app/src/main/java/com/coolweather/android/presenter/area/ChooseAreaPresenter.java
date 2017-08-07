package com.coolweather.android.presenter.area;

import android.util.Log;

import com.coolweather.android.common.open.guolinchina.AreaRequestService;
import com.coolweather.android.common.open.guolinchina.ParseUtil;
import com.coolweather.android.common.util.LogUtil;
import com.coolweather.android.contract.area.IChooseAreaPresenter;
import com.coolweather.android.contract.area.IChooseAreaView;
import com.coolweather.android.entity.area.City;
import com.coolweather.android.entity.area.County;
import com.coolweather.android.entity.area.Province;
import com.coolweather.android.service.area.IAreaService;
import com.coolweather.android.service.area.impl.AreaServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by chenweihao on 2017/8/7.
 */

public class ChooseAreaPresenter implements IChooseAreaPresenter {
    private static final String TAG = "ChooseAreaPresenter";
    IAreaService areaService;
    IChooseAreaView chooseAreaView;
    AreaRequestService requestService;

    public ChooseAreaPresenter(IChooseAreaView chooseAreaView){
        this.chooseAreaView = chooseAreaView;
        areaService = new AreaServiceImpl();
        requestService = new AreaRequestService();
    }

    @Override
    public void loadProvince() {
        List<Province> provinceList = areaService.getProvinceList();
        if (provinceList!=null&&provinceList.size()>0){
            chooseAreaView.showProvinceList(provinceList);
        }else{
            requestService.queryProvince(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {}

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    areaService.addProvince(ParseUtil.parseProvince(response.body().string()));
                    chooseAreaView.getActivity().runOnUiThread(()->{loadProvince();});
                }
            });
        }
    }

    @Override
    public void loadCity(int province) {
        List<City> cityList  = areaService.getCityListByProvinceId(province);
        if (cityList!=null&&cityList.size()>0){
            chooseAreaView.showCityList(cityList);
        }else{
            requestService.queryCity(province, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {}

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    areaService.addCity(ParseUtil.parseCity(response.body().string(),province));
                    chooseAreaView.getActivity().runOnUiThread(()->{loadCity(province);});
                }
            });
        }
    }

    @Override
    public void loadCounty(int province,int city) {
        List<County> countyList = areaService.getCountListByCityId(city);
        if (countyList!=null&&countyList.size()>0){
            chooseAreaView.showCountyList(countyList);
        }else {
            requestService.queryCounty(province,city, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {}

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    areaService.addCounty(ParseUtil.parseCounty(response.body().string(),city));
                    chooseAreaView.getActivity().runOnUiThread(()->{loadCounty(province,city);});
                }
            });
        }
    }


}
