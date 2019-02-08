package com.lukechi.android.hellodagger.activity;

import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.lukechi.android.hellodagger.FakeHelloApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import static com.google.common.truth.Truth.assertThat;

@RunWith(AndroidJUnit4.class)
@Config(application = FakeHelloApp.class)
public class MainActivityTestRobolectric {

//    /**
//     * IMPORTANT for testing RxJava with Robolectric
//     */
//    @BeforeClass
//    public static void setupClass() {
//        RxAndroidPlugins.setInitMainThreadSchedulerHandler(
//                __ -> Schedulers.trampoline());
//    }

    @Test
    public void testLaunchActivity() throws InterruptedException {

        // GIVEN
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);

        // WHEN
        scenario.moveToState(Lifecycle.State.CREATED);
//        scenario.moveToState(Lifecycle.State.INITIALIZED);
//        scenario.moveToState(Lifecycle.State.STARTED);
//        scenario.moveToState(Lifecycle.State.RESUMED);
//        scenario.moveToState(Lifecycle.State.DESTROYED);

        // THEN check dagger injection Not Null
        scenario.onActivity(activity -> {
            assertThat(activity.myHeater).isNotNull();
            assertThat(activity.bazService).isNotNull();
            assertThat(activity.thirdParty).isNotNull();
            assertThat(activity.todService).isNotNull();
            assertThat(activity.parkingLotDao).isNotNull();
            assertThat(activity.parkingLotsViewModelFactory).isNotNull();
        });

        Thread.sleep(3000); // wait api return for test
    }
}
