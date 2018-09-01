package com.lukechi.android.hellodagger.core.impl;

import org.junit.Test;

import static org.junit.Assert.*;

public class FooConfigTest {

    @Test
    public void getIp() {
        FooConfig fooConfig = new FooConfig();
        assertEquals("real FooConfig: 127.0.0.1", fooConfig.getIp());
    }
}