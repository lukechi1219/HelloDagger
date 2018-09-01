package com.lukechi.android.hellodagger.core.impl;

import javax.inject.Inject;

public class BazService {

    private final BarDAO barDAO;

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
