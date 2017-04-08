package com.example.mustafa.bikefuel;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


//Created by Mustafa on 8/04/2017.

public class HistoryFragment extends Fragment {
    DBHelper dbHelper;
    LayoutInflater myInflator = null;
    ListView fillUpList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DBHelper(getContext());

    }


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        myInflator = inflater;
        View v = inflater.inflate(R.layout.fragment_history, container, false);

        fillUpList = (ListView) v.findViewById(R.id.history_list_LV);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadListFillUps();
    }

    public void loadListFillUps(){
        Cursor c = dbHelper.getAllFillUps ();
        //fillUpList = (ListView) getActivity().findViewById(R.id.history_list_LV);
        ItemCursorAdapter itemAdapter = new ItemCursorAdapter(getContext(), c ,getActivity().getSupportFragmentManager());
        fillUpList.setAdapter(itemAdapter);
    }
}