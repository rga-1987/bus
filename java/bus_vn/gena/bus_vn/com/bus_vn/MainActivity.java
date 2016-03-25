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
package bus_vn.gena.bus_vn.com.bus_vn;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    CollapsingToolbarLayout collapsingToolbarLayout;
    ImageView image;
    CardView busList;
    CardView enterpriseBus;
    CardView help;
    CardView scheme;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //находим кнопку выход
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        //устанавливаем обработчик
        fab.setOnClickListener(this);

        busList=(CardView)findViewById(R.id.bus_list);
        busList.setOnClickListener(this);

        help=(CardView)findViewById(R.id.help);
        help.setOnClickListener(this);

        enterpriseBus=(CardView)findViewById(R.id.enterprise_bus);
        enterpriseBus.setOnClickListener(this);

        scheme=(CardView)findViewById(R.id.scheme);
        scheme.setOnClickListener(this);

        //находим изобажение для заголовка и устанавливаем его
        image = (ImageView) findViewById(R.id.image);
        image.setImageResource(R.drawable.pic);

        //устанавливаем нужный нам заголовок и надпись
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Расписание движения");
        DbOpenHelper dbOpenHelper = new DbOpenHelper(this);
        SQLiteDatabase db ;
        db = dbOpenHelper.getWritableDatabase();
        dbOpenHelper.proverka(db);
    }
    //Обработчик для всех действий
    @Override
    public void onClick(View v){
        switch (v.getId()){
            //обработка нажатия кнопки "Выход"
            case R.id.fab:
                try{
                    finish();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                break;
            //обработка нажатия кнопки "Выход"
            case R.id.help:
                try{
                    // Создаем объект Intent для вызова новой Activity
                    Intent intent = new Intent(this, Help.class);
                    // запуск activity
                    startActivity(intent);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                break;
            //обработка нажатия кнопки "Выход"
            case R.id.enterprise_bus:
                try{
                    // Создаем объект Intent для вызова новой Activity
                    Intent intent = new Intent(this, Enterprise_bus.class);
                    // запуск activity
                    startActivity(intent);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                break;
            //обработка нажатия кнопки расписание движения
            case R.id.bus_list:
                try{
                    // Создаем объект Intent для вызова новой Activity
                    Intent intent = new Intent(this, List_bus.class);
                    intent.putExtra("type","listBus");
                    // запуск activity
                    startActivity(intent);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                break;
            //обработка нажатия кнопки расписание движения
            case R.id.scheme:
                try{
                    // Создаем объект Intent для вызова новой Activity
                    Intent intent = new Intent(this, List_bus.class);
                    intent.putExtra("type","scheme");
                    // запуск activity
                    startActivity(intent);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}