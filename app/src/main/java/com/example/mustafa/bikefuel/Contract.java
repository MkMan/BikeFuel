package com.example.mustafa.bikefuel;

import android.provider.BaseColumns;


//Created by Mustafa Mansour on 8/04/2017.


final class Contract {

    //Private empty constructor to appease Android Studio
    private Contract(){}

    static class FillUp implements BaseColumns {
        static final String TABLE_NAME = "Z300";

        static final String ID = "_id";
        //public static final String COLUMN_NAME_DATE = "Date";
        static final String COLUMN_NAME_ODOMETER = "Odometer";
        static final String COLUMN_NAME_LITRE = "Litres";
        static final String COLUMN_NAME_COST = "Price";
    }
}
