package com.coolweather.android.service.area;

import com.coolweather.android.entity.area.City;
import com.coolweather.android.entity.area.County;
import com.coolweather.android.entity.area.Province;

import java.util.List;

/**
 * Created by chenweihao on 2017/8/7.
 */

public interface IAreaService {

    void addProvince(Province province);

    List<Province> getProvinceList();

    void addCity(City city);

    List<City> getCityListByProvinceId(int provinceId);

    void addCounty(County county);

    List<County> getCountListByCityId(int cityId);

}
