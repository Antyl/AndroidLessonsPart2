package com.antyl.lesson093;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyService extends Service {

    final String LOG_TAG = "myLogs";
    ExecutorService executorService;
    Object someRes;

    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "MyService onCreate");
        executorService = Executors.newFixedThreadPool(3);
        someRes = new Object();
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "MyService onDestroy");
        someRes = null;
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "MyService onStartCommand");
        int time = intent.getIntExtra("time", 1);
        MyRun mr = new MyRun(time, startId);
        executorService.execute(mr);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    class MyRun implements Runnable {

        int time;
        int startId;

        public MyRun(int time, int startId) {
            this.time = time;
            this.startId = startId;
            Log.d(LOG_TAG, "MyRun#" + startId + " create");
        }

        @Override
        public void run() {
            Log.d(LOG_TAG, "MyRun#" + startId + " start, time = " + time);
            try {
                TimeUnit.SECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Log.d(LOG_TAG, "MyRun#" + startId + " someRes = " + someRes.getClass());
            } catch (NullPointerException e) {
                Log.d(LOG_TAG, "MyRun#" + startId + " error, null pointer");
            }
            stop();
        }

        void stop() {
            Log.d(LOG_TAG, "MyRun#" + startId + " end, stopSelf(" + startId + ") = " + stopSelfResult(startId));
        }
    }
}
