package com.lukechi.android.hellodagger.core.impl;

import javax.inject.Inject;

public class Bar {

    private final Foo foo;

    @Inject
    public Bar(Foo foo) {
        this.foo = foo;
    }

    public void work() {
        foo.work();
    }
}
