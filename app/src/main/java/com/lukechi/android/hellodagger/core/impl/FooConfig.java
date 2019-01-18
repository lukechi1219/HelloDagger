package com.lukechi.android.hellodagger.core.impl;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class FooConfig {

    // final inject is better ? compare to GasHeater
    //    @Inject
    private final Context context;

    private final String ip = "real FooConfig: 127.0.0.1";

    @Inject
    public FooConfig(Context context) {
        this.context = context;
    }

    public String getIp() {
        System.out.println("getIp() fooConfig " + ip);
        System.out.println(context);
        return ip;
    }
}
