package com.moudle.mvpcore.download;

import android.os.AsyncTask;

import com.moudle.mvpcore.ResetCreator;
import com.moudle.mvpcore.callback.IError;
import com.moudle.mvpcore.callback.IFailure;
import com.moudle.mvpcore.callback.IRequest;
import com.moudle.mvpcore.callback.ISuccess;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2018/8/23.
 */

public class DownLoadHandler {
    private  HashMap<String,Object> PARAMS;
    private String URL;
    private IRequest REQUEST;
    private ISuccess SUCCESS;
    private IFailure FAILURE;
    private IError IERROR;
    private String DOWNLOAD_DIR;
    private String EXTENSION;
    private String FILENAME;

    public DownLoadHandler(HashMap<String, Object> PARAMS, String URL, IRequest REQUEST, ISuccess SUCCESS,
                           IFailure FAILURE, IError IERROR, String DOWNLOAD_DIR,
                           String EXTENSION, String FILENAME) {
        this.PARAMS = PARAMS;
        this.URL = URL;
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
        this.FAILURE = FAILURE;
        this.IERROR = IERROR;
        this.DOWNLOAD_DIR = DOWNLOAD_DIR;
        this.EXTENSION = EXTENSION;
        this.FILENAME = FILENAME;
    }

    public final void handleDownload(){
        ResetCreator.getRestService().download(URL,PARAMS)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        //启动异步任务保存文件
                        if(response.isSuccessful()){
                            SaveFileTask task = new SaveFileTask(REQUEST,SUCCESS);
                            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,DOWNLOAD_DIR,response.body(),FILENAME);
                            if(task.isCancelled()){
                                if(REQUEST != null){
                                    REQUEST.onRequestEnd();
                                }
                            }
                        }else {
                            if(IERROR != null){
                                IERROR.onError(response.code(),response.message());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if(FAILURE != null){
                            FAILURE.onFailure(t);
                        }
                    }
                });
    }
}
