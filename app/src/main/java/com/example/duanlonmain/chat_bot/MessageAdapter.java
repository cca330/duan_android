package com.example.duanlonmain.chat_bot;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.duanlonmain.R;

import androidx.annotation.NonNull;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MessageEntity> messages;

    public MessageAdapter(List<MessageEntity> messages) {
        this.messages = messages;
    }

    @Override
    public int getItemViewType(int position) {
        return messages.get(position).isUser() ? 0 : 1; // 0=user, 1=AI
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            //LayoutInflater là class dùng để thổi phồng file XML layout thành một object View trong Java/Kotlin.
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_user, parent, false);
            return new UserViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_ai, parent, false);
            return new AiViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MessageEntity msg = messages.get(position);
        if (holder instanceof UserViewHolder) {
            ((UserViewHolder) holder).textView.setText(msg.getText());
        } else if (holder instanceof AiViewHolder) {
            ((AiViewHolder) holder).textView.setText(msg.getText());
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        UserViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvUserMessage);
        }
    }

    static class AiViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        AiViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvMessage);
        }
    }
}
