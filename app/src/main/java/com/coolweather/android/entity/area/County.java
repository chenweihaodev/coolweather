package com.coolweather.android.entity.area;

import org.litepal.crud.DataSupport;

/**
 * Created by chenweihao on 2017/8/7.
 */

public class County extends DataSupport{

    int id;

    String countyName;

    String weatherId;

    int cityId;

    public County() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}
