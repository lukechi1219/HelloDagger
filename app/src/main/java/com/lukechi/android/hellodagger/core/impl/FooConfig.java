package com.lukechi.android.hellodagger.core.impl;

import javax.inject.Inject;

public class FooConfig {

    private final String ip = "real FooConfig: 127.0.0.1";

    @Inject
    public FooConfig() {
        System.out.println("init fooConfig " + ip);
    }

    public String getIp() {
        return ip;
    }
}
