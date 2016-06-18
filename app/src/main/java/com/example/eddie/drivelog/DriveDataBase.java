package com.example.eddie.drivelog;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by eddie on 8/3/2015.
 */
public class DriveDataBase {
    private static DriveDataBase sDriveDataBase;
    private Context mAppContext;

    private DriveDataBase(Context appContext){
        mAppContext = appContext;
        mDrives = new ArrayList<Drive>();
    }
    public static DriveDataBase get (Context c){
        if (sDriveDataBase == null){
            sDriveDataBase = new DriveDataBase(c.getApplicationContext());
        }
        return sDriveDataBase;
    }
    public Drive getDrive(UUID id){
        for (Drive drive: mDrives) {
            int i = 0;
            System.out.println("# "+i + " "+ id.toString());
            System.out.println("current Id:"+ id.toString());
            i++;
            if (drive.getId().equals(id));
            return drive;

        }

        return null;
    }
    public ArrayList<Drive> getDrives(){
        return mDrives;
    }
    public int getOdometerReading (){
        return mOdometerReading;
    }
    public void setOdometerReading(int odometerReading){
        mOdometerReading = odometerReading;
    }
    public void addDriveToStorage(Drive drive){
        mDrives.add(drive);
        Log.d("Eddie",Integer.toString(mDrives.size()));
    }
    public void removeDrive(Drive drive){
        mDrives.remove(drive);
    }

    /*Private Instance Variables*/
    private ArrayList<Drive> mDrives;
    private int mOdometerReading;
}
