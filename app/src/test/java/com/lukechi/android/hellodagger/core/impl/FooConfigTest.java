package com.lukechi.android.hellodagger.core.impl;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FooConfigTest {

    @Test
    public void getIp() {
        FooConfig fooConfig = new FooConfig(null);
        assertEquals("real FooConfig: 127.0.0.1", fooConfig.getIp());
    }
}