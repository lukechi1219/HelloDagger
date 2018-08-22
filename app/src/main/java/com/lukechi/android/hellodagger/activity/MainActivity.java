package com.lukechi.android.hellodagger.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lukechi.android.hellodagger.R;

import com.lukechi.android.hellodagger.core.Heater;
import com.lukechi.android.hellodagger.di.Injectable;

import javax.inject.Inject;

// important: use Injectable or other interface to inform dagger to do inject()
public class MainActivity extends AppCompatActivity implements Injectable {

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
