package com.example.duanlonmain.reading;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.duanlonmain.R;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;

public class ReadingAdapter extends RecyclerView.Adapter<ReadingAdapter.QuestionViewHolder> {
    private Context context;

    private ArrayList<Reading_quetion_hoanthanhcau> QuetionList;



    public ReadingAdapter(Context context, ArrayList<Reading_quetion_hoanthanhcau> QuetionList) {
        this.context = context;
        this.QuetionList = QuetionList;
    }



    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_reading_hoanthanhcau, parent, false);
        return new QuestionViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return QuetionList.size();
    }





    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        Reading_quetion_hoanthanhcau q = QuetionList.get(position);

        holder.txtcau.setText(q.socau + ".");


        holder.rb1.setText(q.options[0]);
        holder.rb2.setText(q.options[1]);
        holder.rb3.setText(q.options[2]);
        holder.rb4.setText(q.options[3]);
        holder.txtcau.setText(q.socau + "."+q.cauhoi);


        holder.rb1.setTextColor(Color.BLACK);
        holder.rb2.setTextColor(Color.BLACK);
        holder.rb3.setTextColor(Color.BLACK);
        holder.rb4.setTextColor(Color.BLACK);



        holder.radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            int selected = -1;
            if (checkedId == holder.rb1.getId()) selected = 0;

            else if (checkedId == holder.rb2.getId()) selected = 1;

            else if (checkedId == holder.rb3.getId()) selected = 2;

            else if (checkedId == holder.rb4.getId()) selected = 3;


            holder.rb1.setTextColor(Color.BLACK);
            holder.rb2.setTextColor(Color.BLACK);
            holder.rb3.setTextColor(Color.BLACK);
            holder.rb4.setTextColor(Color.BLACK);

            holder.giaithichcauhoi.setText(q.giaithichcauhoi);
            holder.giaithichdapan1.setText(q.giaithichdapan[0]);
            holder.giaithichdapan2.setText(q.giaithichdapan[1]);
            holder.giaithichdapan3.setText(q.giaithichdapan[2]);
            holder.giaithichdapan4.setText(q.giaithichdapan[3]);
            holder.giaithichdapan5.setText(q.giaithichdapan[4]);
            holder.giaithichdapan6.setText(q.giaithichdapan[5]);




            if (selected == q.dapan) {
                getRadio(holder, selected).setTextColor(Color.GREEN);
            } else {
                // Nếu chọn sai
                if (selected != -1) {
                    getRadio(holder, selected).setTextColor(Color.RED);
                }
                // Đáp án đúng sẽ luôn xanh
                getRadio(holder, q.dapan).setTextColor(Color.GREEN);
            }

            holder.rb1.setText(q.options[0]+"\n\t\t\t"+q.dichdapan[0]);
            holder.rb2.setText(q.options[1]+"\n\t\t\t"+q.dichdapan[1]);
            holder.rb3.setText(q.options[2]+"\n\t\t\t"+q.dichdapan[2]);
            holder.rb4.setText(q.options[3]+"\n\t\t\t"+q.dichdapan[3]);


        });
    }







    static class QuestionViewHolder extends RecyclerView.ViewHolder {
        ImageView btnPrey, btnNext;
        RadioGroup radioGroup;
        RadioButton rb1, rb2, rb3, rb4;
        TextView txtcau, giaithichcauhoi, giaithichdapan1, giaithichdapan2, giaithichdapan3, giaithichdapan4, giaithichdapan5, giaithichdapan6;



        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            btnPrey = itemView.findViewById(R.id.btnPrev);
            btnNext = itemView.findViewById(R.id.btnNext);
            radioGroup = itemView.findViewById(R.id.radioGroup);
            rb1 = itemView.findViewById(R.id.rb11);
            rb2 = itemView.findViewById(R.id.rb22);
            rb3 = itemView.findViewById(R.id.rb33);
            rb4 = itemView.findViewById(R.id.rb44);
            txtcau = itemView.findViewById(R.id.txtcau);
            giaithichcauhoi = itemView.findViewById(R.id.giaithichcauhoi);
            giaithichdapan1 = itemView.findViewById(R.id.giaithichdapan1);
            giaithichdapan2 = itemView.findViewById(R.id.giaithichdapan2);
            giaithichdapan3 = itemView.findViewById(R.id.giaithichdapan3);
            giaithichdapan4 = itemView.findViewById(R.id.giaithichdapan4);
            giaithichdapan5 = itemView.findViewById(R.id.giaithichdapan5);
            giaithichdapan6 = itemView.findViewById(R.id.giaithichdapan6);



        }
    }

    private RadioButton getRadio(QuestionViewHolder h, int idx) {
        switch (idx) {
            case 0:
                return h.rb1;
            case 1:
                return h.rb2;
            case 2:
                return h.rb3;
            default:
                return h.rb4;
        }
    }


}




