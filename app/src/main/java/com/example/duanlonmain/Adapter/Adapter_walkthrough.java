package com.example.duanlonmain.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.duanlonmain.intro.Intro_two222;
import com.example.duanlonmain.intro.Intro_one111;
import com.example.duanlonmain.intro.Intro_three333;
import com.example.duanlonmain.intro.Intro_four444;


public class Adapter_walkthrough extends FragmentStatePagerAdapter {

    private final Fragment[] fragments = {
            new Intro_one111(),
            new Intro_two222(),
            new Intro_three333(),
            new Intro_four444()
    };

    public Adapter_walkthrough(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments[position];  // lấy fragment theo index
    }

    @Override
    public int getCount() {
        return fragments.length; // tự động đếm
    }
}

