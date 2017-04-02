package com.example.mustafa.bikefuel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Mustafa on 25/03/2017.
 */

class FillUp {
    private int odometerReading;
    private double litersFilled;
    private double cost;
    private Date fillDate;

    //SimpleDateFormat myDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

/*    //A 2 parameter constructor in case price isn't recorded
    public FillUp(int newOdo, double newLiter) {
        this.odometerReading = newOdo;
        this.litersFilled = newLiter;
        this.fillDate = new Date();
    }*/

    //Full parameter constructor
    public FillUp(int odometerReading, double litersFilled, double cost) {
        this.odometerReading = odometerReading;
        this.litersFilled = litersFilled;
        this.cost = cost;
        this.fillDate = new Date();
    }

    public double getLitersFilled() {
        return litersFilled;
    }

    public double getCost() {
        return cost;
    }

    public int getOdometerReading() {
        return odometerReading;
    }
}
