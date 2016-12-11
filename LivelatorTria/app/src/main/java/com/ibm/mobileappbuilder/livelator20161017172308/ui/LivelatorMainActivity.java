
package com.ibm.mobileappbuilder.livelator20161017172308.ui;

import android.support.v4.app.Fragment;
import android.util.SparseArray;

import ibmmobileappbuilder.ui.DrawerActivity;

import ibmmobileappbuilder.actions.StartActivityAction;
import ibmmobileappbuilder.util.Constants;
import com.ibm.mobileappbuilder.livelator20161017172308.R;

public class LivelatorMainActivity extends DrawerActivity {

    private final SparseArray<Class<? extends Fragment>> sectionFragments = new SparseArray<>();
    {
            sectionFragments.append(R.id.entry0, HomeFragment.class);
            sectionFragments.append(R.id.entry1, LogoutFragment.class);
    }

    @Override
    public SparseArray<Class<? extends Fragment>> getSectionFragmentClasses() {
      return sectionFragments;
    }

}
