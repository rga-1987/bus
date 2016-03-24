/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bus_vn.gena.bus_vn;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.bus_vn.gena.bus_vn.com.bus_vn.gena.bus_vn.adapters.PagerAdapterBus;
/**
 * Created by gena on 18.09.2015.
 */
public class List_bus extends AppCompatActivity{
    TabLayout tabLayout;
    Toolbar toolbar;
    String type;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_bus);
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Выбор маршрута");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupTablayout();
    }
    private void setupTablayout(){
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.addTab(tabLayout.newTab().setText("АВТОБУСЫ"));
        tabLayout.addTab(tabLayout.newTab().setText("ТРОЛЛЕЙБУСЫ"));

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapterBus adapter = new PagerAdapterBus  (getSupportFragmentManager(), tabLayout.getTabCount(),type);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab){
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab){}
            @Override
            public void onTabReselected(TabLayout.Tab tab){}
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
    }
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
    }
}