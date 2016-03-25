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
package bus_vn.gena.bus_vn.com.bus_vn.adapters;
/**
 * Created by gena on 02.10.2015.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import bus_vn.gena.bus_vn.com.bus_vn.tabs.Tab_list_bus;

public class PagerAdapterBus extends FragmentStatePagerAdapter{
    int mNumOfTabs;
    String type;
    public PagerAdapterBus(FragmentManager fm, int numOfTabs, String type){
        super(fm);
        this.mNumOfTabs = numOfTabs;
        this.type=type;
    }
    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                Tab_list_bus tab1 = new Tab_list_bus("1",type);
                return tab1;
            case 1:
                Tab_list_bus tab2 = new Tab_list_bus("2",type);
                return tab2;
            default:
                return null;
        }
    }
    @Override
    public int getCount(){
        return mNumOfTabs;
    }
}