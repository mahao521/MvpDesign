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

    /**
     *   这个是为了下面使用提供Activity；
     *   某些博客是这样写的，但是没有写，也正确；
     * @return
     */
    @Provides
    public MainActivity getMainActivity(){
        return mMainActivity;
    }

    @Provides
    public MahaoPresenter provideMahaoPrecenter(){
        return new MahaoPresenter(mMainActivity);
    }
}
