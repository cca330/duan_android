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

import androidx.recyclerview.widget.RecyclerView;

import com.example.duanlonmain.R;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {

    private Context context;
    private ArrayList<Question> questions;

    // MediaPlayer chỉ chạy 1 bài
    private MediaPlayer mediaPlayer;
    private Handler handler = new Handler();

    public QuestionAdapter(Context context, ArrayList<Question> questionList) {
        this.context = context;
        this.questions = questionList;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_question, parent, false);
        return new QuestionViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        Question q = questions.get(position);

        holder.imgQuestion.setImageResource(q.imageRes);
        holder.rb1.setText(q.options[0]);
        holder.rb2.setText(q.options[1]);
        holder.rb3.setText(q.options[2]);
        holder.rb4.setText(q.options[3]);

        // Reset màu khi bind lại
        holder.rb1.setTextColor(Color.BLACK);
        holder.rb2.setTextColor(Color.BLACK);
        holder.rb3.setTextColor(Color.BLACK);
        holder.rb4.setTextColor(Color.BLACK);

        // Check đáp án
        holder.radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            int selected = -1;
            if (checkedId == holder.rb1.getId()) selected = 0;
            else if (checkedId == holder.rb2.getId()) selected = 1;
            else if (checkedId == holder.rb3.getId()) selected = 2;
            else if (checkedId == holder.rb4.getId()) selected = 3;

            if (selected == q.correctIndex) {
                getRadio(holder, selected).setTextColor(Color.GREEN);
            } else {
                getRadio(holder, selected).setTextColor(Color.RED);
                getRadio(holder, q.correctIndex).setTextColor(Color.GREEN);
            }
        });

        // --- PLAY ---
        holder.btnPlay.setOnClickListener(v -> {
            releaseMedia(); // dừng audio cũ nếu có
            mediaPlayer = MediaPlayer.create(context, q.audioRes);
            mediaPlayer.start();

            // cập nhật seekbar
            handler.post(updateSeekBar(holder));

            mediaPlayer.setOnCompletionListener(mp -> {
                holder.seekBar.setProgress(0);
                releaseMedia();
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

    @Override
    public int getItemCount() {
        return questions.size();
    }

    // Cập nhật SeekBar theo tiến độ phát
    private Runnable updateSeekBar(QuestionViewHolder holder) {
        return new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    int progress = (int) (((float) mediaPlayer.getCurrentPosition()
                            / mediaPlayer.getDuration()) * 100);
                    holder.seekBar.setProgress(progress);
                    handler.postDelayed(this, 500);
                }
            }
        };
    }

    // Giải phóng MediaPlayer
    private void releaseMedia() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void playAudioAt(int position) {
        if (position < 0 || position >= questions.size()) return;

        // Dừng audio cũ
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }

        // Tạo audio mới cho câu hiện tại
        Question q = questions.get(position);
        mediaPlayer = MediaPlayer.create(context, q.audioRes);
        mediaPlayer.start();

        // Nếu muốn cập nhật SeekBar thì notifyItemChanged(position)
    }

    // ViewHolder
    static class QuestionViewHolder extends RecyclerView.ViewHolder {
        ImageView imgQuestion, btnPlay, btnRestart;
        RadioGroup radioGroup;
        RadioButton rb1, rb2, rb3, rb4;
        SeekBar seekBar;

        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            imgQuestion = itemView.findViewById(R.id.imgQuestion);
            btnPlay = itemView.findViewById(R.id.imageViewplay);
            btnRestart = itemView.findViewById(R.id.imageViewRestart); // thêm nút restart
            radioGroup = itemView.findViewById(R.id.radioGroup);
            rb1 = itemView.findViewById(R.id.rb1);
            rb2 = itemView.findViewById(R.id.rb2);
            rb3 = itemView.findViewById(R.id.rb3);
            rb4 = itemView.findViewById(R.id.rb4);
            seekBar = itemView.findViewById(R.id.seekBar);
        }
    }

    private RadioButton getRadio(QuestionViewHolder h, int idx) {
        switch (idx) {
            case 0: return h.rb1;
            case 1: return h.rb2;
            case 2: return h.rb3;
            default: return h.rb4;
        }
    }
}
