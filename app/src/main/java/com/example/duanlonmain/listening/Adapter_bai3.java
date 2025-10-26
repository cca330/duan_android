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

public class Adapter_bai3 extends RecyclerView.Adapter<Adapter_bai3.QuestionViewHolder> {

    Context context;
    ArrayList<Question_bai3> questions;
    MediaPlayer mediaPlayer;
    int currentPlayingPosition = -1;
    Handler handler = new Handler();
    int answeredCount = 0;




    public Adapter_bai3(Context context, ArrayList<Question_bai3> questionList) {
        this.context = context;
        this.questions = questionList;
    }


    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_bainghe3, parent, false);
        return new QuestionViewHolder(v);
    }

    public static class QuestionViewHolder extends RecyclerView.ViewHolder {

        ImageView  btnPlay, btnRestart;
        RadioGroup radioGroup1, radioGroup2, radioGroup3;
        RadioButton rb1, rb2, rb3,rb4,rb2_1,rb2_2,rb2_3,rb2_4,rb3_1,rb3_2,rb3_3,rb3_4;
        SeekBar seekBar;
        TextView txtcau,txtcauhoi1,txtcauhoi2,txtcauhoi3,txthoithoai;


        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            btnPlay = itemView.findViewById(R.id.imageViewplay);
            btnRestart = itemView.findViewById(R.id.imageViewRestart);
            rb1 = itemView.findViewById(R.id.rb11);
            rb2 = itemView.findViewById(R.id.rb22);
            rb3 = itemView.findViewById(R.id.rb33);
            rb4 = itemView.findViewById(R.id.rb44);
            rb2_1 = itemView.findViewById(R.id.rb2_11);
            rb2_2 = itemView.findViewById(R.id.rb2_22);
            rb2_3 = itemView.findViewById(R.id.rb2_33);
            rb2_4 = itemView.findViewById(R.id.rb2_44);
            rb3_1 = itemView.findViewById(R.id.rb3_11);
            rb3_2 = itemView.findViewById(R.id.rb3_22);
            rb3_3 = itemView.findViewById(R.id.rb3_33);
            rb3_4 = itemView.findViewById(R.id.rb3_44);
            txtcauhoi1 = itemView.findViewById(R.id.txtcauhoi1);
            txtcauhoi2 = itemView.findViewById(R.id.txtcauhoi2);
            txtcauhoi3 = itemView.findViewById(R.id.txtcauhoi3);
            txthoithoai = itemView.findViewById(R.id.hoithoai);
            radioGroup1 = itemView.findViewById(R.id.radioGroup1);
            radioGroup2 = itemView.findViewById(R.id.radioGroup2);
            radioGroup3 = itemView.findViewById(R.id.radioGroup3);
            seekBar = itemView.findViewById(R.id.seekBar);
            txtcau = itemView.findViewById(R.id.txtcau);
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




    private RadioButton getRadio(Adapter_bai3.QuestionViewHolder h, int groupIndex, int idx) {
        switch (groupIndex) {
            case 1:
                switch (idx) {
                    case 0: return h.rb1;
                    case 1: return h.rb2;
                    case 2: return h.rb3;
                    default: return h.rb4;
                }
            case 2:
                switch (idx) {
                    case 0: return h.rb2_1;
                    case 1: return h.rb2_2;
                    case 2: return h.rb2_3;
                    default: return h.rb2_4;
                }
            case 3:
                switch (idx) {
                    case 0: return h.rb3_1;
                    case 1: return h.rb3_2;
                    case 2: return h.rb3_3;
                    default: return h.rb3_4;
                }
            default:
                return null;
        }
    }


    public void stopCurrentAudio() {
        releaseMedia();
        notifyItemChanged(currentPlayingPosition);
        currentPlayingPosition = -1;
    }



    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {

        Question_bai3 q = questions.get(position);

        holder.rb1.setText(q.options[0]);
        holder.rb2.setText(q.options[1]);
        holder.rb3.setText(q.options[2]);
        holder.rb4.setText(q.options[3]);

        holder.txtcauhoi1.setText(q.cauhoi[0]);
        holder.txtcauhoi2.setText(q.cauhoi[1]);
        holder.txtcauhoi3.setText(q.cauhoi[2]);

        holder.rb2_1.setText(q.options[0]);
        holder.rb2_2.setText(q.options[1]);
        holder.rb2_3.setText(q.options[2]);
        holder.rb2_4.setText(q.options[3]);

        holder.rb3_1.setText(q.options[0]);
        holder.rb3_2.setText(q.options[1]);
        holder.rb3_3.setText(q.options[2]);
        holder.rb3_4.setText(q.options[3]);

        holder.txtcau.setText(q.causo + ".");



        holder.rb1.setText("A. "+q.answ[0]);
        holder.rb2.setText("B. "+q.answ[1]);
        holder.rb3.setText("C. "+q.answ[2]);
        holder.rb4.setText("D. "+q.answ[3]);


        holder.rb2_1.setText("A. "+q.answ[4]);
        holder.rb2_2.setText("B. "+q.answ[5]);
        holder.rb2_3.setText("C. "+q.answ[6]);
        holder.rb2_4.setText("D. "+q.answ[7]);



        holder.rb3_1.setText("A. "+q.answ[8]);
        holder.rb3_2.setText("B. "+q.answ[9]);
        holder.rb3_3.setText("C. "+q.answ[10]);
        holder.rb3_4.setText("D. "+q.answ[11]);





        // Reset màu khi bind lại
        holder.rb1.setTextColor(Color.BLACK);
        holder.rb2.setTextColor(Color.BLACK);
        holder.rb3.setTextColor(Color.BLACK);
        holder.rb4.setTextColor(Color.BLACK);

        holder.rb2_1.setTextColor(Color.BLACK);
        holder.rb2_2.setTextColor(Color.BLACK);
        holder.rb2_3.setTextColor(Color.BLACK);
        holder.rb2_4.setTextColor(Color.BLACK);

        holder.rb3_1.setTextColor(Color.BLACK);
        holder.rb3_2.setTextColor(Color.BLACK);
        holder.rb3_3.setTextColor(Color.BLACK);
        holder.rb3_4.setTextColor(Color.BLACK);













        final boolean[] answered = {false, false, false};


        Runnable checkAllAnswered = () -> {
            if (answered[0] && answered[1] && answered[2]) {
                holder.txthoithoai.setText(q.hoithoai);
                showResult(holder, q);
            }
        };





        holder.radioGroup1.setOnCheckedChangeListener((group, checkedId) -> {
            int selected = -1;
            if (checkedId == holder.rb1.getId()) selected = 0;
            else if (checkedId == holder.rb2.getId()) selected = 1;
            else if (checkedId == holder.rb3.getId()) selected = 2;
            else if (checkedId == holder.rb4.getId()) selected = 3;

            q.selectedAnswers[0] = selected; // Lưu lựa chọn
            if (checkedId != -1) answered[0] = true;
            checkAllAnswered.run();
        });

        holder.radioGroup2.setOnCheckedChangeListener((group, checkedId) -> {
            int selected = -1;
            if (checkedId == holder.rb2_1.getId()) selected = 0;
            else if (checkedId == holder.rb2_2.getId()) selected = 1;
            else if (checkedId == holder.rb2_3.getId()) selected = 2;
            else if (checkedId == holder.rb2_4.getId()) selected = 3;

            q.selectedAnswers[1] = selected;
            if (checkedId != -1) answered[1] = true;
            checkAllAnswered.run();
        });

        holder.radioGroup3.setOnCheckedChangeListener((group, checkedId) -> {
            int selected = -1;
            if (checkedId == holder.rb3_1.getId()) selected = 0;
            else if (checkedId == holder.rb3_2.getId()) selected = 1;
            else if (checkedId == holder.rb3_3.getId()) selected = 2;
            else if (checkedId == holder.rb3_4.getId()) selected = 3;

            q.selectedAnswers[2] = selected;
            if (checkedId != -1) answered[2] = true;
            checkAllAnswered.run();
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
            mediaPlayer = MediaPlayer.create(context, q.audio);
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
    private Runnable updateSeekBar(Adapter_bai3.QuestionViewHolder holder) {
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





    private void setAnswerColor(QuestionViewHolder holder, int groupIndex, int selected, int correct) {
        for (int i = 0; i < 4; i++) {
            getRadio(holder, groupIndex, i).setTextColor(Color.BLACK);
        }


        if (selected == correct) {
            getRadio(holder, groupIndex, selected).setTextColor(Color.GREEN);
        } else {
            if (selected != -1)
                getRadio(holder, groupIndex, selected).setTextColor(Color.RED);
            getRadio(holder, groupIndex, correct).setTextColor(Color.GREEN);
        }
    }


    private void showResult(QuestionViewHolder holder, Question_bai3 q) {
        // Câu 1
        setAnswerColor(holder, 1, q.selectedAnswers[0], q.dapan[0]);
        // Câu 2
        setAnswerColor(holder, 2, q.selectedAnswers[1], q.dapan[1]);
        // Câu 3
        setAnswerColor(holder, 3, q.selectedAnswers[2], q.dapan[2]);
    }







}
