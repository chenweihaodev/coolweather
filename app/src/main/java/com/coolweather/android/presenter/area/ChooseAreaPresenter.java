package com.coolweather.android.presenter.area;

import android.view.View;

import com.coolweather.android.common.open.guolin.area.Area;
import com.coolweather.android.common.open.guolin.area.AreaRequest;
import com.coolweather.android.common.open.guolin.area.JsonParse;
import com.coolweather.android.contract.area.IChooseAreaPresenter;
import com.coolweather.android.contract.area.IChooseAreaView;
import com.coolweather.android.entity.area.City;
import com.coolweather.android.entity.area.County;
import com.coolweather.android.entity.area.Province;
import com.coolweather.android.service.area.IAreaService;
import com.coolweather.android.service.area.impl.AreaServiceImpl;
import com.coolweather.android.view.MainActivity;
import com.coolweather.android.view.weather.WeatherActivity;

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

    private List<Province> provinceList;

    private List<City> cityList;

    private List<County> countyList;

    private Province selectedProvince;

    private City selectedCity;

    private int currentLevel;

    public ChooseAreaPresenter(IChooseAreaView chooseAreaView){
        this.chooseAreaView = chooseAreaView;
        areaService = new AreaServiceImpl();
    }

    @Override
    public void loadProvince() {
        provinceList = areaService.getProvinceList();
        if (provinceList!=null&&provinceList.size()>0){
            List<String> list = new ArrayList<>();
            for (Province province : provinceList){
                list.add(province.getProvinceName());
            }
            chooseAreaView.setListView("中国",list);
            chooseAreaView.setBackButtonVisibility(View.GONE);
            currentLevel = 0;
        }else{
            chooseAreaView.showProgressDialog();
            AreaRequest.queryProvince(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    chooseAreaView.closeProgressDialog();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    List<Area> areaList = JsonParse.parseProvince(response.body().string());
                    for (Area area : areaList){
                        Province province = new Province();
                        province.setProvinceCode(area.id);
                        province.setProvinceName(area.name);
                        areaService.addProvince(province);
                    }
                    chooseAreaView.closeProgressDialog();
                    chooseAreaView.getActivity().runOnUiThread(()->{loadProvince();});
                }
            });
        }
    }

    public void loadCity() {
        cityList  = areaService.getCityListByProvinceId(selectedProvince.getProvinceCode());
        if (cityList!=null&&cityList.size()>0){
            List<String> list = new ArrayList<>();
            for (City city : cityList){
                list.add(city.getCityName());
            }
            chooseAreaView.setListView(selectedProvince.getProvinceName(),list);
            chooseAreaView.setBackButtonVisibility(View.VISIBLE);
            currentLevel = 1;
        }else{
            chooseAreaView.showProgressDialog();
            AreaRequest.queryCity(selectedProvince.getProvinceCode(), new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    chooseAreaView.closeProgressDialog();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    List<Area> areaList = JsonParse.parseCity(response.body().string());
                    for (Area area : areaList){
                        City city = new City();
                        city.setProvinceId(selectedProvince.getProvinceCode());
                        city.setCityCode(area.id);
                        city.setCityName(area.name);
                        areaService.addCity(city);
                    }
                    chooseAreaView.closeProgressDialog();
                    chooseAreaView.getActivity().runOnUiThread(()->{loadCity();});
                }
            });
        }
    }

    public void loadCounty() {

        countyList = areaService.getCountListByCityId(selectedCity.getCityCode());
        if (countyList!=null&&countyList.size()>0){
            List<String> list = new ArrayList<>();
            for (County county : countyList){
                list.add(county.getCountyName());
            }
            chooseAreaView.setListView(selectedProvince.getProvinceName(),list);
            chooseAreaView.setBackButtonVisibility(View.VISIBLE);
            currentLevel = 2;
        }else {
            chooseAreaView.showProgressDialog();
            AreaRequest.queryCounty(selectedProvince.getProvinceCode(),selectedCity.getCityCode(), new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    chooseAreaView.closeProgressDialog();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    List<Area> areaList = JsonParse.parseCounty(response.body().string());
                    for (Area area:areaList){
                        County county = new County();
                        county.setCityId(selectedCity.getCityCode());
                        county.setWeatherId(area.weatherId);
                        county.setCountyName(area.name);
                        areaService.addCounty(county);
                    }
                    chooseAreaView.closeProgressDialog();
                    chooseAreaView.getActivity().runOnUiThread(()->{loadCounty();});
                }
            });
        }
    }

    @Override
    public void chooseArea(int position) {
        if (currentLevel==0){
            selectedProvince = provinceList.get(position);
            loadCity();
        }else if (currentLevel==1){
            selectedCity = cityList.get(position);
            loadCounty();
        }else if (currentLevel==2){
            County county = countyList.get(position);
            if (chooseAreaView.getActivity() instanceof MainActivity){
                WeatherActivity.activityStart(chooseAreaView.getActivity(),county.getWeatherId());
                chooseAreaView.getActivity().finish();
            }else if (chooseAreaView.getActivity() instanceof WeatherActivity){
                WeatherActivity weatherActivity = (WeatherActivity) chooseAreaView.getActivity();
                weatherActivity.drawerLayout.closeDrawers();
                weatherActivity.setRefreshing(true);
                weatherActivity.weatherPresenter.requestWeather(county.getWeatherId());
            }

        }
    }

    @Override
    public void back() {
        if (currentLevel==2){
            loadCity();
        }else if (currentLevel==1){
            loadProvince();
        }
    }

}
