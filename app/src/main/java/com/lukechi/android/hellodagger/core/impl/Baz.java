package com.lukechi.android.hellodagger.core.impl;

import javax.inject.Inject;

public class Baz {

    private final Bar bar;

    @Inject
    public Baz(Bar bar) {
        this.bar = bar;
    }

    public void work() {
        bar.work();
    }
}
