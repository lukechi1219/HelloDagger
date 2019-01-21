package com.lukechi.android.hellodagger.core.impl;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class BazService {

    //    @Inject
//    BarDAO barDAO; // field inject
    private final BarDAO barDAO;

    // Constructor injection is preferred
    @Inject
    public BazService(BarDAO barDAO) {
        this.barDAO = barDAO;
    }

    public String work() {
        String result = "work data: " + barDAO.queryAll();
        System.out.println(result);
        return result;
    }
}
