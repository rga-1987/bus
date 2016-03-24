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
package com.bus_vn.gena.bus_vn.com.bus_vn.gena.bus_vn.tabs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.bus_vn.gena.bus_vn.DbOpenHelper;
import com.bus_vn.gena.bus_vn.R;
import com.bus_vn.gena.bus_vn.Time;
import com.bus_vn.gena.bus_vn.com.bus_vn.gena.bus_vn.adapters.ListviewArrayAdapterBusTime;
import java.util.ArrayList;

//класс для работы с фрагментом где содержатся все маршруты автобусов
public class Tab_time extends Fragment implements AdapterView.OnItemClickListener{
    String busPathId;
    String busStopId;
    String typeDay;
    ArrayList<Time> allTime= new ArrayList<Time>();
    public  Tab_time(String busPathId,String busStopId, String typeDay){
        this.busStopId=busStopId;
        this.busPathId=busPathId;
        this.typeDay=typeDay;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v =inflater.inflate(R.layout.tab_time,container,false);
        return v;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        Context context = getActivity();
        DbOpenHelper dbOpenHelper = new DbOpenHelper(context);
        SQLiteDatabase db;
        db = dbOpenHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.clear();

        String busStopPathId="";
        String st="";
        st="SELECT bus_stop_path_table.id  FROM bus_stop_path_table ";
        st=st+" WHERE bus_stop_path_table.Bus_path_id='"+busPathId+"'";
        st=st+" AND bus_stop_path_table.Bus_stop_id='"+busStopId+"'";
        st=st+" AND bus_stop_path_table.Type_day_id='"+typeDay+"'";
        Cursor c = db.rawQuery(st, null);
        while (c.moveToNext()){
            busStopPathId= c.getString(0);
        }
        st="";
        st="SELECT bus_time_table.Time  FROM bus_time_table ";
        st=st+" WHERE bus_time_table.Bus_path_stop_id='"+busStopPathId+"'";
        st=st+" AND bus_time_table.Type_day_id='"+typeDay+"'";
        c = db.rawQuery(st, null);
        ArrayList<String>  busTimeArray= new ArrayList<String>();
        while (c.moveToNext()){
            String str=c.getString(0).replaceAll(" ", "");
            if(str.equals("-")) {}
            else {
                busTimeArray.add(c.getString(0));
            }
        }
        String st3;
        String hour;
        for (int i = 0; i < busTimeArray.size(); i++){
            st3=busTimeArray.get(i);
            hour =st3.substring(0, st3.indexOf(':'));
            int k=0;
            int number=0;
            for (int j=0;j<allTime.size();j++){
                if(hour.equals(allTime.get(j).Get_hour())){
                    k=1;
                    number=j;
                }
            }
            if (k==0){
                Time myTime = new Time();
                myTime.Set_hour(hour);
                myTime.Add_minutes(st3.substring(st3.indexOf(':') + 1, st3.length()));
                allTime.add(myTime);
            }
            else {
                allTime.get(number).Add_minutes(st3.substring(st3.indexOf(':') + 1, st3.length()));
            }
        }
        ArrayList<String> hours= new ArrayList<String>();
        for (int i = 0; i < allTime.size(); i++){
            hours.add(allTime.get(i).Get_hour());
        }
        String[] resultsHours = hours.toArray(new String[hours.size()]);
        ListView listView = (ListView) getView().findViewById(R.id.lv);
        listView.setOnItemClickListener((android.widget.AdapterView.OnItemClickListener) this);
        ListviewArrayAdapterBusTime caa;
        caa = new ListviewArrayAdapterBusTime(getActivity(),resultsHours,allTime);
        listView.setAdapter(caa);
    }
    //обработчик нажатия на элемент gridView
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id){}
}

