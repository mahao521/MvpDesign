package com.moudle.dragger2s;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.moudle.dragger2s.bean.Person;

import javax.inject.Inject;
import javax.inject.Named;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "SecondActivity";

    @Named("context")
    @Inject
    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
       /* MainComponent build = DaggerMainComponent.builder().mainMoudle(new MainMoudle())
                .build();*/
        ComponetConfigs.getInstance(this).inject(this);  //为了确保单例；
        Log.d(TAG,"onCreate" + person.hashCode());
    }
}
