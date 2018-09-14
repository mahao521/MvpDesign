package com.moudle.mvpcore.rx;

import java.io.File;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2018/8/24.
 */

public class RxRestClientBuilder {
    private HashMap<String,Object> params;
    private String url;
    private RequestBody mBody;
    private File mFile;

    public RxRestClientBuilder(){
    }

    public RxRestClientBuilder params(HashMap<String,Object> params){
        this.params = params;
        return this;
    }

    public RxRestClientBuilder url(String url){
        this.url = url;
        return this;
    }

    public RxRestClientBuilder body(RequestBody body){
        this.mBody = body;
        return this;
    }

    public RxRestClientBuilder file(File file){
        this.mFile = file;
        return this;
    }

    public RxRestClientBuilder file(String file){
        this.mFile = new File(file);
        return this;
    }

    public final RxRestClientBuilder raw(String raw){
        this.mBody=RequestBody.create(
                MediaType.parse("application/json;charset=UTF-8"),raw);
        return this;
    }

    public RxRestClient build(){
        return  new RxRestClient(params,url,mBody,mFile);
    }
}
