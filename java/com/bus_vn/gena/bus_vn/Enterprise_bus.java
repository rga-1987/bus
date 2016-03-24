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
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Enterprise_bus extends AppCompatActivity implements View.OnClickListener{
    Toolbar toolbar;
    TextView txtSite;
    TextView txtTel_1_1;
    TextView txtTel_1_2;
    TextView txtTel_2_1;
    TextView txtTel_3_1;
    TextView txtTel_3_2;
    TextView txtTel_4_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enterprise_bus);

        setTitle("Предприятия перевозчики");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtSite = (TextView)findViewById(R.id.site);
        txtSite.setOnClickListener(this);

        txtTel_1_1= (TextView)findViewById(R.id.tel_1_1);
        txtTel_1_1.setOnClickListener(this);

        txtTel_1_2= (TextView)findViewById(R.id.tel_1_2);
        txtTel_1_2.setOnClickListener(this);

        txtTel_2_1= (TextView)findViewById(R.id.tel_2_1);
        txtTel_2_1.setOnClickListener(this);

        txtTel_3_1= (TextView)findViewById(R.id.tel_3_1);
        txtTel_3_1.setOnClickListener(this);

        txtTel_3_2= (TextView)findViewById(R.id.tel_3_2);
        txtTel_3_2.setOnClickListener(this);

        txtTel_4_1= (TextView)findViewById(R.id.tel_4_1);
        txtTel_4_1.setOnClickListener(this);
    }
    //Обработчик для всех действий
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //обработка нажатия кнопки "Выход"
            case R.id.site:
                try {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://buspark53.ru"));
                    startActivity(browserIntent);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.tel_1_1:
                try {
                    Intent myIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + 633416));
                    myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(myIntent);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.tel_1_2:
                try{
                    Intent myIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + 636440));
                    myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(myIntent);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case R.id.tel_2_1:
                try{
                    Intent myIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + 961701));
                    myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(myIntent);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case R.id.tel_3_1:
                try{
                    Intent myIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + 621420));
                    myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(myIntent);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case R.id.tel_3_2:
                try{
                    Intent myIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + 621565));
                    myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(myIntent);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case R.id.tel_4_1:
                try{
                    Intent myIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + 961701));
                    myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(myIntent);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                break;
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}