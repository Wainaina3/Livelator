

package com.ibm.mobileappbuilder.livelator20161017172308.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.ibm.mobileappbuilder.livelator20161017172308.R;

import ibmmobileappbuilder.ui.BaseListingActivity;
/**
 * HomeActivity list activity
 */
public class HomeActivity extends BaseListingActivity {

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        if(isTaskRoot()) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        
        setTitle(getString(R.string.homeActivity));
    }

    @Override
    protected Class<? extends Fragment> getFragmentClass() {
        return HomeFragment.class;
    }

}
