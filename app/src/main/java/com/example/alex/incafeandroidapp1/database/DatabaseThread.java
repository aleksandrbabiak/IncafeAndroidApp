package com.example.alex.incafeandroidapp1.database;

import android.os.Handler;
import android.os.HandlerThread;

import com.example.alex.incafeandroidapp1.utils.WakeLockProvider;

/**
 * Created by Alex on 10.11.2015.
 */
public class DatabaseThread extends HandlerThread {

    private static volatile DatabaseThread instance;

    private Handler handler;


    public static DatabaseThread getInstance() {
        if (instance == null) {
            synchronized (DatabaseThread.class) {
                if (instance == null) {
                    instance = new DatabaseThread();

                }
            }
        }

        return instance;
    }

    private DatabaseThread() {
        super("BackgroundThread");
        start();
        waitUntilReady();


    }

    private void waitUntilReady() {
        handler = new Handler(getLooper());
    }

    public synchronized void stopAllOperations() {
        handler.removeCallbacksAndMessages(null);

    }

    public void handleOperation(final Runnable operation) {

        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    WakeLockProvider.acquire();
                    operation.run();
                } finally {
                    WakeLockProvider.release();
                }
            }
        });
    }

//    public void handleNetworkOperation(final Runnable operation) {
//
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    WifiLockProvider.acquire();
//                    WakeLockProvider.acquire();
//                    operation.run();
//                } finally {
//                    WifiLockProvider.release();
//                    WakeLockProvider.release();
//
//                }
//            }
//        });
//    }

}
