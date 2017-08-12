package com.coolweather.android.common.open.guolin.area;

/**
 * Created by chenweihao on 2017/8/10.
 */

public class Area {

    public int id;

    public String name;

    public String weatherId;

    public Area(int id,String name){
        this.id = id;
        this.name = name;
    }

    public Area(int id,String name,String weatherId){
        this.id = id;
        this.name = name;
        this.weatherId = weatherId;
    }

}
