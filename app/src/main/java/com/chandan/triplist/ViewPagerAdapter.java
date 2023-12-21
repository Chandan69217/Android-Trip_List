package com.chandan.triplist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        fragmentManager = fm;
        fragmentTransaction = fragmentManager.beginTransaction();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        if(position==0){
            fragment = new ListFrag();
        }else if(position == 1){
            fragment = new BoysFrag();
        }else {
            fragment = new GirlsFrag();
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0){
            return "List All";
        }else if(position==1){
            return "Boys";
        }else{
            return "Girls";
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
