package com.example.eddie.drivelog;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.CalendarView;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * A placeholder fragment containing a simple view.
 */
public class CalendarFragment extends Fragment {

public static final String EXTRA_DATE = "com.example.eddie.drivelog.extra_date";
    public static final String TAG = "Eddie";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_calandar, container, false);
        mCalendarView= (CalendarView)v.findViewById(R.id.calendar_widget);
                currDate = mCalendarView.getDate();
        mCalendarView.setShowWeekNumber(false);

        mCalendarView.setOnDateChangeListener( new CalendarView.OnDateChangeListener(){
            public void onSelectedDayChange(CalendarView view, int year, int month,int dayOfMonth) {
                // open listview activity
                long date = mCalendarView.getDate();
                Log.d(TAG,"date" + date);
                Log.d(TAG,""+(currDate != date));
                if (currDate != date) {
                    Log.d("Eddie", "longDate:" + date);
                    Date mDate = new Date(date);
                    Log.d(TAG, mDate.toString());
                    Intent i = new Intent(getActivity(), DriveListActivity.class);
                    i.putExtra(EXTRA_DATE, date);
                    startActivity(i);
                }
            }
        });
        return v;
    }
    @Override
    public void onResume(){
        super.onResume();
        currDate = mCalendarView.getDate();
    }

    private long currDate;
    private CalendarView mCalendarView;
    private GregorianCalendar mCalendar;
}
