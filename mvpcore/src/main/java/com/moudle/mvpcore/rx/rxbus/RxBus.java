package com.moudle.mvpcore.rx.rxbus;

import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/8/24.
 */

public class RxBus {

    private static final String TAG = "RxBus";
    public static volatile RxBus instance = null;
    private Set<Object> mObjectSet;

    private RxBus(){
        mObjectSet = new CopyOnWriteArraySet<>();
    }

    public static RxBus getInstance(){
        if(instance == null){
            synchronized (RxBus.class){
                if(instance == null){
                    instance = new RxBus();
                }
            }
        }
        return instance;
    }

    public synchronized void register(Object clazz){
        mObjectSet.add(clazz);
    }

    public synchronized void unRegister(Object clazz){
        mObjectSet.remove(clazz);
    }

    public void proceed(Function function){
        Observable.just("")
                .subscribeOn(Schedulers.io())
                .map(function)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer() {
                    @Override
                    public void accept(Object object) throws Exception {
                        if(object == null){
                            return ;
                        }
                       sendData(object);
                    }
                });
    }

    private void sendData(Object data) {
        Iterator iterator = mObjectSet.iterator();
        while (iterator.hasNext()){
            Object next = iterator.next();
            callMethodByAnnotion(next,data);
        }
    }

    private void callMethodByAnnotion(Object target,Object data) {
        Method[] methods = target.getClass().getMethods();
        for(int i = 0; i < methods.length; i++){
            RegisterBus annotation = methods[i].getAnnotation(RegisterBus.class);
            if(annotation != null){
                Log.d(TAG, "callMethodByAnnotion: " + methods[i].getParameterTypes()[0].toString());
                Log.d(TAG, "callMethodByAnnotion: " + data.getClass().getName());
                //  list  是  arrayList的父类或者接口
                if(methods[i].getParameterTypes()[0].isAssignableFrom(data.getClass())){
                    try {
                        methods[i].invoke(target,data);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
