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

public class DashboardItemsAdapter extends RecyclerView.Adapter<DashboardItemViewHolder> {

    private ArrayList<DashboardItem> dashboardItems;

    void setData(ArrayList<DashboardItem> dashboardItems) {
        this.dashboardItems = dashboardItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DashboardItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dash_board_item, parent, false);
        DashboardItemViewHolder dashBoardItemViewHolder = new DashboardItemViewHolder(view);
        return dashBoardItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardItemViewHolder holder, int position) {
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
