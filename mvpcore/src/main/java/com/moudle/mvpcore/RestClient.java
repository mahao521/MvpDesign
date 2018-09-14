package com.moudle.mvpcore;

import com.moudle.mvpcore.callback.IError;
import com.moudle.mvpcore.callback.IFailure;
import com.moudle.mvpcore.callback.IRequest;
import com.moudle.mvpcore.callback.ISuccess;
import com.moudle.mvpcore.callback.RequestCallbacks;
import com.moudle.mvpcore.download.DownLoadHandler;

import java.io.File;
import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Administrator on 2018/8/23.
 */

public class RestClient {
    private HashMap<String,Object> PARAMS;
    private  String URL;
    private IRequest REQUEST;
    private ISuccess SUCCESS;
    private IFailure FAILURE;
    private IError ERROR;
    private RequestBody BODY;
    private File FILE;
    private String DOWNlOAD_DIR;
    private String EXTENSION;
    private String FILENAME;

    public RestClient(HashMap<String, Object> PARAMS, String URL, IRequest REQUEST, ISuccess SUCCESS,
                      IFailure FAILURE, IError ERROR, RequestBody BODY, File FILE, String DOWNlOAD_DIR,
                      String EXTENSION, String FILENAME) {
        this.PARAMS = PARAMS;
        this.URL = URL;
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
        this.FAILURE = FAILURE;
        this.ERROR = ERROR;
        this.BODY = BODY;
        this.FILE = FILE;
        this.DOWNlOAD_DIR = DOWNlOAD_DIR;
        this.EXTENSION = EXTENSION;
        this.FILENAME = FILENAME;
    }

    public static RestClientBuilder create(){
        return new RestClientBuilder();
    }

    //实现真实的网络操作
    private void request(HttpMethod method){
        final ResetService service = ResetCreator.getRestService();
        Call<String> call = null;
        if(REQUEST != null){
            REQUEST.onRequestStart();
        }
        switch (method){
            case GET:
                call = service.get(URL,PARAMS);
                break;
            case POST:
                call = service.post(URL,PARAMS);
                break;
            case PUT:
                call = service.put(URL,PARAMS);
                break;
            case DELETE:
                call = service.delete(URL,PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(MultipartBody.FORM,FILE);
                final MultipartBody.Part body = MultipartBody.Part.createFormData("file",FILE.getName(),requestBody);
                call = service.upload(URL,body);
                break;
             default:
                    break;
        }
        if(call != null){
            call.enqueue(getRequestCallback());
        }
    }

    public Callback<String> getRequestCallback() {
        return new RequestCallbacks(REQUEST,SUCCESS,FAILURE,ERROR);
    }

    public final void get(){
        request(HttpMethod.GET);
    }

    public final void post(){
        request(HttpMethod.POST);
    }

    public final void put(){
        request(HttpMethod.PUT);
    }

    public final void delete(){
        request(HttpMethod.DELETE);
    }

    public final void upload(){
        request(HttpMethod.UPLOAD);
    }

    public final void downLoad(){
        new DownLoadHandler(PARAMS,URL,REQUEST,SUCCESS,FAILURE,
                ERROR,DOWNlOAD_DIR,EXTENSION,FILENAME)
                .handleDownload();
    }
}
