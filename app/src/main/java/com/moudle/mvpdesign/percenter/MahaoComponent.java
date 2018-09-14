package com.moudle.mvpdesign.percenter;
import com.moudle.mvpdesign.MainActivity;
import com.moudle.mvpdesign.dagger.MahaoPrecenterMoudle;

import dagger.Component;
/**
 * Created by Administrator on 2018/8/24.
 */

@Component(modules = MahaoPrecenterMoudle.class)
public interface MahaoComponent {
    void inject(MainActivity mainActivity);
}
