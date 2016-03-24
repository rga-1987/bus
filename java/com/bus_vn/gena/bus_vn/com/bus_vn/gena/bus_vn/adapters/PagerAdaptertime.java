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
package com.bus_vn.gena.bus_vn.com.bus_vn.gena.bus_vn.adapters;
/**
 * Created by gena on 02.10.2015.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.bus_vn.gena.bus_vn.com.bus_vn.gena.bus_vn.tabs.Tab_time;
import java.util.ArrayList;

public class PagerAdaptertime extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    String busStopId;
    String busPathId;
    ArrayList<String> kolTypeDay =new ArrayList<String>();;

    public PagerAdaptertime(FragmentManager fm, int numOfTabs, String busPathId,String busStopId,ArrayList<String> kol_type_day){
        super(fm);
        this.mNumOfTabs = numOfTabs;
        this.busStopId=busStopId;
        this.busPathId=busPathId;
        this.kolTypeDay=kol_type_day;
    }
    @Override
    public Fragment getItem(int position){
        if(kolTypeDay.size()==2){
            switch (position) {
                case 0:
                    Tab_time tab1 = new Tab_time(busPathId,busStopId,"1");
                    return tab1;
                case 1:
                    Tab_time tab2 = new Tab_time(busPathId,busStopId,"2");
                    return tab2;
                default:
                    return null;
            }
        }
        if(kolTypeDay.size()==1){
            if(kolTypeDay.get(0).equals("4")){
                Tab_time tab1 = new Tab_time(busPathId,busStopId, "4");
                return tab1;
            }
            else {
                Tab_time tab1 = new Tab_time(busPathId,busStopId,"1");
                return tab1;
            }
        }
        if(kolTypeDay.size()==3){
            switch (position) {
                case 0:
                    Tab_time tab1 = new Tab_time(busPathId,busStopId,"1");
                    return tab1;
                case 1:
                    Tab_time tab2 = new  Tab_time(busPathId,busStopId,"3");
                    return tab2;
                case 2:
                    Tab_time tab3 = new  Tab_time(busPathId,busStopId,"2");
                    return tab3;
                default:
                    return null;
            }
        }
        return null;
    }
    @Override
    public int getCount(){
        return mNumOfTabs;
    }
}