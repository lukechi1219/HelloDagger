package com.lukechi.android.hellodagger.core.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BazServiceTest {

    @Mock
    BarDAO barDAO;

    @Before
    public void setup() {
        when(barDAO.queryAll()).thenReturn("def");
    }

    @Test
    public void work() {
        BazService bazService = new BazService(barDAO);
        String result = bazService.work();

        assertEquals("work data: def", result);
    }
}