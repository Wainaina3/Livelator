

package com.ibm.mobileappbuilder.livelator20161017172308.ui;

import android.os.Bundle;

import com.ibm.mobileappbuilder.livelator20161017172308.R;

import java.util.ArrayList;
import java.util.List;

import ibmmobileappbuilder.MenuItem;


/**
 * HomeFragment menu fragment.
 */
public class HomeFragment extends ibmmobileappbuilder.ui.MenuFragment {
    /**
     * Default constructor
     */
    public HomeFragment(){
        super();
    }

    // Factory method
    public static HomeFragment newInstance(Bundle args) {
        HomeFragment fragment = new HomeFragment();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Menu Fragment interface
    @Override
    public List<MenuItem> getMenuItems() {
        ArrayList<MenuItem> items = new ArrayList<MenuItem>();
        return items;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_list;
    }

    @Override
    public int getItemLayout() {
        return R.layout.home_item;
    }
}
