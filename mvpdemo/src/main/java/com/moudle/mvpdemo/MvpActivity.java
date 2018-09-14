package com.moudle.mvpdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.moudle.mvpdemo.adapter.MahaoAdapter;
import com.moudle.mvpdemo.bean.MaHao;
import com.moudle.mvpdemo.presenter.MahaoPresenter;
import com.moudle.mvpdemo.view.IGrilView;

import java.util.List;

public class MvpActivity extends AppCompatActivity implements IGrilView {

    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        mListView = findViewById(R.id.lv_show);
        new MahaoPresenter(this).fetch();
    }

    @Override
    public void showMahao(List<MaHao> maHaos) {
        mListView.setAdapter(new MahaoAdapter(this,maHaos));
    }
}
