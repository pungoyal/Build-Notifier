package com.thoughtworks.buildnotifier.tests;

import android.content.Intent;
import android.os.IBinder;
import android.test.ServiceTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;
import com.thoughtworks.buildnotifier.services.BuildMonitorService;

public class BuildMonitorServiceTest extends ServiceTestCase<BuildMonitorService> {
    public BuildMonitorServiceTest(Class<BuildMonitorService> serviceClass) {
        super(serviceClass);
    }

    @SmallTest
    public void testStartable() {
        Intent startIntent = new Intent();
        startIntent.setClass(getContext(), BuildMonitorService.class);
        startService(startIntent);
        assertEquals(1, 2);
    }

    @MediumTest
    public void testBindable() {
        Intent intent = new Intent();
        intent.setClass(getContext(), BuildMonitorService.class);
        IBinder service = bindService(intent);
    }
}
