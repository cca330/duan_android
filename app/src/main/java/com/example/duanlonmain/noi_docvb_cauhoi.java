package com.example.duanlonmain;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class noi_docvb_cauhoi extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noi_cauhoi);

        int x = 6;
        Button btn_batDau = findViewById(R.id.btn_batDau);
        ArrayList<Integer> al = new ArrayList<Integer>();
        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item,al);
        Spinner sp_slCauHoi = findViewById(R.id.sp_slCauHoi);
        for(int i = 1; i <= x; i++){
            ((ArrayList<Integer>) al).add(i);
        }
        sp_slCauHoi.setAdapter(ad);

        sp_slCauHoi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
