package com.lukechi.android.hellodagger.core.impl;

import javax.inject.Inject;

public class BarDAO {

    //    @Inject
//    FooConfig fooConfig; // field inject
    private final FooConfig fooConfig;

    // Constructor injection is preferred
    @Inject
    public BarDAO(FooConfig fooConfig) {
        this.fooConfig = fooConfig;
    }

    public String queryAll() {
        System.out.println("queryAll from ip: " + fooConfig.getIp());
        return "DATA from " + fooConfig.getIp();
    }
}
