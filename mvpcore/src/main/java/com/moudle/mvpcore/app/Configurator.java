package com.moudle.mvpcore.app;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;
import okhttp3.internal.Internal;

/**
 * Created by Administrator on 2018/8/22.
 */

public class Configurator {

    private static final HashMap<Object,Object> CONFIGS = new HashMap<>();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

    public static class Holder{
        private static final Configurator INSTANCE = new Configurator();
    }

    private  Configurator(){
        CONFIGS.put(ConfigKeys.CONFIG_READY.name(),false);
    }

    public static Configurator getInstance(){
        return Holder.INSTANCE;
    }

    //获取配置信息
    final HashMap<Object,Object> getConfigs(){
        return CONFIGS;
    }

    final <T> T getConfiguration(Object key){
        checkConfiguration();
        final Object object = CONFIGS.get(key);
        if(object == null){
            throw new NullPointerException(key.toString()+"IS NULL");
        }
        return (T)getConfigs().get(key);
    }

    //配置APIHOST
    public final Configurator withApiHost(String host){
        CONFIGS.put(ConfigKeys.API_HOST,host);
        return this;
    }



    private void checkConfiguration(){
        final boolean isReady = (boolean) CONFIGS.get(ConfigKeys.CONFIG_READY.name());
        if(!isReady){
            throw new RuntimeException("Configuration is not ready,call configure()");
        }
    }

    //配置完成
    public final void configure(){
        CONFIGS.put(ConfigKeys.CONFIG_READY.name(),true);
    }


}
