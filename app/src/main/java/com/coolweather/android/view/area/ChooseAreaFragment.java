package com.coolweather.android.view.area;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.coolweather.android.R;
import com.coolweather.android.contract.area.IChooseAreaView;
import com.coolweather.android.contract.area.IChooseAreaPresenter;
import com.coolweather.android.entity.area.City;
import com.coolweather.android.entity.area.County;
import com.coolweather.android.entity.area.Province;
import com.coolweather.android.presenter.area.ChooseAreaPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenweihao on 2017/8/7.
 */

public class ChooseAreaFragment extends Fragment implements IChooseAreaView {

    IChooseAreaPresenter presenter;

    private TextView titleText;

    private Button backButton;

    private ListView listView;

    private ArrayAdapter<String> adapter;

    private List<String> areaList = new ArrayList<>();

    private List<Province> provinceList;

    private List<City> cityList;

    private List<County> countyList;

    private Province selectedProvince;

    private City selectedCity;

    private int currentLevel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ChooseAreaPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.choose_area,container,false);
        titleText = (TextView)view.findViewById(R.id.title_text);
        backButton = (Button)view.findViewById(R.id.back_button);
        listView = (ListView)view.findViewById(R.id.list_view);
        adapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,areaList);
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id)-> {
            if (currentLevel==0){
                this.selectedProvince = provinceList.get(position);
                presenter.loadCity(selectedProvince.getProvinceCode());
            }else if (currentLevel==1){
                this.selectedCity = cityList.get(position);
                presenter.loadCounty(selectedProvince.getProvinceCode(),selectedCity.getCityCode());
            }
        });

        backButton.setOnClickListener((View v) -> {
            if (currentLevel==2){
                presenter.loadCity(selectedProvince.getProvinceCode());
            }else if (currentLevel==1){
                presenter.loadProvince();
            }
        });

        presenter.loadProvince();
    }

    @Override
    public void showProvinceList(List<Province> provinceList) {
        titleText.setText("中国");
        backButton.setVisibility(View.GONE);
        this.provinceList = provinceList;
        areaList.clear();
        for (Province province : provinceList){
            areaList.add(province.getProvinceName());
        }
        adapter.notifyDataSetChanged();
        listView.setSelection(0);
        this.currentLevel = 0;
    }

    @Override
    public void showCityList(List<City> cityList) {
        titleText.setText(selectedProvince.getProvinceName());
        backButton.setVisibility(View.VISIBLE);
        this.cityList = cityList;
        areaList.clear();
        for (City city : cityList){
            areaList.add(city.getCityName());
        }
        adapter.notifyDataSetChanged();
        listView.setSelection(0);
        this.currentLevel = 1;
    }

    @Override
    public void showCountyList(List<County> countyList) {
        titleText.setText(selectedCity.getCityName());
        backButton.setVisibility(View.VISIBLE);
        this.countyList = countyList;
        areaList.clear();
        for (County county:countyList){
            areaList.add(county.getCountyName());
        }
        adapter.notifyDataSetChanged();
        listView.setSelection(0);
        this.currentLevel = 2;
    }

}
