package com.moudle.mvpdemo.model;

import com.moudle.mvpdemo.bean.MaHao;

import java.util.List;

/**
 * Created by Administrator on 2018/8/21.
 */

public interface IMahaoModel {

    void  loadList(onCompleteListener listener);

    interface  onCompleteListener{

        void completeListener(List<MaHao> list);
    }
}
