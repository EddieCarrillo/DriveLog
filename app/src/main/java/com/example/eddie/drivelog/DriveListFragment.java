package com.example.eddie.drivelog;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;


public class DriveListFragment extends ListFragment {


    /*Constantsw*/
    public static final String TAG = "Eddie";
    public static final String DRIVEVIEW_FRAG = "com.example.eddie.drivelog.driveview_frag";
    public static final int RESULT_DELETE_ITEM = -100;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View v = inflater.inflate(R.layout.fragment_drive_list, container, false);
        //mDrives holds all drives
        mDrives = DriveDataBase.get(getActivity()).getDrives();
         mDateList = currDateList();



        //Uses an ArrayAdapter with a temporary ArrayList that holds carry the same date that was pressed on the Calendar
         adapter = new ArrayAdapter<Drive>(getActivity(),android.R.layout.simple_list_item_1,mDateList);
       setListAdapter(adapter);

        return v;
    }
    @Override
            public void onListItemClick(ListView l, View v, int position, long id){
        Drive drive = (Drive)getListAdapter().getItem(position);
        Log.d(TAG +"current",drive.toString());
       FragmentManager fm = getActivity().getSupportFragmentManager();
       DriveViewFragment mDialog = DriveViewFragment.newInstance(drive);
        mDialog.setTargetFragment(DriveListFragment.this, RESULT_DELETE_ITEM);

        mDialog.show(fm, DRIVEVIEW_FRAG);


    }

    private ArrayList<Drive> currDateList(){
        ArrayList<Drive> tempList = new ArrayList<Drive>();
       Date calendarDate = new Date(getActivity().getIntent().getLongExtra(CalendarFragment.EXTRA_DATE,0));
        Log.d(TAG, "calendarDate" + calendarDate);

        for (Drive drive : mDrives){
       if ((compareCurrDate(drive, calendarDate) == true)) {
                //  && ((removeDrive==null) || (removeDrive.equals(drive.getId())) == false))
                tempList.add(drive);
            }

        }
        return tempList;
    }

    private boolean compareCurrDate(Drive currDrive, Date calendarDate){
        Calendar driveCalendar = Calendar.getInstance();
        driveCalendar.setTime(currDrive.getDateTraveled());
        Calendar datePressed = Calendar.getInstance();
        datePressed.setTime(calendarDate);

        if (driveCalendar.get(Calendar.YEAR) == datePressed.get(Calendar.YEAR)){
            if (driveCalendar.get(Calendar.MONTH) == datePressed.get(Calendar.MONTH)){
                if (driveCalendar.get(Calendar.DAY_OF_MONTH) == datePressed.get(Calendar.DAY_OF_MONTH)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void onActivityResult(int requestCode,int resultCode, Intent data){
        if (requestCode == Activity.RESULT_OK) return;

         if (resultCode == RESULT_DELETE_ITEM ){
           Drive deleteDrive =  (Drive)data.getSerializableExtra(DriveViewFragment.KEY_DRIVE_ID);
            removeDrive(deleteDrive);
         }
    }

    private void removeDrive(Drive drive){

        int currentOdometerReading = DriveDataBase.get(getActivity()).getOdometerReading();
        //Update the current odometer reading
        DriveDataBase.get(getActivity())
                .setOdometerReading(currentOdometerReading-drive.getMilesTraveled());
        DriveDataBase.get(getActivity()).removeDrive(drive);
        //remove the item from ArrayList with Drives
        mDateList.remove(drive);
        //Update the adapter
        adapter.remove(drive);
        adapter.setNotifyOnChange(true);

        Log.d("Eddie", "RemoveDrive was called");

    }

    /*Private Instance Variables*/
    private ArrayList<Drive> mDrives;
   private ArrayList<Drive> mDateList;
  private  ArrayAdapter <Drive> adapter;

}
