package com.ssu.mylook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TabHost;

public class ClosetActivity extends ActivityGroup {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closet);

        TabHost host=(TabHost)findViewById(R.id.menu_season);
        host.setup(this.getLocalActivityManager());

        ImageView tabwidget01 = new ImageView(this);
        tabwidget01.setImageResource(R.drawable.tab_01_selector);


        ImageView tabwidget02 = new ImageView(this);
        tabwidget02.setImageResource(R.drawable.tab_02_selector);

        ImageView tabwidget03 = new ImageView(this);
        tabwidget03.setImageResource(R.drawable.tab_03_selector);

        TabHost.TabSpec spec= host.newTabSpec("Tab1");
        spec.setIndicator(tabwidget01)
                .setContent(new Intent(this,ActivityTab1.class));
        host.addTab(spec);

        spec= host.newTabSpec("Tab2");
        spec.setIndicator(tabwidget02)
                .setContent(new Intent(this,ActivityTab2.class));
        host.addTab(spec);

        spec= host.newTabSpec("Tab3");
        spec.setIndicator(tabwidget03)
                .setContent(new Intent(this,ActivityTab3.class));
        host.addTab(spec);

    }
}
