package com.lukechi.android.hellodagger.core.impl;

import javax.inject.Inject;

public class FooConfig {

    @Inject
    public FooConfig() {}

    private final String name = "real FooConfig";

    public String getName() {
        return name;
    }

    public void work() {
        System.out.println("fooConfig name is " + name);
    }
}
