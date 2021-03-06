package com.lukechi.android.hellodagger.activity;

import android.content.Context;
import android.content.Intent;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;
import androidx.test.rule.ActivityTestRule;
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
    public final ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class, false, false);

    @Test
    public void testLaunchActivity() {

        // Context of the app under test.
//        Context appContext = InstrumentationRegistry.getTargetContext();
        Context appContext = ApplicationProvider.getApplicationContext();

        assertEquals("com.lukechi.android.hellodagger", appContext.getPackageName());

        activityTestRule.launchActivity(new Intent());
    }
}
