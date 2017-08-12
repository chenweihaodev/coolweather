package com.coolweather.android.common.open.guolin.weather.data;

/**
 * Created by chenweihao on 2017/8/10.
 */

public class Forecast {

    public String date;

    public Cond cond;

    public class Cond{

        public String txt_d;

    }

    public Tmp tmp;

    public class Tmp{

        public String max;

        public String min;

    }

}
