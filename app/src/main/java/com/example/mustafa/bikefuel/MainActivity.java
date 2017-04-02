package com.example.mustafa.bikefuel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //This arrayList holds the fillups, will be eventually replaced by either DB or file
    static ArrayList<FillUp> fillUps = new ArrayList<>();
    //An attempt at using a file to save data
    static FileWriter myFileWritter;

    TextView averageDisplay;
    TextView totalLiterDisplay;
    TextView totalCostDisplay;

    final DecimalFormat TWODECIMALPOINTS = new DecimalFormat("0.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            FileReader myFileReader = new FileReader("Z300");

        }
        catch (Exception e){

        }



        //Initialising the TextViews
        averageDisplay = (TextView) findViewById(R.id.avg_fuel_eco_TV);
        totalLiterDisplay = (TextView) findViewById(R.id.total_fuel_used_TV);
        totalCostDisplay = (TextView) findViewById(R.id.total_cost_TV);

        //The add fill up button
        FloatingActionButton addFillUpFab = (FloatingActionButton) findViewById(R.id.fab);

        //The Navigation drawer mess
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //This opens a new activity to add a new entry
        addFillUpFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openAddPage = new Intent(v.getContext(), NewEntry.class);
                startActivity(openAddPage);
            }
        });

/*
        //Display the overall stats
        displayTotalLiters();
        displayTotalDollars();
        displayAvgConsumption();
*/

    }

/*    @Override
    protected void onResume() {
        super.onResume();
        displayTotalLiters();
        displayTotalDollars();
        displayAvgConsumption();
    }*/

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        /*if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
