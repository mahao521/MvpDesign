package com.moudle.mvpdesign;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.moudle.mvpcore.CoreActivity;
import com.moudle.mvpcore.rx.rxbus.RxBus;
import com.moudle.mvpdemo.MvpActivity;
import com.moudle.mvpdesign.adapter.MahaoAdapter;
import com.moudle.mvpdesign.bean.MaHao;
import com.moudle.mvpdesign.dagger.MahaoPrecenterMoudle;
import com.moudle.mvpdesign.percenter.DaggerMahaoComponent;
import com.moudle.mvpdesign.percenter.MahaoPresenter;
import com.moudle.mvpdesign.view.IGrilView;

import java.util.List;
import javax.inject.Inject;

/**
 *   mvp  v层 定义需要的数据接口，更新数据，展示
 *
 *       P层： 持有view和model层的引用对象   p层操作moule层请求数据，返回到p层。 view引用传递给view层；
 *
 *       model 请求数据，定义接口，返回给p层
 *
 *  mvp + rxbus + daggger  + retofit + rxjava
 *
 *       rxbus : 定义在P层， 通过注解，反射P层需要回调的方法。----------目的解耦
 *
 *       rxjava : 传递modle的数据直接给 P 层 -----------------目的： 配合rxbus解耦 ，形成事件流。
 *
 *       dagger : 解耦
 *
 *
 */



public class MainActivity extends AppCompatActivity implements View.OnClickListener, IGrilView {

    private ListView mListView;

    @Inject
    MahaoPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_mvp).setOnClickListener(this);
        findViewById(R.id.btn_retro_mvp).setOnClickListener(this);
        mListView = findViewById(R.id.lv_show);
        DaggerMahaoComponent.builder()
                .mahaoPrecenterMoudle(new MahaoPrecenterMoudle(this))
                .build().inject(this);
        RxBus.getInstance().register(mPresenter);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_mvp:
                Intent intent = new Intent(this, MvpActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_retro_mvp:
                Intent intent1 = new Intent(this, CoreActivity.class);
                startActivity(intent1);
                break;
        }
    }

    @Override
    public void showMahao(List<MaHao> maHaos) {
        mListView.setAdapter(new MahaoAdapter(this,maHaos));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getInstance().unRegister(mPresenter);
    }
}
