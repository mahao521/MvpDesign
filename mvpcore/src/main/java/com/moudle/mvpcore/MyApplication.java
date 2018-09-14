package com.moudle.mvpcore;

import android.app.Application;

import com.moudle.mvpcore.app.ConfigKeys;
import com.moudle.mvpcore.app.ProjectInit;

/**
 * Created by Administrator on 2018/8/24.
 */

public class MyApplication extends Application {

    //http://p.gdown.baidu.com/a273dd4fc1c1d202615f0641c0d042523cc0b41345f22efff4eea5ff7fd436dbc0b668b2807c4cff0b93f4525be7d13d2b90841d802e91c6808a8ea36e2e96e1967627ccd832b66b7c87c4e602070540e8f3f2a027eca023c7bff94d8a78a1883632b7a9c1502ac0df21cd1bb658872492270cb8d0eed1128c1bd21bc6cdefdcddce7e9cb7db2e4f13585eb75ff4939143879d6ee25894f6e2a2820e3f53a60d2093102d7e6c0d3ee31177d1b4ae621a3c74b28b0867fc3ee741d6ce3590af1971facd6f41e73ed7e84b6a27344e50b49b8a4e0b9d05c3acd3e61400375e5ac485c81d0818b9670c6664b5bd87e71518
    @Override
    public void onCreate() {
        super.onCreate();
        ProjectInit.init(this)
                .withApiHost("http://restapi.amap.com")
                .configure();
    }
}
