package com.example.eddie.drivelog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.UUID;


public class  DriveViewFragment extends DialogFragment {
    /*Constants*/
    public static final String KEY_DRIVE_ID="com.example.eddie.drivelog";
@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    // Inflate the layout for this fragment

    View v = getActivity().getLayoutInflater().inflate(R.layout.fragment_drive_view, null);
    mDrive = getDrive();
    String date = formatDate();



    mOdometer = (TextView) v.findViewById(R.id.odometer_reading_text);
     mOdometer.setText("Odometer: " + mDrive.getOdometer());
    mMiles = (TextView) v.findViewById(R.id.miles_traveled_text);
    mMiles.setText("Miles: " + mDrive.getMilesTraveled());
    mStart = (TextView) v.findViewById(R.id.starting_point_text);
    mStart.setText("From:" + mDrive.getStartingLocation());
    mEnd = (TextView) v.findViewById(R.id.end_point_text);
   mEnd.setText("To: " + mDrive.getEndLocation());
    mDate = (TextView) v.findViewById(R.id.date_text);
   mDate.setText("Date: " + date);


    return new AlertDialog.Builder(getActivity())
            .setView(v)
            .setTitle(R.string.driveview_title)
            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener()
            {
                @Override
                //when dateButton is pressed a result is sent saying data transfer was successful
                public void onClick(DialogInterface dialog, int which) {
                    sendResult(Activity.RESULT_OK);

                }
            })
            .setNegativeButton(R.string.negative_delete_button, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    sendResult(DriveListFragment.RESULT_DELETE_ITEM);
                }
            }).create();
}


    public static DriveViewFragment newInstance(Drive drive){
        Bundle args = new Bundle();
        args.putSerializable(KEY_DRIVE_ID,drive);
        Log.d(DriveListFragment.TAG,drive.toString());
        DriveViewFragment fragment = new DriveViewFragment();
        fragment.setArguments(args);

        return fragment;
    }
    private Drive getDrive(){
        Drive drive = (Drive)getArguments().getSerializable(KEY_DRIVE_ID);
        return drive;
    }

    /**
     * @return returns a formatted date into month/day/year format string
     */
    private String formatDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
       return formatter.format(mDrive.getDateTraveled()).toString();

    }
    private void sendResult(int resultCode){
        if (getTargetFragment() == null){
            return;
        }
        Intent i = new Intent();
        i.putExtra(KEY_DRIVE_ID,mDrive);
        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,i);


    }

    /*Private Instance Variables*/
  private  TextView mOdometer;
    private TextView mMiles;
    private TextView mStart;
    private TextView mEnd;
    private TextView mDate;
    private Drive mDrive;



}
