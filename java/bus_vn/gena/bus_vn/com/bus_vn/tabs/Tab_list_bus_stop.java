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
package bus_vn.gena.bus_vn.com.bus_vn.tabs;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
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
import bus_vn.gena.bus_vn.com.bus_vn.DbOpenHelper;
import bus_vn.gena.bus_vn.com.bus_vn.R;
import bus_vn.gena.bus_vn.com.bus_vn.adapters.ListviewArrayAdapter;
import bus_vn.gena.bus_vn.com.bus_vn.List_bus_time;
import java.util.ArrayList;

//класс для работы с фрагментом где содержатся все маршруты автобусов
public class Tab_list_bus_stop extends Fragment  implements AdapterView.OnItemClickListener {
    private String busPathId;
    private String typeDay;
    ArrayList<String> results= new ArrayList<String>();

    public  Tab_list_bus_stop (String busPathId, String typeDay){
        this.busPathId=busPathId;
        this.typeDay=typeDay;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab_list_bus_stop,container,false);
        return v;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Context context = getActivity();
        DbOpenHelper dbOpenHelper = new DbOpenHelper(context);
        SQLiteDatabase db;
        db = dbOpenHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.clear();
        String st;
        st="";
        st="SELECT bus_stop_table.Name FROM bus_stop_path_table INNER JOIN bus_stop_table ON  bus_stop_path_table.Bus_stop_id=bus_stop_table.id ";
        st=st+"INNER JOIN bus_path_table ON  bus_stop_path_table.Bus_path_id=bus_path_table.id ";
        st=st+" WHERE bus_stop_path_table.Bus_path_id='"+busPathId+"'";
        st=st+ " AND bus_stop_path_table.Type_day_id='"+typeDay+"'";
        Cursor c = db.rawQuery(st, null);

        while (c.moveToNext()){
            String st2="";
            st2 = c.getString(0);
            results.add(st2);
        }
        // находим список
        ListView lvMain = (ListView) getView().findViewById(R.id.listView1);
        lvMain.setDivider(null);//убираем рамку вокруг элемента ListView
        //массив остановок
        String[] values = results.toArray(new String[results.size()]);

        //Создаем custom adapter для listview
        //Устанавливаем ClickListener для listview
        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            //обработка нажатия
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                //считываем название остановки
                String busStop = (String) parent.getItemAtPosition(position);
                String busStopId ="";
                Context context = getActivity();
                DbOpenHelper dbOpenHelper = new DbOpenHelper(context);
                SQLiteDatabase db;
                db = dbOpenHelper.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.clear();

                String st = "";
                st = "SELECT bus_stop_table.id FROM bus_stop_table ";
                st = st + " WHERE bus_stop_table.Name='" + busStop + "'";
                Cursor c = db.rawQuery(st, null);
                while (c.moveToNext()) {
                    busStopId=c.getString(0);
                }
                Intent intent = new Intent(getActivity(), List_bus_time.class);
                intent.putExtra("busPathId", busPathId);
                intent.putExtra("busStopId", busStopId);
                intent.putExtra("typeDay",typeDay);
                //запускаем активити с остановками данного маршрута
                startActivity(intent);
                //делаем анимацию при переходе между активити
                // overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);**/
            }
        });
        ListviewArrayAdapter adapter = new ListviewArrayAdapter(getActivity(), values,values.length-1);
        //подключаем наш адаптер к  listview
        lvMain.setAdapter(adapter);
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
    }
}