package com.lukechi.android.hellodagger.core.impl;

import android.content.Context;
import com.lukechi.android.hellodagger.core.Heater;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 * https://google.github.io/dagger/android#when-to-inject
 * <p>
 * Constructor injection is preferred whenever possible because javac will ensure that
 * no field is referenced before it has been set, which helps avoid NullPointerExceptions.
 */
@Singleton
public class GasHeater implements Heater {

    private final Context context;

    // Constructor injection is preferred
    @Inject
    public GasHeater(@Named("ApplicationContext") Context context) {
        this.context = context;
    }

    @Override
    public void heat() {
        System.out.println("heat with gas. context: " + this.context);
    }
}
