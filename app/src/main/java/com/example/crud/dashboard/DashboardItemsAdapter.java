package com.example.crud.dashboard;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.movie.MoviesActivity;
import com.example.crud.series.SeriesListActivity;
import com.example.crud.message.MessagesActivity;
import com.example.crud.R;
import com.example.crud.template.TemplatesActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DashboardItemsAdapter extends RecyclerView.Adapter<DashboardViewHolder> {

    private ArrayList<DashboardItem> dashboardItems;

    //Todo: DashboardItemAdapter-change parameter name dashboardItems
    void setData(ArrayList<DashboardItem> dashboardItemList) {
        dashboardItems = dashboardItemList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DashboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dash_board_item, parent, false);
        DashboardViewHolder dashBoardViewHolder = new DashboardViewHolder(view);
        return dashBoardViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardViewHolder holder, int position) {
        DashboardItem dashboardItem = dashboardItems.get(position);
        holder.dashBoardTitleTxt.setText(dashboardItem.title);
        Picasso.get().load(dashboardItem.imageUrl).into(holder.dashBoardImage);
        holder.itemView.setOnClickListener(view -> {
            if (holder.dashBoardTitleTxt.getText().toString().equalsIgnoreCase("Messages")) {
                Intent intent = new Intent(holder.dashBoardTitleTxt.getContext(), MessagesActivity.class);
                holder.itemView.getContext().startActivity(intent);
            } else if (holder.dashBoardTitleTxt.getText().toString().equalsIgnoreCase("SeriesList")) {
                Intent intent = new Intent(holder.dashBoardTitleTxt.getContext(), SeriesListActivity.class);
                holder.itemView.getContext().startActivity(intent);
            } else if (holder.dashBoardTitleTxt.getText().toString().equalsIgnoreCase("Templates")) {
                Intent intent = new Intent(holder.dashBoardTitleTxt.getContext(), TemplatesActivity.class);
                holder.itemView.getContext().startActivity(intent);
            } else if (holder.dashBoardTitleTxt.getText().toString().equalsIgnoreCase("Movies")) {
                Intent intent = new Intent(holder.dashBoardTitleTxt.getContext(), MoviesActivity.class);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dashboardItems.size();
    }
}