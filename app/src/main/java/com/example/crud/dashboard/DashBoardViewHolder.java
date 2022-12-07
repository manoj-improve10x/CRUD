package com.example.crud.dashboard;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;

public class DashBoardViewHolder extends RecyclerView.ViewHolder {

     TextView dashBoardTitle;
     ImageView dashBoardImage;
     LinearLayout dashboardLayout;

    public DashBoardViewHolder(@NonNull View itemView) {
        super(itemView);
        dashBoardTitle = itemView.findViewById(R.id.dash_board_title);
        dashBoardImage = itemView.findViewById(R.id.dash_board_img);
        dashboardLayout = itemView.findViewById(R.id.dashboard_layout);

    }
}
