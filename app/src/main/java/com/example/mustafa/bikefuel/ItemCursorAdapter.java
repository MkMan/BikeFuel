package com.example.mustafa.bikefuel;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static com.example.mustafa.bikefuel.MainActivity.TWODECIMALPOINTS;


//Created by Mustafa on 8/04/2017.

public class ItemCursorAdapter extends CursorAdapter {

    private DBHelper dbHelper;
    private Context myContext;
    private FragmentManager myFragmentManager;

    public ItemCursorAdapter(Context context, Cursor c, FragmentManager fMgr) {
        super(context, c, 0);
        myContext = context;
        myFragmentManager = fMgr;
        dbHelper = new DBHelper(myContext);

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_fillup_template, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView odo_TV = (TextView) view.findViewById(R.id.item_odo_TV);
        TextView litre_TV = (TextView) view.findViewById(R.id.item_litre_TV);
        TextView dollar_TV = (TextView) view.findViewById(R.id.item_dollar_TV);

        final int odo = cursor.getInt(cursor.getColumnIndexOrThrow(Contract.FillUp.COLUMN_NAME_ODOMETER));
        final double litre = cursor.getDouble(cursor.getColumnIndexOrThrow(Contract.FillUp.COLUMN_NAME_LITRE));
        final double cost = cursor.getDouble(cursor.getColumnIndexOrThrow(Contract.FillUp.COLUMN_NAME_COST));

        odo_TV.setText(Integer.toString(odo));
        litre_TV.setText(TWODECIMALPOINTS.format(litre));
        dollar_TV.setText(TWODECIMALPOINTS.format(cost));


    }
}
