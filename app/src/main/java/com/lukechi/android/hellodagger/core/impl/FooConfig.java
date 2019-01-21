package com.lukechi.android.hellodagger.core.impl;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * https://google.github.io/dagger/android#when-to-inject
 * <p>
 * Constructor injection is preferred whenever possible because javac will ensure that
 * no field is referenced before it has been set, which helps avoid NullPointerExceptions.
 */
@Singleton
public class FooConfig {

    private final Context context;

    private final String ip = "real FooConfig: 127.0.0.1";

    // Constructor injection is preferred
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
