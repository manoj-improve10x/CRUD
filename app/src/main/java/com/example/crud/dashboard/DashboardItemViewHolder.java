package com.example.crud.dashboard;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;

public class DashboardItemViewHolder extends RecyclerView.ViewHolder {

    TextView titleTxt;
    ImageView dashboardImg;

    public DashboardItemViewHolder(@NonNull View itemView) {
        super(itemView);
        titleTxt = itemView.findViewById(R.id.title_txt);
        dashboardImg = itemView.findViewById(R.id.dashboard_img);
    }
}
