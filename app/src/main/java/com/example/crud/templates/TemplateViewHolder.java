package com.example.crud.templates;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;

public class TemplateViewHolder extends RecyclerView.ViewHolder {

     TextView textTxt;
     ImageButton deleteTemplateBtn;

    public TemplateViewHolder(@NonNull View itemView) {
        super(itemView);
        textTxt = itemView.findViewById(R.id.text_txt);
        deleteTemplateBtn = itemView.findViewById(R.id.delete_template_btn);
    }
}
