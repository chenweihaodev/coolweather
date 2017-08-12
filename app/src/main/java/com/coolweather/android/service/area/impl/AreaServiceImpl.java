package com.coolweather.android.service.area.impl;

import com.coolweather.android.entity.area.City;
import com.coolweather.android.entity.area.County;
import com.coolweather.android.entity.area.Province;
import com.coolweather.android.service.area.IAreaService;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by chenweihao on 2017/8/7.
 */

public class AreaServiceImpl implements IAreaService{

    @Override
    public void addProvince(Province province) {
        province.save();
    }

    @Override
    public List<Province> getProvinceList() {
        return DataSupport.findAll(Province.class);
    }

    @Override
    public void addCity(City city) {
        city.save();
    }

    @Override
    public List<City> getCityListByProvinceId(int provinceId) {
        return DataSupport.where("provinceid = ?",String.valueOf(provinceId)).find(City.class);
    }

    @Override
    public void addCounty(County county) {
        county.save();
    }

    @Override
    public List<County> getCountListByCityId(int cityId) {
        return DataSupport.where("cityid = ?",String.valueOf(cityId)).find(County.class);
    }
}
