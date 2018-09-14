package com.moudle.mvpcore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.moudle.mvpcore.callback.IError;
import com.moudle.mvpcore.callback.IFailure;
import com.moudle.mvpcore.callback.ISuccess;
import com.moudle.mvpcore.rx.RxRestClient;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CoreActivity extends AppCompatActivity {

    private static final String TAG = "CoreActivity";
    //http://restapi.amap.com/v3/weather/weatherInfo
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_core);
        HashMap<String,Object> params = new HashMap<>();
        params.put("city","北京");
        params.put("key","13cb58f5884f9749287abbead9c658f2");
       /* RestClient.create().params(params)
                .url("/v3/weather/weatherInfo")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(CoreActivity.this,response.toString(),Toast.LENGTH_SHORT).show();
                    }
                }).error(new IError() {
            @Override
            public void onError(int code, String msg) {
                Toast.makeText(CoreActivity.this,msg + " " + code,Toast.LENGTH_LONG).show();
            }
        }).failure(new IFailure() {
            @Override
            public void onFailure(Throwable throwable) {
                Toast.makeText(CoreActivity.this,throwable.getMessage(),Toast.LENGTH_LONG).show();
            }
        }).build().get();*/


        RxRestClient.create().params(params)
                .url("/v3/weather/weatherInfo")
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "onNext: " + s);
                        Toast.makeText(CoreActivity.this,s,Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }
}
