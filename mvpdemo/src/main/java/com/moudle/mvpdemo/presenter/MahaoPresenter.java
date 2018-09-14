package com.moudle.mvpdemo.presenter;

import android.app.Activity;
import android.view.View;

import com.moudle.mvpdemo.MvpActivity;
import com.moudle.mvpdemo.bean.MaHao;
import com.moudle.mvpdemo.model.IMahaoModel;
import com.moudle.mvpdemo.model.IMahaoModelImpl;
import com.moudle.mvpdemo.view.IGrilView;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by Administrator on 2018/8/21.
 */

public class MahaoPresenter <T extends IGrilView>{

    private WeakReference<T> mView;
    private IMahaoModel mMahaoModel;

    public MahaoPresenter(T view){
        mView = new WeakReference<T>(view);
        mMahaoModel = new IMahaoModelImpl();
    }

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
    }
}

