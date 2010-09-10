package com.thoughtworks.buildnotifier;

import android.test.ActivityInstrumentationTestCase2;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class com.thoughtworks.buildnotifier.BuildNotifierTest \
 * com.thoughtworks.buildnotifier.tests/android.test.InstrumentationTestRunner
 */
public class BuildNotifierTest extends ActivityInstrumentationTestCase2<BuildNotifier> {

    public BuildNotifierTest() {
        super("com.thoughtworks.buildnotifier", BuildNotifier.class);
    }

}
