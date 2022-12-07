package com.example.crud;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    TextView movieNameTxt;
    ImageView movieImg;
    ImageButton movieDeleteBtn;

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);
        movieNameTxt = itemView.findViewById(R.id.movie_name_txt);
        movieDeleteBtn = itemView.findViewById(R.id.movie_delete_btn);
        movieImg = itemView.findViewById(R.id.movie_img);
    }
}
