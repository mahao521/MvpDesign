package com.moudle.mvpcore.rx;

import com.moudle.mvpcore.HttpMethod;
import com.moudle.mvpcore.ResetCreator;
import com.moudle.mvpcore.download.DownLoadHandler;

import java.io.File;
import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2018/8/24.
 */

public class RxRestClient {
    private HashMap<String,Object> PARAMS;
    private String url;
    private RequestBody mBody;
    //上传下载
    private File mFile;

    public RxRestClient(HashMap<String, Object> PARAMS, String url, RequestBody body, File file) {
        this.PARAMS = PARAMS;
        this.url = url;
        mBody = body;
        mFile = file;
    }

    public static RxRestClientBuilder create(){
        return new RxRestClientBuilder();
    }

    //网络操作
    private Observable<String> request(HttpMethod method){
        final RxRestService service = ResetCreator.getRxRestService();
        Observable<String> observable = null;
        switch (method){
            case GET:
                observable = service.get(url,PARAMS);
                break;
            case POST:
                observable = service.post(url,PARAMS);
                break;
            case PUT:
                observable = service.put(url,PARAMS);
                break;
            case DELETE:
                observable = service.delete(url,PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(MultipartBody.FORM,mFile);
                MultipartBody.Part body = MultipartBody.Part.createFormData("file",mFile.getName(),requestBody);
                observable = service.upload(url,body);
                break;
            default:
                break;
        }
        return observable;
    }

    public final Observable<String> get() {
        return request(HttpMethod.GET);
    }

    public final Observable<String> post(){
        return request(HttpMethod.POST);
    }

    public final Observable<String> put(){
        return request(HttpMethod.PUT);
    }

    public final Observable<String> delete(){
        return request(HttpMethod.DELETE);
    }

    public final Observable<String> upload(){
        return request(HttpMethod.UPLOAD);
    }

    public final Observable<ResponseBody> downLoad(){
        return ResetCreator.getRxRestService().download(url,PARAMS);
    }
}
