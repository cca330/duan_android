package com.example.duanlonmain;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.Nullable;

import com.example.duanlonmain.listening.Bai1_nghe_img;
import com.example.duanlonmain.reading.Reading_hoanthanhcau;
import com.example.duanlonmain.vocabulary.Vocabulary;

import java.util.ArrayList;

public class menu_Activity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);

        GridView gv_listening =  findViewById(R.id.gv_listening);
        GridView gv_reading =  findViewById(R.id.gv_reading);
        GridView gv_speaking =  findViewById(R.id.gv_speaking);
        GridView gv_writing =  findViewById(R.id.gv_writing);

        ArrayList al_listening = new ArrayList<menu_gridItem>();
        ArrayList al_reading = new ArrayList<menu_gridItem>();
        ArrayList al_speaking = new ArrayList<menu_gridItem>();
        ArrayList al_writing = new ArrayList<menu_gridItem>();

        al_listening.add(new menu_gridItem("@mipmap/ic_launcher", "Nghe hình ảnh", ""));


        al_reading.add(new menu_gridItem("@mipmap/ic_launcher", "Từ vựng", ""));
        al_reading.add(new menu_gridItem("@mipmap/ic_launcher", "Hoàn thành câu", ""));


        al_speaking.add(new menu_gridItem("@mipmap/ic_launcher", "Part 1", ""));
        al_speaking.add(new menu_gridItem("@mipmap/ic_launcher", "Part 2", ""));
        al_speaking.add(new menu_gridItem("@mipmap/ic_launcher", "Part 3", ""));
        al_speaking.add(new menu_gridItem("@mipmap/ic_launcher", "Part 4", ""));
        al_speaking.add(new menu_gridItem("@mipmap/ic_launcher", "Part 5", ""));

        al_writing.add(new menu_gridItem("@mipmap/ic_launcher", "Part 1", ""));
        al_writing.add(new menu_gridItem("@mipmap/ic_launcher", "Part 2", ""));
        al_writing.add(new menu_gridItem("@mipmap/ic_launcher", "Part 3", ""));

        menu_gridAdapter adapter_listening = new menu_gridAdapter(this, al_listening);
        menu_gridAdapter adapter_reading = new menu_gridAdapter(this, al_reading);
        menu_gridAdapter adapter_speaking = new menu_gridAdapter(this, al_speaking);
        menu_gridAdapter adapter_writing = new menu_gridAdapter(this, al_writing);
        
        gv_listening.setAdapter(adapter_listening);
        gv_reading.setAdapter(adapter_reading);
        gv_speaking.setAdapter(adapter_speaking);
        gv_writing.setAdapter(adapter_writing);






        gv_listening.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                switch (position){

                    case 0:
                        intent = new Intent(menu_Activity.this, Bai1_nghe_img.class);
                        break;
                }
                if (intent != null) startActivity(intent);

            }

        });





        gv_reading.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                switch (position){
                    case 0:
                        intent = new Intent(menu_Activity.this, Vocabulary.class);

                    case 1:
                        intent = new Intent(menu_Activity.this, Reading_hoanthanhcau.class);
                        break;
                }
                if (intent != null) startActivity(intent);
            }
        });












}

}
