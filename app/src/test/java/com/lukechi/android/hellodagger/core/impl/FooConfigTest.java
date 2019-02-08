package com.lukechi.android.hellodagger.core.impl;

import android.content.Context;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class FooConfigTest {

    @Test
    public void getIp() {
        Context appContext = ApplicationProvider.getApplicationContext();
        FooConfig fooConfig = new FooConfig(appContext);
        assertEquals("real FooConfig: 127.0.0.1", fooConfig.getIp());
    }
}