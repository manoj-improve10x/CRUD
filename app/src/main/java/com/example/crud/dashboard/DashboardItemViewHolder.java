package com.example.crud.dashboard;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;
import com.example.crud.databinding.DashBoardItemBinding;

public class DashboardItemViewHolder extends RecyclerView.ViewHolder {

    DashBoardItemBinding binding;

    public DashboardItemViewHolder(DashBoardItemBinding dashBoardItemBinding) {
        super(dashBoardItemBinding.getRoot());
       binding = dashBoardItemBinding;
    }
}
