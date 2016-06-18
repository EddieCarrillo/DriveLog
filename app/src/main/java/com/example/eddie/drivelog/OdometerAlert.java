package com.example.eddie.drivelog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



/**
 * A placeholder fragment containing a simple view.
 */
public class OdometerAlert extends DialogFragment {

    /*Constants*/
    //Acts as a key for access of activiy result passsed
public static final String KEY_UPDATE_ODOMETER = "com.example.eddie.drivelog.update_odometer";


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context mContext = getActivity();
        final Dialog dialog = new Dialog(mContext);
        View v = getActivity().getLayoutInflater().inflate(R.layout.fragment_alert_text_box, null);

        mOdometerReading = (EditText) v.findViewById(R.id.alert_editText);

        //Inflates View and allows for user to send result of menu
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.odometer_label)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendResult(Activity.RESULT_OK);

                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendResult(Activity.RESULT_CANCELED);
                    }
                })
                .create();
    }
    private void sendResult(int resultCode){
        if (getTargetFragment()== null) return;
        Intent i = new Intent();
        /*Makes sure the user does only puts a number as a value for the odometer
        * If the user does not a Toast appears informing the user of the issue*/
        try {
            i.putExtra(KEY_UPDATE_ODOMETER, Integer.parseInt(mOdometerReading.getText().toString()));
            getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, i);
        }
        catch(NumberFormatException ex){
            Toast.makeText(getActivity(),ex.getMessage(),Toast.LENGTH_SHORT);

        }
    }

    private EditText mOdometerReading;

}
