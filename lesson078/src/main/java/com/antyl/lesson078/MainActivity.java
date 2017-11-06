package com.antyl.lesson078;

import android.app.TabActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends TabActivity {

    final String TABS_TAG_1 = "Tag 1";
    final String TABS_TAG_2 = "Tag 2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tabHost = getTabHost();
        TabHost.TabSpec tabSpec;

        tabSpec = tabHost.newTabSpec(TABS_TAG_1);
        tabSpec.setContent(tabContentFactory);
        tabSpec.setIndicator("Автоматически");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec(TABS_TAG_2);
        tabSpec.setContent(tabContentFactory);
        tabSpec.setIndicator("Вручную");
        tabHost.addTab(tabSpec);
    }

    TabHost.TabContentFactory tabContentFactory = new TabHost.TabContentFactory() {
        @Override
        public View createTabContent(String tag) {
            if (tag.equals(TABS_TAG_1)) {
                return getLayoutInflater().inflate(R.layout.tab, null);
            }
            else if (tag.equals(TABS_TAG_2)) {
                TextView tv = new TextView(MainActivity.this);
                tv.setText("Это создано вручную");
                tv.setGravity(300);
                return tv;
            }
            return null;
        }
    };
}
