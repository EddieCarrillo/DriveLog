package com.example.eddie.drivelog;

import android.support.v4.app.Fragment;

/*Hosts ManualDriveFragment */
public class DriveActivity extends SingleFragmentActivity {

    public Fragment createFragment() {
        return new ManualDriveFragment();
    }
}


