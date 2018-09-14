package com.moudle.mvpcore;

import com.moudle.mvpcore.callback.IError;
import com.moudle.mvpcore.callback.IFailure;
import com.moudle.mvpcore.callback.IRequest;
import com.moudle.mvpcore.callback.ISuccess;

import java.io.File;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2018/8/23.
 */

public class RestClientBuilder {
    private HashMap<String,Object> mParams;
    private String mUrl;
    private IRequest mIRequest;
    private ISuccess mISuccess;
    private IFailure mIFailure;
    private IError mIError;
    private RequestBody mBody;
    private File mFile;
    private String mDownloadDir;
    private String mExtension;
    private String mFilename;

    public RestClientBuilder(){

    }

    public final RestClientBuilder url(String url){
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(HashMap<String,Object> params){
        this.mParams = params;
        return this;
    }

    public final RestClientBuilder success(ISuccess success){
        this.mISuccess = success;
        return this;
    }

    public final RestClientBuilder request(IRequest request){
        this.mIRequest = request;
        return this;
    }

    public final RestClientBuilder error(IError error){
        this.mIError = error;
        return this;
    }

    public final  RestClientBuilder failure(IFailure failure){
        this.mIFailure = failure;
        return this;
    }

    public final RestClientBuilder Raw(String raw){
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),raw);
        return this;
    }

    public final RestClientBuilder file(File file){
        this.mFile = file;
        return this;
    }

    public final RestClientBuilder file(String file){
        this.mFile = new File(file);
        return this;
    }

    public final RestClientBuilder dir(String dir){
        this.mDownloadDir = dir;
        return this;
    }

    public final RestClientBuilder extension(String extension){
        this.mExtension = extension;
        return this;
    }

    public final RestClientBuilder filename(String filename){
        this.mFilename = filename;
        return this;
    }

    public final RestClient build(){
        return new RestClient(mParams,mUrl,mIRequest,mISuccess,mIFailure,
                mIError,mBody,mFile,mDownloadDir,mExtension,mFilename);
    }




}
