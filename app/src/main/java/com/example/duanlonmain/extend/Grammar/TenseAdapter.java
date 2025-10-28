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

public class TenseAdapter extends RecyclerView.Adapter<TenseAdapter.ViewHolder> {

    private Context context;
    private List<Tense> tenseList;

    public TenseAdapter(Context context, List<Tense> tenseList) {
        this.context = context;
        this.tenseList = tenseList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tense, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tense tense = tenseList.get(position);
        holder.txtTenthi.setText(tense.getTenThi());
        holder.txtCachdung.setText("Cách dùng: " + tense.getCachDung());
        holder.txtKhangdinh.setText("(+): " + tense.getKhangDinh());
        holder.txtPhudinh.setText("(-): " + tense.getPhuDinh());
        holder.txtNghivan.setText("(?): " + tense.getNghiVan());
        holder.txtVidu.setText("Eg: " + tense.getViDu());
        holder.imageView.setImageResource(tense.getImageResId());
    }

    @Override
    public int getItemCount() {
        return tenseList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txtTenthi, txtCachdung, txtKhangdinh, txtPhudinh, txtNghivan, txtVidu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            txtTenthi = itemView.findViewById(R.id.txttenthi);
            txtCachdung = itemView.findViewById(R.id.txtcachdung);
            txtKhangdinh = itemView.findViewById(R.id.txtkhangdinh);
            txtPhudinh = itemView.findViewById(R.id.txtphudinh);
            txtNghivan = itemView.findViewById(R.id.txtnghivan);
            txtVidu = itemView.findViewById(R.id.txtvidu);
        }
    }
}
