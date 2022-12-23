package com.example.crud.message;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;
import com.example.crud.databinding.MessageItemBinding;

import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessageViewHolder> {

    private List<Message> messages;
    private OnItemActionListener onItemActionListener;

    void setData(List<Message> messages) {
        this.messages = messages;
        notifyDataSetChanged();
    }

    void setOnItemActionListener(OnItemActionListener actionListener) {
        onItemActionListener = actionListener;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MessageItemBinding binding = MessageItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        MessageViewHolder messageViewHolder = new MessageViewHolder(binding);
        return messageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Message message = messages.get(position);
        holder.binding.nameTxt.setText(message.name);
        holder.binding.messageTextTxt.setText(message.messageText);
        holder.binding.phoneNumberTxt.setText(message.number);
        holder.binding.deleteBtn.setOnClickListener(view -> {
            onItemActionListener.OnDelete(message.id);
        });
        holder.binding.getRoot().setOnClickListener(view -> {
            onItemActionListener.onEdit(message);
        });
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}
