package com.example.duanlonmain.extend.Grammar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.duanlonmain.R;

import java.util.List;

public class PartofSpeechAdapter extends RecyclerView.Adapter<PartofSpeechAdapter.ViewHolder> {

    private Context context;
    private List<PartofSpeech> partList;

    public PartofSpeechAdapter(Context context, List<PartofSpeech> partList) {
        this.context = context;
        this.partList = partList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_part_of_speech, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PartofSpeech item = partList.get(position);
        holder.txtTuloai.setText(item.getTitle());
        holder.txtDinhnghia.setText(item.getDefinition());
        holder.txtPhanloai.setText(item.getTypes());
        holder.txtVidu.setText(item.getExamples());
        holder.imageView.setImageResource(item.getImageResId());
    }

    @Override
    public int getItemCount() {
        return partList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTuloai, txtDinhnghia, txtPhanloai, txtVidu;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTuloai = itemView.findViewById(R.id.txtTuloai);
            txtDinhnghia = itemView.findViewById(R.id.txtDinhnghia);
            txtPhanloai = itemView.findViewById(R.id.txtPhanloai);
            txtVidu = itemView.findViewById(R.id.txtVidu);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}

