package com.example.eddie.drivelog;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/*This fragment is responsible for displaying the users current odometer reading and allowing them
* to create a new drive activity and also letting them to choose to view past drive activities*/
public class ItemsMenuFragment extends Fragment {

    /*Constants*/
    public static final String DIALOG_ODOMETER = "Odometer Reading";
    public static final int REQUEST_ODOMETER = 0;
    public static final int REQUEST_MILES = 1;
    public static final String KEY_ADD_ODOMETER = "com.example.eddie.drivelog.add_odometer";
    public static final String KEY_UPDATE_ODOMETER = "com.example.eddie.drivelog.update_odometer";
    private boolean mOdometerUpdated = false;
    public static final String DEBUG_TAG = "Eddie";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_items_menu, container, false);
        mNewDriveButton = (Button) v.findViewById(R.id.log_button);
        mNewDriveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), DriveActivity.class);
                startActivity(i);
            }
        });
        mOdometerButton = (Button) v.findViewById(R.id.odometer_button);
        updateOdometer();
        mOdometerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                dialog = new OdometerAlert();
                dialog.show(fm, DIALOG_ODOMETER);
                mPlayer = new AudioPlayer();
                Log.d("Eddie","mPlayer");
                mPlayer.play(getActivity());
                dialog.setTargetFragment(ItemsMenuFragment.this, REQUEST_ODOMETER);

            }
        });

        mViewLogButton = (Button) v.findViewById(R.id.view_log_button);
        mViewLogButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), CalendarActivity.class);
                startActivity(i);
            }
        });
        return v;
    }





    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK)
            return;
        if (requestCode == REQUEST_ODOMETER) {
            DriveDataBase.get(getActivity()).setOdometerReading(data.getIntExtra(OdometerAlert.KEY_UPDATE_ODOMETER, 0));
            Log.d(DEBUG_TAG, "onActivityResult was reached");
           updateOdometer();
        }
        if (requestCode == REQUEST_MILES) {
            Log.d(DEBUG_TAG, "success");
            DriveDataBase.get(getActivity()).setOdometerReading(data.getIntExtra(ManualDriveFragment.KEY_ODOMETER_UPDATE, 0));
            updateOdometer();
        }


    }
    private void updateOdometer() {
        mOdometerButton.setText("Odometer:\n" + Integer.toString(DriveDataBase.get(getActivity()).getOdometerReading()));
    }




        //To be implemented later...
   /* private void makeToast(){
        boolean isDriveAdded = getActivity().getIntent().getBooleanExtra(ManualDriveFragment.TAG_DRIVE_ADDED,false);

        if (mIsNewDriveAdded == false) return;
        else{
            Toast.makeText(getActivity(),R.string.drive_added_toast,Toast.LENGTH_SHORT).show();
            mIsNewDriveAdded = false;



    }*/
    @Override
    public void onResume(){
        super.onResume();
        updateOdometer();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mPlayer.stop();
    }


/*Private Instance Varaibles*/
    private Button mOdometerButton;
    private TextView mOdometerTop;
    private AudioPlayer mPlayer;
    private OdometerAlert dialog;
    private Button mNewDriveButton;
    private Button mViewLogButton;
    private boolean mIsNewDriveAdded;
    private DialogInterface.OnDismissListener onDismissListener;



}
