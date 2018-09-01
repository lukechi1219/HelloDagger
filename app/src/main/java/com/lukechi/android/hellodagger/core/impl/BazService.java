package com.lukechi.android.hellodagger.core.impl;

import javax.inject.Inject;

public class BazService {

    private final BarDAO barDAO;

    @Inject
    public BazService(BarDAO barDAO) {
        this.barDAO = barDAO;
    }

    public void work() {
        System.out.println("work data: " + barDAO.queryAll());
    }
}
