package com.moudle.dragger2s;

import android.content.Context;

import com.moudle.dragger2s.component.AppComponent;
import com.moudle.dragger2s.component.DaggerAppComponent;
import com.moudle.dragger2s.component.DaggerMainComponent;
import com.moudle.dragger2s.component.DaggerOtherComponent;
import com.moudle.dragger2s.component.MainComponent;
import com.moudle.dragger2s.component.OtherComponent;
import com.moudle.dragger2s.moudle.AppMoudle;
import com.moudle.dragger2s.moudle.MainMoudle;
import com.moudle.dragger2s.moudle.OtherMoudle;

/**
 * Created by Administrator on 2018/10/9.
 */

public class ComponetConfigs {

    private static MainComponent mainComponent;
    private  ComponetConfigs(){}
    public  static MainComponent getInstance(Context context){
        if(mainComponent == null){
            synchronized (ComponetConfigs.class){
                if(mainComponent == null){
                    AppComponent component = DaggerAppComponent.builder().appMoudle(new AppMoudle()).build();
                    OtherComponent component1 = DaggerOtherComponent.builder().otherMoudle(new OtherMoudle(context.getApplicationContext()))
                            .build();
                    mainComponent = DaggerMainComponent.builder()
                            .appComponent(component)
                            .otherComponent(component1)
                            .mainMoudle(new MainMoudle(context.getApplicationContext()))
                            .build();
                }
            }
        }
        return mainComponent;
    }
}
