package com.example.eddie.drivelog;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;


/**
 * A placeholder fragment containing a simple view.
 */
public class ManualDriveFragment extends Fragment {

     /*Constants*/
    private static final int REQUEST_DATE = 0;
    public static final String KEY_ODOMETER_UPDATE ="com.example.eddie.drivelog.odometer_update";




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_drive, container, false);
        mDrive = new Drive();
        mStartLabel = (TextView)v.findViewById(R.id.starting_point_label);
        mEndLabel = (TextView)v.findViewById(R.id.end_location_label);
        mStartText = (EditText)v.findViewById(R.id.start_location_text);
        mEndText = (EditText)v.findViewById(R.id.end_location_text);
        mDateTraveledButton = (Button)v.findViewById(R.id.date_button);
        mOdometerText = (EditText)v.findViewById(R.id.odometer_edit);
        mOdometerText.setText(Integer.toString(DriveDataBase.get(getActivity()).getOdometerReading()));
        mDistanceText = (EditText)v.findViewById(R.id.distance_edit_text);
        mLiveButton = (Button)v.findViewById(R.id.live_drive_button_frag);
        updateDate();
        mDateTraveledButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Uses the fragment manager to search for fragments
                FragmentManager fm = getActivity()
                        .getSupportFragmentManager();
                //Creates a new alert dialog fragment(DatePicker) and creates a new Instance
                //using the date of the current Drive object
                DatePickerFragment dialog = DatePickerFragment.newInstance(mDrive.getDateTraveled());
                //Shows the date alert fragment by using the fm(fragment manager object) and uses a
                //tag to identify it
                dialog.show(fm, "tag");
                //Makes sure that the dialog is set to open on this Drive Fragment and also sends a tag
                dialog.setTargetFragment(ManualDriveFragment.this, REQUEST_DATE);
            }
        });


        mAddButton = (Button)v.findViewById(R.id.add_button);
        mAddButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //extracting data from from editTexts and storing the data into current drive object
                if (mStartText.getText() != null) {
                    getStartingPointInfo();
                }

                if (mEndText.getText() != null) {
                    getDestinationInfo();
                }
                if (mDistanceText.getText() != null) {
                    getDistanceInfo();
                }
                if (mOdometerText.getText() != null) {
                    getOdometerInfo();
                }
                //ItemsMenuFragment
                sendDataToMainFrag();
            }
        });
       /* mLiveButton.setOnClickListener( new View.OnClickListener(){
            @Override
        public void onClick(View v){
                Intent i = new Intent(getActivity(),LiveDriveActivity.class);
                startActivity(i);
            }
        });*/

        return v;
    }

    private void getOdometerInfo(){
        try {
            mDrive.setOdometerReading(Integer.parseInt(mOdometerText.getText().toString()));
        } catch (NumberFormatException ex) {
            Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    private void getDistanceInfo(){
        try {
            mDrive.setMilesTraveled(Integer.parseInt(mDistanceText.getText().toString()));
        } catch (NumberFormatException ex) {
            Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    private void getDestinationInfo(){
        mDrive.setEndLocation(mEndText.getText().toString());
    }
    private void getStartingPointInfo(){
        mDrive.setStartingLocation(mStartText.getText().toString());
    }
    private void sendDataToMainFrag(){
        //Access the Drive database and add the current Drive into the database
        DriveDataBase.get(getActivity())
                .addDriveToStorage(mDrive);
        //Create a new intent so that I can send miles of the previous drive to update odometer of the Crime to the DriveViewFragment
        Intent i = new Intent(getActivity(),ItemsMenuActivity.class);
         incrementOdometer();
        startActivity(i);
    }
    private void incrementOdometer(){
        int oldOdometer = DriveDataBase.get(getActivity()).getOdometerReading();
        int milesAdded = mDrive.getMilesTraveled();
        DriveDataBase.get(getActivity()).setOdometerReading(milesAdded+oldOdometer);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode!= Activity.RESULT_OK) return;
//Grabs the result from the AlertDialog and taking the Extra (date) and using the information
        //to set the date of the Drive
        if (requestCode == REQUEST_DATE){
              Date date =  (Date)data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mDrive.setDateTraveled(date);
            updateDate();
        }

    }

/*Places the date of drive the onto the button*/
    private void updateDate(){
        mDateTraveledButton.setText(mDrive.getDateTraveled().toString());
    }

    /*Private Instance Variables*/
    private TextView mStartLabel;
    private TextView mEndLabel;
    private EditText mStartText;
    private EditText mEndText;
    private EditText mOdometerText;
    private EditText mDistanceText;
    private Button mDateTraveledButton;
    private Button mAddButton;
    private Button mLiveButton;
     Drive mDrive;





}
