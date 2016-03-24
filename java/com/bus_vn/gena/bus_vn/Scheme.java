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
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
public class Scheme extends AppCompatActivity{
    String numberBus;
    String typeTransport;
    TouchImageView img;
    int resourceID;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scheme);
        setTitle("Схема движения");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        numberBus = intent.getStringExtra("numberBus");
        typeTransport = intent.getStringExtra("typeTransport");
        String drawable = "bus_";
        if(typeTransport.equals("1")){
            drawable="bus_"+numberBus;
        }
        else{
            drawable="trol_"+numberBus;
        }
        resourceID = getResources().getIdentifier(drawable, "drawable", getApplicationInfo().packageName);
        img = new TouchImageView(this);
        img.setImageResource(resourceID);
        img.setMaxZoom(4f);
        setContentView(img);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                img.setImageResource(0);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
