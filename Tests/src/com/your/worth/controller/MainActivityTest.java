package com.your.worth.controller;

import android.test.ActivityInstrumentationTestCase2;
import com.your.worth.controller.MainActivity;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class com.your.worth.controller.MainActivityTest \
 * com.your.worth.tests/android.test.InstrumentationTestRunner
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivityTest() {
        super("com.your.worth", MainActivity.class);
    }

}
