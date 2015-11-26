package com.example.alex.incafeandroidapp1.utils;

import android.content.Context;
import android.os.PowerManager;
import android.util.Log;

/**
 * Created by Alex on 10.11.2015.
 */
public class WakeLockProvider {
    private static final String LOG_TAG = WakeLockProvider.class.getSimpleName();

    private static volatile int counter = 0;

    private static volatile PowerManager.WakeLock lockStatic = null;

    private static Context context;

    private WakeLockProvider() {
    }

    private static PowerManager.WakeLock getLock() {
        if (lockStatic == null) {
            PowerManager mgr = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            lockStatic = mgr.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, LOG_TAG);
            lockStatic.setReferenceCounted(true);
        }
        return lockStatic;
    }

    public static void initialize(Context context) {
        WakeLockProvider.context = context;
    }

    public synchronized static int acquire() {
        getLock().acquire();
        ++counter;

        Log.d(LOG_TAG, "Counter = " + counter);

        return counter;
    }

    public synchronized static int release() {
        PowerManager.WakeLock lock = getLock();
        if (lock.isHeld()) {
            lock.release();
            --counter;
        }

        Log.d(LOG_TAG, "Counter = " + counter);

        return counter;
    }
}
