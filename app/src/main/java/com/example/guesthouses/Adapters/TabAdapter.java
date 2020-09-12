package com.example.guesthouses.Adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.guesthouses.Fragments.Details;
import com.example.guesthouses.Fragments.Reviews;

public class TabAdapter extends FragmentPagerAdapter {

    Context context;
    int totalTabs;

    public TabAdapter(@NonNull FragmentManager fm,Context c, int totalTabs) {
        super(fm);
        context = c;
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0:
                Details details= new Details();
                return details;
            case 1:
                Reviews reviews= new Reviews();
                return reviews;
            default:
            return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
