package com.lukechi.android.hellodagger.activity;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import com.lukechi.android.hellodagger.HelloApp;
import com.lukechi.android.hellodagger.core.Heater;
import com.lukechi.android.hellodagger.di.TestAppComponent;
import com.lukechi.android.hellodagger.di.module.MockAppModule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import it.cosenonjaviste.daggermock.DaggerMockRule;

/**
 * http://www.albertgao.xyz/2018/04/24/how-to-mock-dagger-android-injection-in-instrumented-tests-with-kotlin/
 */
@SmallTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Inject
    Heater heater;

    @Rule
    public DaggerMockRule<TestAppComponent> daggerRule = new DaggerMockRule<>(TestAppComponent.class, new MockAppModule())
            .set(new DaggerMockRule.ComponentSetter<TestAppComponent>() {
                @Override
                public void setComponent(TestAppComponent component) {
                    HelloApp app = (HelloApp) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
                    component.inject(app);
                }
            });

    @Before
    public void setUp() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        HelloApp app = (HelloApp) instrumentation.getTargetContext().getApplicationContext();
    }
}
