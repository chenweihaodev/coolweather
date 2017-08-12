package com.coolweather.android.view.area;

import android.app.ProgressDialog;
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

    ProgressDialog progressDialog;

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
        presenter = new ChooseAreaPresenter(this);

        listView.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id)-> {
            presenter.chooseArea(position);
        });

        backButton.setOnClickListener((View v) -> {
            presenter.back();
        });

        presenter.loadProvince();
    }

    public void setListView(String title,List<String> list){
        titleText.setText(title);
        areaList.clear();
        for (String name : list){
            areaList.add(name);
        }
        adapter.notifyDataSetChanged();
        listView.setSelection(0);
    }

    @Override
    public void setBackButtonVisibility(int id) {
        backButton.setVisibility(id);
    }

    @Override
    public void showProgressDialog() {
        if (progressDialog==null){
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("正在加载...");
            progressDialog.setCancelable(false);
        }
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        if (progressDialog!=null){
            progressDialog.dismiss();
        }
    }

}
