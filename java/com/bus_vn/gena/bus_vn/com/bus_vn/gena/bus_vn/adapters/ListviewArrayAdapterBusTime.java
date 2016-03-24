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
 * Created by gena on 13.10.2015.
 */
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.bus_vn.gena.bus_vn.R;
import com.bus_vn.gena.bus_vn.Time;
import java.util.ArrayList;

public class ListviewArrayAdapterBusTime extends ArrayAdapter<String>{
    private final Context context;
    private final String[] hours;
    ArrayList<Time> allTime = new ArrayList<Time>();

    public ListviewArrayAdapterBusTime(Context context, String[] hours,ArrayList<Time> allTime){
        super(context, R.layout.listview_item_bus_time, hours);
        this.context = context;
        this.hours = hours;
        this.allTime=allTime;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.listview_item_bus_time, parent, false);

        TextView textView = (TextView) rowView.findViewById(R.id.label);
        TextView textView2 = (TextView) rowView.findViewById(R.id.label2);

        textView2.setText(hours[position]+":");
        String st = "";

        for(int j = 0; j < allTime.get(position).Get_size(); j++){
            st = st + " " + allTime.get(position).Get_element_minutes(j);
        }
        textView.setText(st);
        if((position%2)==0){
            rowView.setBackgroundColor(Color.parseColor("#9ac900"));
        }
        else{
        rowView.setBackgroundColor(Color.parseColor("#ededed"));
        }
        return rowView;
    }
}