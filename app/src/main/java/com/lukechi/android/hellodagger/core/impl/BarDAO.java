package com.lukechi.android.hellodagger.core.impl;

import javax.inject.Inject;

public class BarDAO {

    @Inject
    FooConfig fooConfig; // field inject

    @Inject
    public BarDAO() {
        // tell dagger can new this class
    }

    public String queryAll() {
        System.out.println("queryAll from ip: " + fooConfig.getIp());
        return "DATA from " + fooConfig.getIp();
    }
}
