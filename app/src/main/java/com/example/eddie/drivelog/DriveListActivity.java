package com.example.eddie.drivelog;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/*Hosts DriveListFragment*/
public class DriveListActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment(){
        return new DriveListFragment();
    }

}
