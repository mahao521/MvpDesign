package com.moudle.mvpdesign.percenter;

import com.moudle.mvpcore.rx.rxbus.RegisterBus;
import com.moudle.mvpdesign.bean.MaHao;
import com.moudle.mvpdesign.moudle.IMahaoModel;
import com.moudle.mvpdesign.moudle.IMahaoModelImpl;
import com.moudle.mvpdesign.view.IGrilView;


import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/8/21.
 */

public class MahaoPresenter<T extends IGrilView>{

    private WeakReference<T> mView;
    private IMahaoModel mMahaoModel;

    public MahaoPresenter(T view){
        mView = new WeakReference<T>(view);
        mMahaoModel = new IMahaoModelImpl();
        mMahaoModel.loadList();
    }
/*
    public void fetch(){
        mMahaoModel.loadList(new IMahaoModel.onCompleteListener() {
            @Override
            public void completeListener(List<MaHao> list) {
                IGrilView view = (IGrilView) mView.get();
                if(view != null){
                    view.showMahao(list);
                }
            }
        });
    }*/

   @RegisterBus()
    public void getShowMahaoData(ArrayList<MaHao> list){
       mView.get().showMahao(list);
   }
}

