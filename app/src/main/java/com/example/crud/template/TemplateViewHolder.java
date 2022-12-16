package com.example.crud.template;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;

public class TemplateViewHolder extends RecyclerView.ViewHolder {

    TextView templateTextTxt;
    ImageButton deleteBtn;

    public TemplateViewHolder(@NonNull View itemView) {
        super(itemView);
        templateTextTxt = itemView.findViewById(R.id.template_text_txt);
        deleteBtn = itemView.findViewById(R.id.delete_btn);
    }
}
