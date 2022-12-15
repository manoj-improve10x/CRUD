package com.example.crud.message;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;

public class MessageViewHolder extends RecyclerView.ViewHolder {

    TextView nameTxt;
    //ToDo: change object name phoneNumberTxt
    TextView numberTxt;
    ImageButton deleteBtn;
    TextView messageTextTxt;

    public MessageViewHolder(@NonNull View itemView) {
        super(itemView);
        nameTxt = itemView.findViewById(R.id.name_txt);
        numberTxt = itemView.findViewById(R.id.number_txt);
        messageTextTxt = itemView.findViewById(R.id.message_text_txt);
        deleteBtn = itemView.findViewById(R.id.delete_btn);
    }
}
