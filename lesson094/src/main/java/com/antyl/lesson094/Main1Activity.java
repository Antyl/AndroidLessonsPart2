package com.antyl.lesson094;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
    }

    public void onClickStart(View v) {
        startService(new Intent("com.antyl.lesson094_1.MyService").putExtra("name", "value"));
    }
}
