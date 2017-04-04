package com.example.mustafa.bikefuel;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import static com.example.mustafa.bikefuel.MainActivity.fillUps;

public class OverallStats extends Fragment {

    TextView averageDisplay;
    TextView totalLiterDisplay;
    TextView totalCostDisplay;

    final DecimalFormat TWODECIMALPOINTS = new DecimalFormat("0.##");



    public OverallStats() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_overall_stats, container, false);

        //Initialising the TextViews
        averageDisplay = (TextView) view.findViewById(R.id.avg_fuel_eco_TV);
        totalLiterDisplay = (TextView) view.findViewById(R.id.total_fuel_used_TV);
        totalCostDisplay = (TextView) view.findViewById(R.id.total_cost_TV);

        //The add fill up button
        //Activity hostingActivity = this.getActivity();
        FloatingActionButton addFillUpFab = (FloatingActionButton) view.findViewById(R.id.fab);

        //This opens a new activity to add a new entry
        addFillUpFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openAddPage = new Intent(v.getContext(), NewEntry.class);
                startActivity(openAddPage);
            }
        });

        displayAvgConsumption();
        displayTotalDollars();
        displayTotalLiters();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        displayAvgConsumption();
        displayTotalDollars();
        displayTotalLiters();
    }

    //This method gets the total liters of fuel BOUGHT and displays it
    public void displayTotalLiters(){
        totalLiterDisplay.setText(getText(R.string.total_fuel_display) + " " + sumFuel() + " L");
    }

    //This method calculates how much money was spent on fuel and displays it
    public void displayTotalDollars(){
        Double retVal = 0.0;
        for (FillUp i:fillUps){
            retVal += i.getCost();
        }
        totalCostDisplay.setText(getText(R.string.total_dollar_display) + " $ " + retVal.toString());
    }

    //This method calculated and displays the average fuel efficiency
    public void displayAvgConsumption() {

        if (fillUps.size() > 1) {
            int distanceRode = fillUps.get(fillUps.size() - 1).getOdometerReading()
                    - fillUps.get(0).getOdometerReading();

            double fuelUsed = sumFuel() - fillUps.get(fillUps.size() - 1).getLitersFilled();

            fuelUsed *= 100.0/ distanceRode ;

            averageDisplay.setText(getText(R.string.avg_fuel_display) + " " +
                    TWODECIMALPOINTS.format(fuelUsed) + " L/100km");
        }
        else {
            averageDisplay.setText(getText(R.string.avg_fuel_display) + " 0.00 L/100km");
        }

    }

    //This method calculates the total fuel BOUGHT
    public double sumFuel(){
        Double retVal = 0.0;
        for (FillUp i:fillUps){
            retVal += i.getLitersFilled();
        }
        return retVal;
    }

}
