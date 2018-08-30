package com.lukechi.android.hellodagger;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

// need this to new FakeHelloApp -> DaggerTestAppComponent -> TestAppModule
public class MyTestInstrumentationRunner extends AndroidJUnitRunner {

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return super.newApplication(cl, FakeHelloApp.class.getName(), context);
    }
}
