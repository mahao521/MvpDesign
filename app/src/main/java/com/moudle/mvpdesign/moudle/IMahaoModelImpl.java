package com.moudle.mvpdesign.moudle;

import com.moudle.mvpcore.rx.rxbus.RxBus;
import com.moudle.mvpdesign.bean.MaHao;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Function;

/**
 * Created by Administrator on 2018/8/21.
 */

public class IMahaoModelImpl implements IMahaoModel {

    @Override
    public void loadList() {

        RxBus.getInstance().proceed(new Function() {
            @Override
            public Object apply(Object object) throws Exception {

                List<MaHao> list = new ArrayList<>();
                for(int i = 0; i < 10 ; i++){
                    list.add(new MaHao(String.format("i am the %s",i),""+i));
                }
                return list;
            }
        });
    }
}
