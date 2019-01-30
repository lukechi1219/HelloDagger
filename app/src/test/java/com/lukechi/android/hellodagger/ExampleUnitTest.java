package com.lukechi.android.hellodagger;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

// https://developer.android.com/training/testing/fundamentals#assertions
// The Guava team provides a fluent assertions library called Truth.
// Test-Driven Development on Android with the Android Testing Support Library (Google I/O '17)

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    /**
     * Note: Hamcrest is still the preferred library to use when constructing matchers, such as for Espresso's ViewMatcher class.
     */
    @Test
    public void addition_isCorrect() {
        assertThat(2 + 2).isEqualTo(4);
    }
}