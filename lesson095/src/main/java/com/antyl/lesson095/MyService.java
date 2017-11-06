package com.antyl.lesson095;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyService extends Service {

    final String LOG_TAG = "myLogs";
    ExecutorService es;

    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "MyService onCreate");
        es = Executors.newFixedThreadPool(2);
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "MyService onDestroy");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "MyService onStartCommand");

        int time = intent.getIntExtra(MainActivity.PARAM_TIME, 1);
        PendingIntent pendingIntent = intent.getParcelableExtra(MainActivity.PARAM_PINTENT);

        MyRun myRun = new MyRun(time, startId, pendingIntent);
        es.execute(myRun);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    class MyRun implements Runnable {

        int time;
        int startId;
        PendingIntent pendingIntent;

        public MyRun(int time, int startId, PendingIntent pendingIntent) {
            this.time = time;
            this.startId = startId;
            this.pendingIntent = pendingIntent;
            Log.d(LOG_TAG, "MyRun#" + startId + " create");
        }

        @Override
        public void run() {
            Log.d(LOG_TAG, "MyRun#" + startId + " start, time = " + time);
            try {
                pendingIntent.send(MainActivity.STATUS_START);
                TimeUnit.SECONDS.sleep(time);

                Intent intent = new Intent().putExtra(MainActivity.PARAM_RESULT, time * 100);
                pendingIntent.send(MyService.this, MainActivity.STATUS_FINISH, intent);

            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stop();
        }

        void stop() {
            Log.d(LOG_TAG, "MyRun#" + startId + " end, stopSelfResult(" + startId + ")" + stopSelfResult(startId));
        }

    }
}
