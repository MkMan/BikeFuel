package com.example.mustafa.bikefuel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class NewEntry extends AppCompatActivity {

    private EditText odoReading;
    private EditText fuelAdded;
    private EditText dollarSpent;
    private Button confirmButton;

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);

        odoReading = (EditText) findViewById(R.id.odo_ET);
        fuelAdded = (EditText) findViewById(R.id.fuel_ET);
        dollarSpent = (EditText) findViewById(R.id.cost_ET);

        confirmButton = (Button) findViewById(R.id.confirm_button);

        dbHelper = new DBHelper(this);

        confirmButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                addFillUp(v);
            }
        });

    }



    public void addFillUp (View view){
        FillUp newFillUp = new FillUp(Integer.parseInt(odoReading.getText().toString()),
                Double.parseDouble(fuelAdded.getText().toString()),
                Double.parseDouble(dollarSpent.getText().toString()));

        dbHelper.addFillUp(newFillUp);
        finish();
    }

    public void cancel (View view){
        finish();
    }
}
