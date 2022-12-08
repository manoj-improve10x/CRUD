package com.example.crud.messages;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageViewHolder> {

    private List<Message> messages;
    private OnItemActionListener onItemActionListener;

    public void setData(List<Message> messageList) {
        messages = messageList;
        notifyDataSetChanged();
    }

    public void setOnItemActionListener(OnItemActionListener actionListener) {
        onItemActionListener = actionListener;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, parent, false);
        MessageViewHolder messageViewHolder =new MessageViewHolder(view);
        return messageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Message message = messages.get(position);
        holder.name.setText(message.name);
        holder.messageText.setText(message.messageText);
        holder.number.setText(message.number);
        holder.deleteBtn.setOnClickListener(view -> {
            onItemActionListener.OnDelete(message.id);
        });
        holder.itemView.setOnClickListener(view -> {
            onItemActionListener.onEdit(message);
        });

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}
