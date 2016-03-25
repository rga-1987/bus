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

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Working with Database
 * Created by Gena on 08.08.2015.
 * @author Gena
 * @version 1.0
 *
 */
public class DbOpenHelper extends SQLiteOpenHelper{

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "bus_vn";
    private static final String DB_PATH = "/data/data/bus_vn.gena.bus_vn.com.bus_vn/databases/";

    Context myContext;
    public static final String BUS_PATH_TABLE = "bus_path_table"; //Таблица маршрутов
    public static final String BUS_STOP_TABLE = "bus_stop_table"; //Таблица остановок
    public static final String BUS_TIME_TABLE = "bus_time_table"; //Таблица времени остановок
    public static final String TYPE_DAY_TABLE = "type_day_table"; //Таблица с типами дня (рабочие дни, выходные)
    public static final String TYPE_TRANSPORT_TABLE = "type_transport_table"; //Таблица с типами дня (рабочие дни, выходные)
    public static final String BUS_STOP_PATH_TABLE = "bus_stop_path_table"; //

    public static final String NAME = "Name";
    public static final String TIME = "Time";
    public static final String TYPE_TIME ="Type_time";

    public static final String BUS_PATH_ID = "Bus_path_id";
    public static final String BUS_STOP_ID = "Bus_stop_id";
    public static final String BUS_TIME_ID = "Bus_time_id";
    public static final String TYPE_DAY_ID = "Type_day_id";
    public static final String TYPE_TRANSPORT_ID = "Type_transport_id";
    public static final String BUS_PATH_STOP_ID ="Bus_path_stop_id";


    private static final String TYPE_TRANSPORT_CREATE_TABLE = "create table " + TYPE_TRANSPORT_TABLE  + " ( id integer primary key autoincrement, " + NAME + " TEXT)";
    private static final String BUS_PATH_CREATE_TABLE = "create table " + BUS_PATH_TABLE  + " ( id integer primary key autoincrement, " + NAME + " TEXT, " + TYPE_TRANSPORT_ID + " integer)";
    private static final String BUS_STOP_CREATE_TABLE = "create table " + BUS_STOP_TABLE  + " ( id integer primary key autoincrement, " + NAME + " TEXT)";
    private static final String TYPE_DAY_CREATE_TABLE = "create table " + TYPE_DAY_TABLE  + " ( id integer primary key autoincrement, " + NAME + " TEXT)";
    private static final String BUS_STOP_PATH_CREATE_TABLE = "create table " + BUS_STOP_PATH_TABLE  + " ( id integer primary key autoincrement, " +  BUS_PATH_ID + " integer, " +  BUS_STOP_ID + " integer, " +  TYPE_DAY_ID + " integer)";
    private static final String BUS_TIME_CREATE_TABLE = "create table " + BUS_TIME_TABLE  + " ( id integer primary key autoincrement, " + TIME + " TEXT, " +  BUS_PATH_STOP_ID + " integer, " + TYPE_DAY_ID + " integer)";

    public DbOpenHelper(Context context) {
        super(context, DB_NAME, null,DB_VERSION);
        myContext = context;
    }
    /**
     * Check database exists
     * @param sqLiteDatabase SQLiteDatabase
     * @throws IOException If there is any I/O problem
     */
    public void proverka(SQLiteDatabase sqLiteDatabase) {
        if(isTableExists(sqLiteDatabase, "bus_path_table")) {
        }
        else{
            try{
                copyDataBase();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){}

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){}

    public void Create_BD(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL(BUS_PATH_CREATE_TABLE);
        sqLiteDatabase.execSQL(BUS_STOP_CREATE_TABLE);
        sqLiteDatabase.execSQL(BUS_TIME_CREATE_TABLE);
        sqLiteDatabase.execSQL(TYPE_DAY_CREATE_TABLE);
        sqLiteDatabase.execSQL(TYPE_TRANSPORT_CREATE_TABLE);
        sqLiteDatabase.execSQL(BUS_STOP_PATH_CREATE_TABLE);
    }
    /**
     * Check database exists
     * @return false if database no exits, if database exist return true
     * @param tableName String
     * @param db SQLiteDatabase
     */
    boolean isTableExists(SQLiteDatabase db, String tableName) {
        if (tableName == null || db == null || !db.isOpen()) {
            return false;
        }
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM sqlite_master WHERE type = ? AND name = ?", new String[] {"table", tableName});
        if (!cursor.moveToFirst()) {
            return false;
        }
        int count = cursor.getInt(0);
        cursor.close();
        return count > 0;
    }

    /**
     * Copy database from assets
     * @throws IOException If there is any I/O problem
     */
    public void copyDataBase() throws IOException {
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

}

