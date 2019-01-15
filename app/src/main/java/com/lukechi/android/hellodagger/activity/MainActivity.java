package com.lukechi.android.hellodagger.activity;

import android.os.Bundle;

import com.lukechi.android.hellodagger.R;
import com.lukechi.android.hellodagger.core.Heater;
import com.lukechi.android.hellodagger.core.impl.BazService;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * A class shouldn’t know anything about how it is injected. So we hide inject code into DaggerAppCompatActivity
 */
// DaggerAppCompatActivity
// https://medium.com/@ffvanderlaan/you-could-also-have-your-baseactivity-extend-daggerappcompatactivity-then-you-would-not-need-e5faf54fad4e
public class MainActivity extends DaggerAppCompatActivity {

    @Inject
    Heater myHeater;

    @Inject
    BazService bazService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // auto injected!!!
        myHeater.heat();
        bazService.work();
    }
}
