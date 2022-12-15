package com.example.crud.dashboard;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;

//ToDo: change class name DashboardItemViewHolder
public class DashboardViewHolder extends RecyclerView.ViewHolder {
    //Todo: change object names titleTxt, DashboardImg
    TextView dashBoardTitleTxt;
    ImageView dashBoardImage;

    public DashboardViewHolder(@NonNull View itemView) {
        super(itemView);
        dashBoardTitleTxt = itemView.findViewById(R.id.dash_board_title);
        dashBoardImage = itemView.findViewById(R.id.dash_board_img);
    }
}
