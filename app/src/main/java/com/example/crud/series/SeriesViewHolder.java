package com.example.crud.series;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;

public class SeriesViewHolder extends RecyclerView.ViewHolder {

    public TextView seriesNameTxt;
    public ImageButton seriesDeleteBtn;
    public ImageView seriesImg;

    public SeriesViewHolder(@NonNull View itemView) {
        super(itemView);

        seriesNameTxt = itemView.findViewById(R.id.series_name_txt);
        seriesDeleteBtn = itemView.findViewById(R.id.series_delete_btn);
        seriesImg = itemView.findViewById(R.id.series_img);
    }
}
