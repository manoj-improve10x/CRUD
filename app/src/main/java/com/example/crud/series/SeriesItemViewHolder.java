package com.example.crud.series;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;

public class SeriesItemViewHolder extends RecyclerView.ViewHolder {

    TextView seriesNameTxt;
    ImageButton DeleteBtn;
    ImageView seriesImg;

    public SeriesItemViewHolder(@NonNull View itemView) {
        super(itemView);

        seriesNameTxt = itemView.findViewById(R.id.series_name_txt);
        DeleteBtn = itemView.findViewById(R.id.series_delete_btn);
        seriesImg = itemView.findViewById(R.id.series_img);
    }
}
