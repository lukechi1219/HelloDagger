package com.lukechi.android.hellodagger.core.impl;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class BazService {

    @Inject
    BarDAO barDAO; // field inject

    @Inject
    public BazService() {
        // tell dagger can new this class
    }

    public String work() {
        String result = "work data: " + barDAO.queryAll();
        System.out.println(result);
        return result;
    }
}
