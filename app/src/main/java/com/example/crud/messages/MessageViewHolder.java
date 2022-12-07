package com.example.crud.messages;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;

public class MessageViewHolder extends RecyclerView.ViewHolder {

    public TextView name;
    public TextView number;
    public ImageButton deleteBtn;
    public TextView messageText;

    public MessageViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name_txt);
        number = itemView.findViewById(R.id.number_txt);
        messageText = itemView.findViewById(R.id.message_text_txt);
        deleteBtn = itemView.findViewById(R.id.delete_btn);
    }
}
