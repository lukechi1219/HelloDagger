package com.lukechi.android.hellodagger.activity;

import android.os.Bundle;

import com.lukechi.android.hellodagger.R;
import com.lukechi.android.hellodagger.core.Heater;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

// DaggerAppCompatActivity
// https://medium.com/@ffvanderlaan/you-could-also-have-your-baseactivity-extend-daggerappcompatactivity-then-you-would-not-need-e5faf54fad4e
public class MainActivity extends DaggerAppCompatActivity {

    @Inject
    Heater myHeater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // auto injected!!!
        myHeater.heat();
    }
}
