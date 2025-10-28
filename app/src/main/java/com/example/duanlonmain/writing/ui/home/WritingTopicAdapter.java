package com.example.duanlonmain.writing.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.duanlonmain.R;
import com.example.duanlonmain.writing.data.local.model.WritingTopic;

import java.util.List;

public class WritingTopicAdapter extends RecyclerView.Adapter<WritingTopicAdapter.ViewHolder> {
    private List<WritingTopic> topics;
    private OnTopicClickListener listener;

    public interface OnTopicClickListener {
        void onTopicClick(WritingTopic topic);
    }

    public WritingTopicAdapter(List<WritingTopic> topics, OnTopicClickListener listener) {
        this.topics = topics;
        this.listener = listener;
    }
    public void updateData(List<WritingTopic> newTopics) {
        this.topics.clear();
        if (newTopics != null) {
            this.topics.addAll(newTopics);
        }
        notifyDataSetChanged();  // LÀM MỚI ADAPTER
    }

    @NonNull @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_writing_topic, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WritingTopic topic = topics.get(position);
        holder.tvTopic.setText(topic.title);
        holder.itemView.setOnClickListener(v -> listener.onTopicClick(topic));
    }

    @Override public int getItemCount() { return topics.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTopic;
        ViewHolder(View itemView) {
            super(itemView);
            tvTopic = itemView.findViewById(R.id.tv_topic);
        }
    }
}
