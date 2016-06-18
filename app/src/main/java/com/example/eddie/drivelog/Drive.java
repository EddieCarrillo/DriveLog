package com.example.eddie.drivelog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Created by eddie on 8/2/2015.
 * Class that holds all Data pertaining to a Drive Object
 */
public class Drive implements Serializable {




public Drive(){
    mDateTraveled = new Date();
    mId = UUID.randomUUID();
}

    public void setStartingLocation(String startLocation){
        mStartingLocation = startLocation;
    }
    public void setMilesTraveled(int milesTraveled){
        mMilesTraveled = milesTraveled;
    }
    public void setEndLocation(String endLocation){
        mEndLocation = endLocation;
    }
    public void setDateTraveled(Date date){
        mDateTraveled = date;
    }
    public void setOdometerReading(int odometer){
        mOdometer = odometer;
    }

    public int getMilesTraveled(){

        return mMilesTraveled;
    }
    public String getStartingLocation(){
        return mStartingLocation;
    }
    public String getEndLocation(){
        return mEndLocation;
    }
    public Date getDateTraveled(){
        return mDateTraveled;
    }
    public UUID getId(){
        return mId;
    }
    public int getOdometer(){
        return mOdometer;
    }
    public String toString(){

        return "To: "+mEndLocation + " Miles: " + mMilesTraveled + " DateTraveled " + mDateTraveled  ;
    }




    /*Private Instance Variables*/
    private String mStartingLocation;
    private String mEndLocation;
    private int mMilesTraveled;
    private Date mDateTraveled;
    private UUID mId;
    private int mOdometer;
}
