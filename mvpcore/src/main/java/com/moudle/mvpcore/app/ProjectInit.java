package com.moudle.mvpcore.app;

import android.content.Context;

/**
 * Created by Administrator on 2018/8/23.
 */

public class ProjectInit {
    public  static Configurator init(Context context){
        Configurator.getInstance()
                .getConfigs()
                .put(ConfigKeys.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator(){
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key){
        return getConfigurator().getConfiguration(key);
    }

    public static Context getApplicationContext(){
        return getConfiguration(ConfigKeys.APPLICATION_CONTEXT.name());
    }
}
















