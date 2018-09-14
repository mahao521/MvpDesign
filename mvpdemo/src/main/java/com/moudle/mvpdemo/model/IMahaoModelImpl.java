package com.moudle.mvpdemo.model;

import android.util.SparseArray;

import com.moudle.mvpdemo.bean.MaHao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/8/21.
 */

public class IMahaoModelImpl implements IMahaoModel {

    @Override
    public void loadList(onCompleteListener listener) {

        List<MaHao> list = new ArrayList<>();
        for(int i = 0; i < 10 ; i++){
            list.add(new MaHao(String.format("i am the %s",i),""+i));
        }
        listener.completeListener(list);
    }
}
