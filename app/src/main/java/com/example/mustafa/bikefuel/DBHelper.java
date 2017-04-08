package com.example.mustafa.bikefuel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

//Created by Mustafa Mansour on 8/04/2017.

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "FillUP.db";

    //I was forced to make this constructor
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //SQL statement to create the table
    private final static String SQL_CREATE_TABLE =
            "CREATE TABLE " + Contract.FillUp.TABLE_NAME + " ( " +
                    Contract.FillUp.ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    Contract.FillUp.COLUMN_NAME_ODOMETER + " INTEGER, " +
                    Contract.FillUp.COLUMN_NAME_LITRE + " NUMERIC, " +
                    Contract.FillUp.COLUMN_NAME_COST + " NUMERIC );";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addFillUp (FillUp fillUp){
        ContentValues contentValues = new ContentValues();

        contentValues.put(Contract.FillUp.COLUMN_NAME_ODOMETER, fillUp.getOdometerReading());
        contentValues.put(Contract.FillUp.COLUMN_NAME_COST, fillUp.getCost());
        contentValues.put(Contract.FillUp.COLUMN_NAME_LITRE, fillUp.getLitersFilled());

        //Gets the data repository in write mode, inserts the values and closes the repository
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(Contract.FillUp.TABLE_NAME, null, contentValues);
        db.close();

    }

    public Cursor getAllFillUps(){
        SQLiteDatabase db = this.getReadableDatabase();
        Log.i("GET ALL FILLUPS","getAllFillUps");

        Cursor cursor = db.rawQuery("select * from " + Contract.FillUp.TABLE_NAME, null);

        return cursor;
    }
}
