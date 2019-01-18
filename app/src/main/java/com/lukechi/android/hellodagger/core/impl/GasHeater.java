package com.lukechi.android.hellodagger.core.impl;

import android.content.Context;
import com.lukechi.android.hellodagger.core.Heater;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
public class GasHeater implements Heater {

    // Dagger does not support injection into private fields
    // final inject is better ? compare to FooConfig
    @Inject
    @Named("ApplicationContext")
    Context context;

    @Inject
    public GasHeater() {
    }

    @Override
    public void heat() {
        System.out.println("heat with gas. context: " + this.context);
    }
}
