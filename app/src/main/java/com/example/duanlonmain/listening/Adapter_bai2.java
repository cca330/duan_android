package com.example.duanlonmain.listening;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.duanlonmain.R;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;

public class Adapter_bai2 extends RecyclerView.Adapter<Adapter_bai2.QuestionViewHolder> {

    private Context context;

    private ArrayList<Question_bai2> questions;
    private MediaPlayer mediaPlayer;
    private int currentPlayingPosition = -1;
    private Handler handler = new Handler();


    public Adapter_bai2(Context context, ArrayList<Question_bai2> questionList) {
        this.context = context;
        this.questions = questionList;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_nghe_bai2_ques_and_response, parent, false);
        return new QuestionViewHolder(v);
    }


    public static class QuestionViewHolder extends RecyclerView.ViewHolder {
        ImageView imgQuestion, btnPlay, btnRestart;
        RadioGroup radioGroup;
        RadioButton rb1, rb2, rb3;
        SeekBar seekBar;
        TextView txtcau,txtcauhoi;

        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            imgQuestion = itemView.findViewById(R.id.imgQuestion);
            btnPlay = itemView.findViewById(R.id.imageViewplay);
            btnRestart = itemView.findViewById(R.id.imageViewRestart);
            radioGroup = itemView.findViewById(R.id.radioGroup);
            rb1 = itemView.findViewById(R.id.rb1);
            rb2 = itemView.findViewById(R.id.rb2);
            rb3 = itemView.findViewById(R.id.rb3);
            seekBar = itemView.findViewById(R.id.seekBar);
            txtcau = itemView.findViewById(R.id.txtcau);
            txtcauhoi = itemView.findViewById(R.id.cauhoi);

        }
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }


    // Giải phóng MediaPlayer
    private void releaseMedia() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.stop();
                mediaPlayer.release();

            } catch (Exception e) {
                e.printStackTrace();
            }
            mediaPlayer = null;
        }
        handler.removeCallbacksAndMessages(null);
    }




    private RadioButton getRadio(Adapter_bai2.QuestionViewHolder h, int idx) {
        switch (idx) {
            case 0: return h.rb1;
            case 1: return h.rb2;
            default: return h.rb3;
        }
    }

    public void stopCurrentAudio() {
        releaseMedia();
        notifyItemChanged(currentPlayingPosition);
        currentPlayingPosition = -1;
    }






    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        Question_bai2 q = questions.get(position);

        holder.rb1.setText(q.options[0]);
        holder.rb2.setText(q.options[1]);
        holder.rb3.setText(q.options[2]);
        holder.txtcau.setText(q.causo + ".");

        // Reset màu khi bind lại
        holder.rb1.setTextColor(Color.BLACK);
        holder.rb2.setTextColor(Color.BLACK);
        holder.rb3.setTextColor(Color.BLACK);



        // Check đáp án
        holder.radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            int selected = -1;
            if (checkedId == holder.rb1.getId()) selected = 0;
            else if (checkedId == holder.rb2.getId()) selected = 1;
            else if (checkedId == holder.rb3.getId()) selected = 2;


            // Hiện đầy đủ text của cả 4 đáp án
            holder.txtcauhoi.setText(q.cauhoi);
            holder.rb1.setText("A. " + q.answ[0]);
            holder.rb2.setText("B. " + q.answ[1]);
            holder.rb3.setText("C. " + q.answ[2]);


            // Reset màu về đen trước
            holder.rb1.setTextColor(Color.BLACK);
            holder.rb2.setTextColor(Color.BLACK);
            holder.rb3.setTextColor(Color.BLACK);


            // Nếu chọn đúng
            if (selected == q.correctIndex) {
                getRadio(holder, selected).setTextColor(Color.GREEN);
            } else {
                // Nếu chọn sai
                if (selected != -1) {
                    getRadio(holder, selected).setTextColor(Color.RED);
                }
                // Đáp án đúng sẽ luôn xanh
                getRadio(holder, q.correctIndex).setTextColor(Color.GREEN);
            }



        });








        holder.btnPlay.setOnClickListener(v -> {

            int clickedPosition = holder.getAdapterPosition();// lấy vị trí item hiện tại

            // Nếu đang phát mà nhấn lại chính câu đó → tắt
            if (mediaPlayer != null && currentPlayingPosition == clickedPosition) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                releaseMedia();
                holder.btnPlay.setImageResource(R.drawable.circle_play_solid_full);
                currentPlayingPosition = -1;
                return;
            }

            // Nếu đang phát câu khác → tắt audio cũ
            if (mediaPlayer != null) {
                releaseMedia();
                notifyItemChanged(currentPlayingPosition); // cập nhật lại icon play câu cũ
            }

            // Phát câu mới
            mediaPlayer = MediaPlayer.create(context, q.audioRes);
            mediaPlayer.start();
            holder.btnPlay.setImageResource(R.drawable.pause_solid_full);
            currentPlayingPosition = clickedPosition;
            handler.post(updateSeekBar(holder));

            // Khi phát xong → đổi icon về play
            mediaPlayer.setOnCompletionListener(mp -> {
                holder.btnPlay.setImageResource(R.drawable.circle_play_solid_full);
                releaseMedia();
                currentPlayingPosition = -1;
            });

        });







        // --- RESTART ---
        holder.btnRestart.setOnClickListener(v -> {
            if (mediaPlayer != null) {
                mediaPlayer.seekTo(0);
                mediaPlayer.start();
            }
        });

        // --- SeekBar tua ---
        holder.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser && mediaPlayer != null) {
                    int newPos = (mediaPlayer.getDuration() * progress) / 100;
                    mediaPlayer.seekTo(newPos);
                }
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });


    }



    // Cập nhật SeekBar theo tiến độ phát
    private Runnable updateSeekBar(Adapter_bai2.QuestionViewHolder holder) {
        return new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    int progress = (int) (((float) mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration()) * 100);
                    holder.seekBar.setProgress(progress);
                    handler.postDelayed(this, 500);
                }
            }
        };
    }


}
