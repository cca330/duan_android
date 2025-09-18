package com.example.duanlonmain.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.duanlonmain.intro.Intro_two222;
import com.example.duanlonmain.intro.Intro_one111;
import com.example.duanlonmain.intro.Intro_three333;
import com.example.duanlonmain.intro.Intro_four444;


public class Adapter_walkthrough  extends FragmentStatePagerAdapter {

    public Adapter_walkthrough( FragmentManager fm) {
        super(fm);
    }



    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                Intro_one111 tab1 = new Intro_one111();
                return tab1;
            case 1:
                Intro_two222 tab2 = new Intro_two222();
                return tab2;
            case 2:
                Intro_three333 tab3 = new Intro_three333();
                return tab3;
            case 3:
                Intro_four444 tab4 = new Intro_four444();
                return tab4;
            default:
                return null;

        }
    }





    @Override
    public int getCount() {
        return 4;
    }
}
