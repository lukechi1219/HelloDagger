package com.lukechi.android.hellodagger.core.impl;

import javax.inject.Inject;

public class Foo {

    @Inject
    public Foo() {}

    private final String name = "real Foo";

    public String getName() {
        return name;
    }

    public void work() {
        System.out.println("foo name is " + name);
    }
}
