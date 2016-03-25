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
package bus_vn.gena.bus_vn.com.bus_vn;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import bus_vn.gena.bus_vn.com.bus_vn.adapters.PageAdapterBusStop;


import java.util.ArrayList;

/**
 * Created by gena on 09.10.2015.
 */
//Класс для работы с активити со списком остановок (активити с кружочками)
public class List_bus_stop extends AppCompatActivity{
    TabLayout tabLayout;
    Toolbar toolbar;
    String busPathId;   //номер автобуса или троллейбуса
    ArrayList<String> kolTypeDay= new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_bus_stop);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Выбор остановки");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        busPathId = intent.getStringExtra("busPathId");
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
                break;
            case 3:
                tabLayout.addTab(tabLayout.newTab().setText("РАБОЧИЕ"));
                tabLayout.addTab(tabLayout.newTab().setText("СУББОТА"));
                tabLayout.addTab(tabLayout.newTab().setText("ВОСКРЕСЕНЬЕ"));
                break;
            default:
                break;
        }
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PageAdapterBusStop adapter = new PageAdapterBusStop(getSupportFragmentManager(), tabLayout.getTabCount(),busPathId,kolTypeDay);
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