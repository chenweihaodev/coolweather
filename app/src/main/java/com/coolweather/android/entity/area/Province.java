package com.coolweather.android.entity.area;

import org.litepal.crud.DataSupport;

/**
 * Created by chenweihao on 2017/8/7.
 */

public class Province extends DataSupport{

    int id;

    String provinceName;

    int provinceCode;

    public Province() {
    }

    public int getId() {
        return id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }
}
