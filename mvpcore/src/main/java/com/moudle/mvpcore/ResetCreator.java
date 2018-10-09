package com.moudle.mvpcore;

import android.support.annotation.NonNull;

import com.moudle.mvpcore.app.ConfigKeys;
import com.moudle.mvpcore.app.ProjectInit;
import com.moudle.mvpcore.rx.RxRestService;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by Administrator on 2018/8/23.
 */

public class ResetCreator {

  private static final class RetrofitHolder{

      private static final String BASE_URL = ProjectInit.getConfiguration(ConfigKeys.API_HOST);
      private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
              .baseUrl(BASE_URL)
              .addConverterFactory(ScalarsConverterFactory.create())
              .callbackExecutor(new Executor() {
                  @Override
                  public void execute(@NonNull Runnable command) {

                  }
              })
              .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())//设置异步，同步
              .client(OKHttpHolder.OK_HTTP_CLIENT)
              .build();
  }

  private static final class OKHttpHolder{
      private static final int TIME_OUT = 60;
      private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
              .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
              .build();
  }

  private static final class RestServiceHolder{
      private static final ResetService REST_SERVICE = RetrofitHolder.RETROFIT_CLIENT.create(ResetService.class);
  }

  public static ResetService getRestService(){
        return  RestServiceHolder.REST_SERVICE;
  }

  private static final class RxRestServiceHolder{
      private static final RxRestService RX_REST_SERVICE = RetrofitHolder.RETROFIT_CLIENT.create(RxRestService.class);
  }

  public static RxRestService getRxRestService(){
      return RxRestServiceHolder.RX_REST_SERVICE;
  }


}


