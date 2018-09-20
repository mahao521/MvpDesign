package com.moudle.mvpdesign.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.http.Multipart;

/**
 * Created by Administrator on 2018/9/20.
 */

public class OkHttpUtils {

    private static OkHttpUtils okHttpUtils  = null;
    private OkHttpClient mClient;
    private OkHttpUtils(){
        mClient = new OkHttpClient();
    }
    public static  OkHttpUtils getInstance(){
        if(okHttpUtils == null){
            synchronized (OkHttpUtils.class){
                if(okHttpUtils == null){
                    okHttpUtils = new OkHttpUtils();
                }
            }
        }
        return okHttpUtils;
    }

    /**
     *   获取请求url
     * @param url
     */
    private Request getRequest(String url){
        Request request = new Request.Builder()
                .url(url)
                .build();
        return request;
    }

    /**
     *   同步获取请求返回对象
     * @param url
     * @return
     */
    private Response getResponse(String url){
        Request request = getRequest(url);
        Response response = null;
        try {
            response = mClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     *   异步获取网络请求，返回对象
     * @param url
     * @return
     */
    private void getResponseAsy(String url){
        Request request = getRequest(url);
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

    /**
     *   获取string类型返回值
     * @param url
     * @return
     */
    private String getResponseStr(String url){
        Response response = getResponse(url);
        try {
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     *  获取byte[]类型的response
     * @param url
     * @return
     */
    private byte[] getResponseBody(String url){
        Response response = getResponse(url);
        try {
            return  response.body().bytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *   post请求 构建body;
     * @param url
     * @param body
     * @return
     */
   private Request postRequest(String url, RequestBody body){
        Request request = new Request.Builder().url(url).post(body).build();
        return request;
   }


    /**
     *  构建requestbody
     * @param map
     * @return
     */
   private static RequestBody postRequestBody(HashMap<String,String> map){
       FormBody.Builder builder = new FormBody.Builder();
       for(Map.Entry<String,String> i : map.entrySet()){
           builder.add(i.getKey(),i.getValue());
       }
       return builder.build();
   }

    /**
     *   post请求获取返回值
     * @param url
     * @param map
     * @return
     */
   public  String getPostResponseStr(String url,HashMap<String,String> map){
       RequestBody requestBody = postRequestBody(map);
       Request request = postRequest(url,requestBody);
       try {
           Response response = mClient.newCall(request)
                   .execute();
           if(response.isSuccessful()){
               return response.body().string();
           }
       } catch (IOException e) {
           e.printStackTrace();
       }
       return null;
   }
}
