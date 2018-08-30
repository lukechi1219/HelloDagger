package com.lukechi.android.hellodagger.activity;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * http://www.albertgao.xyz/2018/04/24/how-to-mock-dagger-android-injection-in-instrumented-tests-with-kotlin/
 */
@SmallTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class, false, false);

    @Test
    public void testLaunchActivity() {

        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.lukechi.android.hellodagger", appContext.getPackageName());

        activityTestRule.launchActivity(new Intent());
    }
}
