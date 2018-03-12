package com.example.trainingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by anton on 3/5/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Trainings.db";
    public static final String TABLE_NAME = "trainings_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Title";
    public static final String COL_3 = "Description";
    public static final String COL_4 = "Kilometers";
    public static final String COL_5 = "Denivelation";
    //public static final String COL_6 = "Date";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, Title TEXT, Description TEXT, Kilometers INTEGER, Denivelation INTEGER "); //Date TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean InsertData(String title, String description, String kilometers, String Denivelation) //String Date)
    {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, title);
        contentValues.put(COL_3, description);
        contentValues.put(COL_4, kilometers);
        contentValues.put(COL_5, Denivelation);
        //contentValues.put(COL_6, Date);
        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public boolean updateData(String id, String title, String description, String kilometers, String Denivelation /*String Date*/ ){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, title);
        contentValues.put(COL_3, description);
        contentValues.put(COL_4, kilometers);
        contentValues.put(COL_5, Denivelation);
        //contentValues.put(COL_6, Date);
        sqLiteDatabase.update(TABLE_NAME, contentValues, "ID = ?", new String[] { id} );
        return  true;
    }

    public Integer deleteData (String id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME, "ID = ?", new String[] {id});
    }
}
