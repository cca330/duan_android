package com.example.duanlonmain;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class menu_gridAdapter extends ArrayAdapter<menu_gridItem> {
    public menu_gridAdapter(Context context, ArrayList<menu_gridItem> list) {
        super(context, 0, list);
    }

    public View getView(int position, @NonNull View convertView, @NonNull android.view.ViewGroup parent) {
        if (convertView == null) {
            convertView = android.view.LayoutInflater.from(getContext()).inflate(R.layout.menu_card_noidung, parent, false);
        }

        menu_gridItem item = getItem(position);

        ImageView img = convertView.findViewById(R.id.img_skill);
        TextView part = convertView.findViewById(R.id.txt_part);
        TextView title = convertView.findViewById(R.id.txt_title);

        if (item != null) {
            int imageResId = getContext().getResources().getIdentifier(item.getImgId(), "drawable", getContext().getPackageName());
            img.setImageResource(imageResId);
            part.setText(item.getPart());
            title.setText(item.getTitle());
        }

        return convertView;
    }

}
