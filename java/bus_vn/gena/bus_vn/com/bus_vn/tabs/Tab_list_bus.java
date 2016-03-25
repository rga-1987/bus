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
import android.widget.GridView;
import android.widget.SimpleAdapter;

import bus_vn.gena.bus_vn.com.bus_vn.DbOpenHelper;
import bus_vn.gena.bus_vn.com.bus_vn.List_bus_stop;
import bus_vn.gena.bus_vn.com.bus_vn.R;
import bus_vn.gena.bus_vn.com.bus_vn.Scheme;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//класс для работы с фрагментом где содержатся все маршруты автобусов
public class Tab_list_bus extends Fragment implements AdapterView.OnItemClickListener {
    private GridView gridView;
    //массив данных картинка автобуса+номер маршрута
    private List<Map<String,Object>> dataList;
    private SimpleAdapter simpleAdapter;
    ArrayList<String> results= new ArrayList<String>();
    private  String typeTransport;
    private  String type;
    public  Tab_list_bus(String typeTransport,String type){
        this.typeTransport=typeTransport;
        this.type=type;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v =inflater.inflate(R.layout.tab_list_bus,container,false);
        return v;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        DbOpenHelper dbOpenHelper = new DbOpenHelper(getActivity());
        SQLiteDatabase db;
        db = dbOpenHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.clear();
        String st;
        st="SELECT Name FROM bus_path_table  WHERE bus_path_table.Type_transport_id='"+typeTransport+"'"+"ORDER BY Number";
        Cursor c = db.rawQuery(st, null);
        while (c.moveToNext()){
            String st2="";
            st2 = c.getString(0);
            results.add(st2);
        }
        // находим список
        gridView = (GridView) getView().findViewById(R.id.gridview);
        //Устанавливаем ClickListener для listview
        gridView.setOnItemClickListener((android.widget.AdapterView.OnItemClickListener) this);
        dataList=new ArrayList<Map<String,Object>>();
        dataList=getData();
        //Устанавливаем ClickListener для gridView
        simpleAdapter=new SimpleAdapter(getActivity(), dataList, R.layout.gridview_item, new String[]{"image","text"}, new int[]{R.id.image,R.id.text});
        //подключаем наш адаптер к  listview
        gridView.setAdapter(simpleAdapter);
    }
    //заполняем массив данными
    private List<Map<String, Object>> getData() {
        for(int i=0;i<results.size();i++){
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("image",R.drawable.bus);//картинка автобуса
            map.put("text","Маршрут "+results.get(i));      //номер маршрута
            dataList.add(map);              //добавляем в массив
        }
        return dataList;
    }
    //обработчик нажатия на элемент gridView
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
        if(type.equals("listBus")){
            //создаем  intent для перехода на активити с остановками данного маршрута
            Intent intent = new Intent(getActivity(), List_bus_stop.class);
            String numberBus = simpleAdapter.getItem(position).toString();
            numberBus = numberBus.substring(32, numberBus.length() - 1);
            numberBus = numberBus.replaceAll(" ", "");

            Context context = getActivity();
            DbOpenHelper dbOpenHelper = new DbOpenHelper(context);
            SQLiteDatabase db;
            db = dbOpenHelper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.clear();
            String st = "";
            st = "SELECT bus_path_table.id FROM bus_path_table ";
            st = st + " WHERE bus_path_table.Name='" + numberBus + "'";
            st = st + " AND bus_path_table.Type_transport_id='" + typeTransport + "'";
            Cursor c = db.rawQuery(st, null);
            String busPathId = null;
            while (c.moveToNext()) {
                busPathId = c.getString(0);
            }
            intent.putExtra("busPathId", busPathId);
            //запускаем активити с остановками данного маршрута
            startActivity(intent);
        }
        else{
            //Здесь надо допилить карту с маршрутом
            //создаем  intent для перехода на активити с остановками данного маршрута
            Intent intent = new Intent(getActivity(), Scheme.class);
            String numberBus = simpleAdapter.getItem(position).toString();
            numberBus = numberBus.substring(32, numberBus.length() - 1);
            numberBus = numberBus.replaceAll(" ", "");
            intent.putExtra("numberBus", numberBus);
            intent.putExtra("typeTransport", typeTransport);
            startActivity(intent);
        }
    }
}


