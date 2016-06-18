package com.example.eddie.drivelog;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/*Hosts Calendar Fragment*/
public class CalendarActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment(){
        return new CalendarFragment();
    }



}
