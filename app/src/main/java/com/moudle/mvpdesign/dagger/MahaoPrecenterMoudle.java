package com.moudle.mvpdesign.dagger;

import com.moudle.mvpdesign.MainActivity;
import com.moudle.mvpdesign.percenter.MahaoPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/8/24.
 */
@Module
public class MahaoPrecenterMoudle {
    MainActivity mMainActivity;

    public MahaoPrecenterMoudle(MainActivity mainActivity){
        this.mMainActivity = mainActivity;
    }

    @Provides
    public MahaoPresenter provideMahaoPrecenter(){
        return new MahaoPresenter(mMainActivity);
    }
}
