package com.moudle.dragger2s;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.moudle.dragger2s.annotation.PersonWithOther;
import com.moudle.dragger2s.annotation.PersonWithUser;
import com.moudle.dragger2s.bean.Database;
import com.moudle.dragger2s.bean.Person;
import com.moudle.dragger2s.bean.User;
import com.moudle.dragger2s.component.AppComponent;
import com.moudle.dragger2s.component.DaggerAppComponent;
import com.moudle.dragger2s.component.DaggerOtherComponent;
import com.moudle.dragger2s.component.OtherComponent;
import com.moudle.dragger2s.moudle.AppMoudle;
import com.moudle.dragger2s.moudle.MainMoudle;
import com.moudle.dragger2s.moudle.OtherMoudle;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * 1 : moudle 和 provider成对出现。  @single注解在provider和component中成对出现；
 * <p>
 * 2 ：保证@singleton在不同的类中，依旧单例，定义一个全局的Component对象；
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    @Named("context")
    @Inject
    Person person;

    @Named("context")
    @Inject
    Person person1;

    @Named("empty")
    @Inject
    Person person2;

    @PersonWithUser
    @Inject
    Person person3;

    @Inject
    User mUser;

    @Inject
    Database mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dragger);
//        MainComponent build = DaggerMainComponent.builder()
//                .mainMoudle(new MainMoudle())
//                .build();
//        build.inject(this);
        //初始化，为了是@singleton单例在不同的类中，依旧单例；
        ComponetConfigs.getInstance(this).inject(this);
        findViewById(R.id.btn_second).setOnClickListener(this);

        Log.d(TAG, "onCreate: " + person.hashCode());
        Log.d(TAG, "onCreate: " + person1.hashCode());
        Log.d(TAG, "onCreate: " + person2.hashCode());
        Log.d(TAG, "onCreate: " + person3.hashCode() + "  " + person3.mUser.getName() + "  " + person3.mUser.getAge());

        //依赖原因： AppComponent中的对象。为了提供给Module;
        Log.d(TAG, "onCreate: " + mUser.getAge());
        //依赖原因 ： 是因为一个Activity只能注入一次；为了借助manMoudle的注入；
        Log.d(TAG, "onCreate: " + mDatabase.getLabel() + "  " + mDatabase.getVersionCode());
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}
