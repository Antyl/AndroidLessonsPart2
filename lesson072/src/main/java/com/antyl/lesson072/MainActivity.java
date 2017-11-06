package com.antyl.lesson072;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvInfo, tvInfo2;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = (TextView) findViewById(R.id.tvInfo);
        tvInfo2 = (TextView) findViewById(R.id.tvInfo2);
        sp = PreferenceManager.getDefaultSharedPreferences(this);
    }

    protected void onResume() {
        String listValue = sp.getString("list", "не выбрано");
        tvInfo.setText("Значение списка - " + listValue);
        String screenValue = sp.getString("categ1", "не выбрано");
        tvInfo2.setText("Значения скрин - " + screenValue);
        super.onResume();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem menuItem = menu.add(0, 1, 0, "Preferences");
        menuItem.setIntent(new Intent(this,PrefActivity.class));
        return super.onCreateOptionsMenu(menu);
    }
}
