package com.moudle.mvpcore.callback;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Administrator on 2018/8/23.
 */

public class RequestCallbacks implements Callback<String> {

    private IRequest REQUEST;
    private ISuccess mISuccess;
    private IFailure mIFailure;
    private IError mIError;

    public RequestCallbacks(IRequest REQUEST, ISuccess ISuccess, IFailure IFailure, IError IError) {
        this.REQUEST = REQUEST;
        mISuccess = ISuccess;
        mIFailure = IFailure;
        mIError = IError;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if(response.isSuccessful()){
            if(call.isExecuted()){
                if(mISuccess != null){
                    mISuccess.onSuccess(response.body());
                }
            }
        }else {
            if(mIError != null){
                mIError.onError(response.code(),response.message());
            }
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
         if(mIFailure != null){
             mIFailure.onFailure(t);
         }
         if(REQUEST != null){
             REQUEST.onRequestEnd();
         }
    }
}
