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

import java.util.ArrayList;
/**
 * Created by gena on 17.11.2015.
 */
public class Time{
    String hour;
    ArrayList<String> minutes= new ArrayList<String>();
    public Time (){}
    public void Set_hour(String hour){
        this.hour=hour;
    }
    public int Get_size(){
        return minutes.size();
    }
    public String Get_hour(){
        return this.hour;
    }
    public String Get_element_minutes(int j){
        return minutes.get(j);
    }
    public void Add_minutes(String minute){
        minutes.add(minute);
    }
}
