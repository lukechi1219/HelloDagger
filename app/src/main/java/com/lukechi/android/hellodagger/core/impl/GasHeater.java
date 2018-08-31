package com.lukechi.android.hellodagger.core.impl;

import android.content.Context;

import com.lukechi.android.hellodagger.core.Heater;

import javax.inject.Inject;

public class GasHeater implements Heater {

    private final Context context;

    // final inject is better
    @Inject
    public GasHeater(Context context) {
        this.context = context;
    }

    @Override
    public void heat() {
        System.out.println("heat with gas. context: " + this.context);
    }
}
