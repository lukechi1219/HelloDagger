package com.lukechi.android.hellodagger.core.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BarDAOTest {

    @Mock
    FooConfig fooConfig;

    @Test
    public void queryAll() {

        when(fooConfig.getIp()).thenReturn("abc");

        BarDAO barDAO = new BarDAO(fooConfig);
        String data = barDAO.queryAll();

        assertEquals("DATA from abc", data);
    }
}