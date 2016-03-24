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

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.bus_vn.gena.bus_vn.com.bus_vn.gena.bus_vn.adapters.PagerAdaptertime;
import java.util.ArrayList;
/**
 * Created by gena on 18.09.2015.
 */
public class List_bus_time extends AppCompatActivity{
    TabLayout tabLayout;
    Toolbar toolbar;
    String busPathId;   //Остановка
    String busStopId;   //Остановка
    String typeDay;
    ArrayList<String> kolTypeDay=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_bus);
        setTitle("Выбор остановки");

        Intent intent = getIntent();
        busPathId = intent.getStringExtra("busPathId");
        busStopId = intent.getStringExtra("busStopId");
        typeDay=intent.getStringExtra("typeDay");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Context context = getApplicationContext();
        DbOpenHelper dbOpenHelper = new DbOpenHelper(context);
        SQLiteDatabase db;
        db = dbOpenHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        //находим количество Type_day (Будни,Выходные,Суббота)
        cv.clear();
        String st="";
        st="SELECT DISTINCT Type_day_id FROM bus_stop_path_table ";
        st=st+"INNER JOIN bus_path_table ON  bus_stop_path_table.Bus_path_id=bus_path_table.id ";
        st=st+" WHERE bus_path_table.id='"+busPathId+"'";
        Cursor c = db.rawQuery(st, null);
        kolTypeDay.clear();
        while (c.moveToNext()){
            String st2="";
            st2 = c.getString(0);
            kolTypeDay.add(st2);
        }
        cv.clear();
        setupTablayout();
    }
    private void setupTablayout(){
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        switch (kolTypeDay.size()){
            case 1:
                if(kolTypeDay.get(0).equals("4")){
                    tabLayout.addTab(tabLayout.newTab().setText("ЕЖЕДНЕВНО"));
                }
                else{
                    tabLayout.addTab(tabLayout.newTab().setText("РАБОЧИЕ"));
                }
                break;
            case 2:
                tabLayout.addTab(tabLayout.newTab().setText("РАБОЧИЕ"));
                tabLayout.addTab(tabLayout.newTab().setText("ВЫХОДНЫЕ"));
                if(typeDay.equals("2")){
                    new Handler().postDelayed(
                        new Runnable(){
                            @Override
                            public void run(){
                                tabLayout.getTabAt(1).select();
                            }
                        }, 1);
                    }
                break;
            case 3:
                tabLayout.addTab(tabLayout.newTab().setText("РАБОЧИЕ"));
                tabLayout.addTab(tabLayout.newTab().setText("СУББОТА"));
                tabLayout.addTab(tabLayout.newTab().setText("ВОСКРЕСЕНЬЕ"));
                //выделяем нужный нам tab на котором была выбрана остановка
                //например если выбрана суббота, то и в активити с временем для этой остановки
                // тоже будет показана суббота
                switch (typeDay){
                    case "2"://выходные
                        new Handler().postDelayed(
                            new Runnable(){
                                @Override
                                public void run(){
                                    tabLayout.getTabAt(2).select();
                                }
                            }, 1);
                        break;
                    case "3"://суббота
                        new Handler().postDelayed(
                            new Runnable(){
                                @Override
                                public void run(){
                                    tabLayout.getTabAt(1).select();
                                }
                            }, 1);
                        break;
                }
                break;
            default:
                break;
        }
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdaptertime adapter = new PagerAdaptertime  (getSupportFragmentManager(), tabLayout.getTabCount(),busPathId,busStopId,kolTypeDay);
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
}