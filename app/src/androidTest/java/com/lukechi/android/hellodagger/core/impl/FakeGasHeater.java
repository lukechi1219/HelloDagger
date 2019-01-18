package com.lukechi.android.hellodagger.core.impl;

import com.lukechi.android.hellodagger.core.Heater;

import javax.inject.Inject;

public class FakeGasHeater implements Heater {

    @Inject
    public FakeGasHeater() {
    }

    @Override
    public void heat() {
        System.out.println("heat with gas. Faked.");
    }
}
