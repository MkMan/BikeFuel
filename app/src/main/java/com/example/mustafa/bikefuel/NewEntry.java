package com.example.mustafa.bikefuel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.FileWriter;

import static com.example.mustafa.bikefuel.MainActivity.fillUps;
import static com.example.mustafa.bikefuel.MainActivity.myFileWritter;

public class NewEntry extends AppCompatActivity {

    private EditText odoReading;
    private EditText fuelAdded;
    private EditText dollarSpent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);

        odoReading = (EditText) findViewById(R.id.odo_ET);
        fuelAdded = (EditText) findViewById(R.id.fuel_ET);
        dollarSpent = (EditText) findViewById(R.id.cost_ET);

    }

    public void addFillUp (View view){
        FillUp newFillUp = new FillUp(Integer.parseInt(odoReading.getText().toString()),
                Double.parseDouble(fuelAdded.getText().toString()),
                Double.parseDouble(dollarSpent.getText().toString()));

        fillUps.add(newFillUp);
        finish();

        try {
            FileWriter myFileWritter = new FileWriter("Z300");
            myFileWritter.append("1");
        } catch (Exception e) {

        }



    }

    public void cancel (View view){
        finish();
    }
}
