package com.antyl.lesson085;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = (TextView) findViewById(R.id.tvInfo);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    runOnUiThread(runnable1);
                    TimeUnit.SECONDS.sleep(1);
                    tvInfo.postDelayed(runnable3, 2000);
                    tvInfo.post(runnable2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    Runnable runnable1 = new Runnable() {
        @Override
        public void run() {
            tvInfo.setText("runnable 1");
        }
    };

    Runnable runnable2 = new Runnable() {
        @Override
        public void run() {
            tvInfo.setText("runnable 2");
        }
    };

    Runnable runnable3 = new Runnable() {
        @Override
        public void run() {
            tvInfo.setText("runnable 3");
        }
    };
}
