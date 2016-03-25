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

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import bus_vn.gena.bus_vn.com.bus_vn.R;
/**
 * Created by gena on 10.10.2015.
 *
 *
 */
public class  ListviewArrayAdapter extends ArrayAdapter<String>{
    private final Context context;
    private final String[] values;
    private final Integer size;

    public  ListviewArrayAdapter(Context context, String[] values, Integer size) {
        super(context, R.layout.listview_item, values);
        this.context = context;
        this.values = values;
        this.size=size;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.listview_item, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        textView.setText(values[position]);
        String s = values[position];
        //меняем изображение для первой остановки
        if (position==0){
            imageView.setImageResource(R.drawable.top_list_bus2);
        }
        else{
            imageView.setImageResource(R.drawable.list_bus);
        }
        //меняем изображение для последней остановки
        if (position==size){
            imageView.setImageResource(R.drawable.bottom_list_bus2);
        }
        return rowView;
    }
}