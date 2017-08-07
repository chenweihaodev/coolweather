package com.coolweather.android.service.area;

import com.coolweather.android.entity.area.City;
import com.coolweather.android.entity.area.County;
import com.coolweather.android.entity.area.Province;

import java.util.List;

/**
 * Created by chenweihao on 2017/8/7.
 */

public interface IAreaService {

    void addProvince(List<Province> provinceList);

    List<Province> getProvinceList();

    void addCity(List<City> cityList);

    List<City> getCityListByProvinceId(int provinceId);

    void addCounty(List<County> countyList);

    List<County> getCountListByCityId(int cityId);

}
