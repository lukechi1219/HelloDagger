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

        // https://developer.android.com/guide/components/activities/activity-lifecycle#alc
        // WHEN
        // Initialized state for a LifecycleOwner. For an Activity, this is the state when it is constructed but has not received onCreate yet.
//        scenario.moveToState(Lifecycle.State.INITIALIZED);
        // Created state for a LifecycleOwner. For an Activity, this state is reached in two cases: after onCreate call; right before onStop call.
        scenario.moveToState(Lifecycle.State.CREATED);
        // Started state for a LifecycleOwner. For an Activity, this state is reached in two cases: after onStart call; right before onPause call.
//        scenario.moveToState(Lifecycle.State.STARTED);
        // Resumed state for a LifecycleOwner. For an Activity, this state is reached after onResume is called.
//        scenario.moveToState(Lifecycle.State.RESUMED);
        // Destroyed state for a LifecycleOwner. After this event, this Lifecycle will not dispatch any more events.
        // For instance, for an Activity, this state is reached right before Activity's onDestroy call.
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
